package com.ipru.mca.underwriting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class BikeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long b_id;
	private String model;
	private String version;
	private double exshowroom;
	@Lob
    private String details;
	public BikeDetails(String model, String version, double exshowroom, String details) {
		super();
		this.model = model;
		this.version = version;
		this.exshowroom = exshowroom;
		this.details = details;
	}
	public BikeDetails() {
		super();
	}
	@Override
	public String toString() {
		return "BikeDetails [b_id=" + b_id + ", model=" + model + ", version=" + version + ", exshowroom=" + exshowroom
				+ ", details=" + details + "]";
	}
	public long getB_id() {
		return b_id;
	}
	public void setB_id(long b_id) {
		this.b_id = b_id;
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
	
}
