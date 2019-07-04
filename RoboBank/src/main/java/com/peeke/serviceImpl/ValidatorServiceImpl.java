package com.peeke.serviceImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.peeke.model.Record;
import com.peeke.service.ValidatorService;

public class ValidatorServiceImpl implements ValidatorService {

	public HashMap<Integer, Record> getRemoveDuplicateRecords(List<Record> records) {
		HashMap<Integer, Record> uniqueRecords = new HashMap<Integer, Record>();
		List<Record> duplicateRecords = new ArrayList<Record>();
		for (Record record : records) {
			if (uniqueRecords.containsKey(record.getTransactionRef())) {
				duplicateRecords.add(record);
			} else {
				uniqueRecords.put(record.getTransactionRef(), record);
			}
		}
		List<Record> finalDuplicateRecords = new ArrayList<Record>();
		finalDuplicateRecords.addAll(duplicateRecords);
		for (Record record : duplicateRecords) {
			if (null != uniqueRecords.get(record.getTransactionRef())) {
				finalDuplicateRecords.add(uniqueRecords.get(record.getTransactionRef()));
				uniqueRecords.remove(record.getTransactionRef());
			}
		}
		
		return uniqueRecords;
	}

	public HashMap<String, List<Record>> getValidAndInvalidRecords(List<Record> records) {
		List<Record> validRecords = new ArrayList<Record>();
		List<Record> inValidRecords = new ArrayList<Record>();
		HashMap<String, List<Record>> validAndinvalidRecords = new HashMap<String, List<Record>>();
		DecimalFormat df = new DecimalFormat("#.##");
		for (Record record : records) {
			double sum=record.getStartBalance()+record.getMutation();
			
			if(df.format(sum).equals(String.valueOf(record.getEndBalance()))) {
				
				validRecords.add(record);
			}else {
				inValidRecords.add(record);
			}
			
			
		}
		
		validAndinvalidRecords.put("validRecords", validRecords);
		validAndinvalidRecords.put("inValidRecords", inValidRecords);
		
		return validAndinvalidRecords;

	}

}
