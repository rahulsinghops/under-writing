package com.ipru.mca.underwriting.model;

public class BreOutputDetails {
	private String type;
	private long lowerRange;
	private long higherRange;
	private String responseCode;
	public BreOutputDetails(String type, long lowerRange, long higherRange, String responseCode) {
		super();
		this.type = type;
		this.lowerRange = lowerRange;
		this.higherRange = higherRange;
		this.responseCode = responseCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getLowerRange() {
		return lowerRange;
	}
	public void setLowerRange(long lowerRange) {
		this.lowerRange = lowerRange;
	}
	public long getHigherRange() {
		return higherRange;
	}
	public void setHigherRange(long higherRange) {
		this.higherRange = higherRange;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public BreOutputDetails() {
		super();
	}
	@Override
	public String toString() {
		return "BreOutputDetails [type=" + type + ", lowerRange=" + lowerRange + ", higherRange=" + higherRange
				+ ", responseCode=" + responseCode + "]";
	}
	
	

}
