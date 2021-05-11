package com.ipru.mca.underwriting.model;

import java.util.List;

public class BikeOutput {
	private String city;
	private String type;
	private String responsecode;
	private List<BikeBO> bikedetails;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResponsecode() {
		return responsecode;
	}
	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}
	public List<BikeBO> getBikedetails() {
		return bikedetails;
	}
	public void setBikedetails(List<BikeBO> bikedetails) {
		this.bikedetails = bikedetails;
	}
	public BikeOutput(String city, String type, String responsecode, List<BikeBO> bikedetails) {
		super();
		this.city = city;
		this.type = type;
		this.responsecode = responsecode;
		this.bikedetails = bikedetails;
	}
	public BikeOutput() {
		super();
	}
	@Override
	public String toString() {
		return "BikeOutput [city=" + city + ", type=" + type + ", responsecode=" + responsecode + ", bikedetails="
				+ bikedetails + "]";
	}
	

}
