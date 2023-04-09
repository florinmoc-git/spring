package com.hospital.patientadmin.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long patientid;
	private String firstName, lastName, address, nhsNumber;
	private LocalDate dateOfBirth, dateOfDeath;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="patient")
	private List<RequestedProcedure> procedures;
	
	public Patient() {}
	
	public Patient(String firstName, String lastName, String address, String nhsNumber, LocalDate dateOfBirth,
			LocalDate dateOfDeath) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.nhsNumber = nhsNumber;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNhsNumber() {
		return nhsNumber;
	}
	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public List<RequestedProcedure> getProcedures() {
		return procedures;
	}
	public void setProcedures(List<RequestedProcedure> procedures) {
		this.procedures = procedures;
	}

}
