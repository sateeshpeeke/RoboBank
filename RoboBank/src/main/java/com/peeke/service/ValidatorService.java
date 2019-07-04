package com.peeke.service;

import java.util.HashMap;
import java.util.List;

import com.peeke.model.Record;

public interface ValidatorService {

public HashMap<Integer, Record> getRemoveDuplicateRecords(List<Record> records);
	
	public HashMap<String, List<Record>> getValidAndInvalidRecords(List<Record> records);

	
}
