package com.ipru.mca.underwriting.logging;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class UWLogging {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long logid;
	@Column(name="s_name")
	private String serviceName;
	@Lob
	private String request;
	@Lob
	private String response;
	@Lob
	private String error;
	private Date modifiedat;
	public UWLogging(String serviceName, String request, String response, String error,Date modifiedat) {
		super();
		this.serviceName = serviceName;
		this.request = request;
		this.response = response;
		this.error = error;
		this.modifiedat = modifiedat;
	}
	public Date getModifiedat() {
		return modifiedat;
	}
	public void setModifiedat(Date modifiedat) {
		this.modifiedat = modifiedat;
	}
	public UWLogging() {
		super();
	}
	public long getLogid() {
		return logid;
	}
	public void setLogid(long logid) {
		this.logid = logid;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	

}
