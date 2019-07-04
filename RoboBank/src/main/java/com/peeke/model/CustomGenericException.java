package com.peeke.model;

import java.util.List;

public class CustomGenericException extends Exception {

	private static final long serialVersionUID = 1L;
	private String responseMessage;
	private int responseCode;
	private List<Record> Records;

	public CustomGenericException(String responseMessage, int responseCode) {
		super();
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}

	public CustomGenericException() {
		super();

	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "Error [responseMessage=" + responseMessage + ", responseCode=" + responseCode + "]";
	}

	public List<Record> getRecords() {
		return Records;
	}

	public void setRecords(List<Record> records) {
		Records = records;
	}

}
