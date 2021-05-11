package com.ipru.mca.underwriting.model;

public class BikeBO {
	private String model;
	private String version;
	private double exshowroom;

	private String details;	
	//private String price;

	public BikeBO(String model, String version, double exshowroom, String details) {
		super();
		this.model = model;
		this.version = version;
		this.exshowroom = exshowroom;
		this.details = details;
		//this.price = price;
	}

	public BikeBO() {
		super();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public double getExshowroom() {
		return exshowroom;
	}

	public void setExshowroom(double exshowroom) {
		this.exshowroom = exshowroom;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "BikeBO [model=" + model + ", version=" + version + ", exshowroom=" + exshowroom + ", details=" + details
				+ "]";
	}

	/*
	 * public String getPrice() { return price; }
	 * 
	 * public void setPrice(String price) { this.price = price; }
	 */

}
