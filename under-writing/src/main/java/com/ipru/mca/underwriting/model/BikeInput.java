package com.ipru.mca.underwriting.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class BikeInput {
	@NotBlank(message = "city value is mandatory")
	@NotEmpty(message = "city value is mandatory")
	@NotNull(message = "city value is mandatory")
	private String city;
	@NotBlank(message = "type value is mandatory")
	@NotEmpty(message = "type value is mandatory")
	@NotNull(message = "type value is mandatory")
	private String type;
	@NotNull(message = "minvalue value is mandatory")
	@Min(value = 40000,message = "minvalue should not be less tha 40000")
	@Max(value = 400000,message = "minvalue should not be greater tha 400000")
	private long minvalue;
	@NotNull(message = "maxvalue value is mandatory")
	@Min(value = 40000,message = "maxvalue should not be less tha 40000")
	@Max(value = 600000,message = "maxvalue should not be greater tha 600000")
	private long maxvalue;
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
	public long getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(long minvalue) {
		this.minvalue = minvalue;
	}
	public long getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(long maxvalue) {
		this.maxvalue = maxvalue;
	}
	public BikeInput(String city, String type, long minvalue, long maxvalue) {
		super();
		this.city = city;
		this.type = type;
		this.minvalue = minvalue;
		this.maxvalue = maxvalue;
	}
	public BikeInput() {
		super();
	}
	@Override
	public String toString() {
		return "BikeInput [city=" + city + ", type=" + type + ", minvalue=" + minvalue + ", maxvalue=" + maxvalue + "]";
	}
	
	

}
