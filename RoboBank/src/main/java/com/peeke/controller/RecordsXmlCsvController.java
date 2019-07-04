package com.peeke.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peeke.model.CustomGenericException;
import com.peeke.model.RoboBankConstants;
import com.peeke.model.Record;
import com.peeke.service.ExtractService;
import com.peeke.service.ValidatorService;

@Controller
public class RecordsXmlCsvController {

	@Autowired
	private ValidatorService validatorService;

	@Autowired
	private ExtractService extractorService;

	@RequestMapping(value = "/processStatment", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile multipart, ModelAndView mav)
			throws Exception {
		List<Record> validRecords = new ArrayList<Record>();
		List<Record> inValidRecords = new ArrayList<Record>();
		List<Record> listUnique = new ArrayList<Record>();
		if (!multipart.isEmpty()) {
			if (multipart.getContentType().equalsIgnoreCase(RoboBankConstants.FILE_TYPE_CSV)) {

				File csvFile = new File(multipart.getOriginalFilename());
				multipart.transferTo(csvFile);
				List<Record> extractedRecords = extractorService.extractStatmentFromCSV(csvFile);
				HashMap<Integer, Record> uniqueRecords = validatorService.getRemoveDuplicateRecords(extractedRecords);

				listUnique = uniqueRecords.values().stream().collect(Collectors.toCollection(ArrayList::new));
				HashMap<String, List<Record>> validInvalidRecordsMap = validatorService
						.getValidAndInvalidRecords(listUnique);
				validRecords = validInvalidRecordsMap.get("validRecords");
				inValidRecords = validInvalidRecordsMap.get("inValidRecords");
				mav.addObject("validRecords", validRecords);
				mav.addObject("inValidRecords", inValidRecords);
				mav.setViewName("validInvalidRecords");

			} else if (multipart.getContentType().equalsIgnoreCase(RoboBankConstants.FILE_TYPE_XML)) {
				File xmlFile = new File(multipart.getOriginalFilename());
				multipart.transferTo(xmlFile);
				List<Record> extractedRecords = extractorService.extractStatmentFromXML(xmlFile);
				HashMap<Integer, Record> uniqueRecords = validatorService.getRemoveDuplicateRecords(extractedRecords);
				listUnique = uniqueRecords.values().stream().collect(Collectors.toCollection(ArrayList::new));
				HashMap<String, List<Record>> validInvalidRecordsMap = validatorService
						.getValidAndInvalidRecords(listUnique);

				validRecords = validInvalidRecordsMap.get("validRecords");
				inValidRecords = validInvalidRecordsMap.get("inValidRecords");
				mav.addObject("validRecords", validRecords);
				mav.addObject("inValidRecords", inValidRecords);
				mav.setViewName("validInvalidRecords");
			} else {
				throw new CustomGenericException(RoboBankConstants.UNSUPORTED_FILE_FORMAT,
						RoboBankConstants.HTTP_CODE_INVALID_INPUT);
			}
		} else {
			throw new CustomGenericException(RoboBankConstants.INVALID_INPUT, RoboBankConstants.HTTP_CODE_INVALID_INPUT);
		}

		return mav;
	}

	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("responseCode", ex.getResponseCode());
		mav.addObject("responseMessage", ex.getResponseMessage());
		mav.setViewName("error");

		return mav;
	}

}
