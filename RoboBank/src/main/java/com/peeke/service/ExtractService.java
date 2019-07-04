package com.peeke.service;

import java.io.File;
import java.util.List;

import com.peeke.model.Record;

public interface ExtractService {

	public List<Record> extractStatmentFromCSV(File file) throws Exception;

	public List<Record> extractStatmentFromXML(File file) throws Exception;

}
