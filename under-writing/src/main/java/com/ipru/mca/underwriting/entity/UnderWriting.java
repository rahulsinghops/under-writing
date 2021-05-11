package com.ipru.mca.underwriting.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class UnderWriting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long u_id;
	private String city;
	private String type;
	private int age;
	private long lowerRange;
	private long upperRange;
	private String road;
	private String offRoadFlag;
	private String seaorhill;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bu_id")
	private List<BikeDetails> bikeDetails;
	public UnderWriting(String city, String type, int age, long lowerRange, long upperRange, String road,
			String offRoadFlag, String seaorhill, List<BikeDetails> bikeDetails) {
		super();
		this.city = city;
		this.type = type;
		this.age = age;
		this.lowerRange = lowerRange;
		this.upperRange = upperRange;
		this.road = road;
		this.offRoadFlag = offRoadFlag;
		this.seaorhill = seaorhill;
		this.bikeDetails = bikeDetails;
	}
	public UnderWriting() {
		super();
	}
	@Override
	public String toString() {
		return "UnderWriting [u_id=" + u_id + ", city=" + city + ", type=" + type + ", age=" + age + ", lowerRange="
				+ lowerRange + ", upperRange=" + upperRange + ", road=" + road + ", offRoadFlag=" + offRoadFlag
				+ ", seaorhill=" + seaorhill + ", bikeDetails=" + bikeDetails + "]";
	}
	public long getU_id() {
		return u_id;
	}
	public void setU_id(long u_id) {
		this.u_id = u_id;
	}
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
	public List<BikeDetails> getBikeDetails() {
		return bikeDetails;
	}
	public void setBikeDetails(List<BikeDetails> bikeDetails) {
		this.bikeDetails = bikeDetails;
	}
	

}
