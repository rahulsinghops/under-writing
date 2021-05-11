package com.ipru.mca.underwriting.model;

import java.util.Date;

public class UWException {
	private Date timestamp;
	private String message;
	private String responseCode;
	public UWException(Date timestamp, String message, String responseCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.responseCode = responseCode;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public UWException() {
		super();
	}
	@Override
	public String toString() {
		return "BikeException [timestamp=" + timestamp + ", message=" + message + ", responseCode=" + responseCode
				+ "]";
	}
	
}
