package com.hospital.patientadmin.domain;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RequestedProcedure {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long rpid;
	private String procedureName, bodyPart, referrer, status;
	private LocalDate dateRequested, datePerformed; 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient")
	private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public RequestedProcedure() {}	
	

	public RequestedProcedure(String procedureName, String bodyPart, String referrer, String status, LocalDate dateRequested,
			LocalDate datePerformed, Patient patient) {
		super();
		this.procedureName = procedureName;
		this.bodyPart = bodyPart;
		this.referrer = referrer;
		this.status = status;
		this.dateRequested = dateRequested;
		this.datePerformed = datePerformed;
		this.patient = patient;
	}

	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getBodyPart() {
		return bodyPart;
	}
	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(LocalDate dateRequested) {
		this.dateRequested = dateRequested;
	}
	public LocalDate getDatePerformed() {
		return datePerformed;
	}
	public void setDatePerformed(LocalDate datePerformed) {
		this.datePerformed = datePerformed;
	}


}
