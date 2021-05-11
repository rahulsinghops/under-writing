package com.ipru.mca.underwriting.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BreInputDetails {

	//private String type;
	/*
	 * @Max(value = 60,message ="Min age should be more than 60" )
	 * 
	 * @Min(value = 20,message ="Min age should not be less than  20" )
	 * 
	 * @NotNull(message = "minage should not be null") private int minage;
	 */
	@Max(value = 60,message =" age should not be more than 60" )
	@Min(value = 20,message =" age should not be less than  20" )
	@NotNull(message = "age should not be null")
	private int age;
	@Max(value = 400000,message ="lowerRange should not be more than 400000" )
	@Min(value = 50000,message ="lowerRange  should not be less than  50000" )
	@NotNull(message = "lowerRange should not be null")
	private long lowerRange;
	@Max(value = 1000000,message ="upperRange should not be more than 1000000" )
	@Min(value = 50000,message ="upperRange should not be less than  50000" )
	@NotNull(message = "upperRange should not be null")
	private long upperRange;
	@NotNull(message = "road should not be null")
	@NotBlank(message = "road should not be blank")
	@Size(max = 20,message = "road should not be more than 20 character ")
	private String road;
	@NotNull(message = "offRoadFlag should be Y or N")
	@NotBlank(message = "offRoadFlag should be Y or N")
	@Size(max = 1,message = "offRoadFlag should be Y or N ")
	private String offRoadFlag;
	@NotNull(message = "seaorhill should  be either SEA/HILL")
	@NotBlank(message = "should  be either SEA/HILL")
	@Size(min = 3,max = 4,message = "should  be either SEA/HILL")
	private String seaorhill;

	/*
	 * public String getType() { return type; } public void setType(String type) {
	 * this.type = type; }
	 */
	/*
	 * public int getMinage() { return minage; } public void setMinage(int minage) {
	 * this.minage = minage; }
	 */
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getLowerRange() {
		return lowerRange;
	}
	public void setLowerRange(long lowerRange) {
		this.lowerRange = lowerRange;
	}
	public long getUpperRange() {
		return upperRange;
	}
	public void setUpperRange(long upperRange) {
		this.upperRange = upperRange;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getOffRoadFlag() {
		return offRoadFlag;
	}
	public void setOffRoadFlag(String offRoadFlag) {
		this.offRoadFlag = offRoadFlag;
	}
	public String getSeaorhill() {
		return seaorhill;
	}
	public void setSeaorhill(String seaorhill) {
		this.seaorhill = seaorhill;
	}
	public BreInputDetails(  int age, long lowerRange, long upperRange, String road,
			String offRoadFlag, String seaorhill) {
		super();
		//this.type = type;
		//this.minage = minage;
		this.age = age;
		this.lowerRange = lowerRange;
		this.upperRange = upperRange;
		this.road = road;
		this.offRoadFlag = offRoadFlag;
		this.seaorhill = seaorhill;
	}
	public BreInputDetails() {
		super();
	}
	@Override
	public String toString() {
		return "BreInputDetails [  age=" + age + ", lowerRange="
				+ lowerRange + ", upperRange=" + upperRange + ", road=" + road + ", offRoadFlag=" + offRoadFlag
				+ ", seaorhill=" + seaorhill + "]";
	}
	
}
