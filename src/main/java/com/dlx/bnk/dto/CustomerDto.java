package com.dlx.bnk.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {

	private Long customerId;
	private String fullName;
	private String dateOfBirth;
	private String email;
	private String phoneNumber;
	
	@NotNull(message = "Aadhaar cannot be null")
	@Pattern(regexp = "\\d{12}", message = "Aadhaar must be 12 digits")
	private String aadhaarNumber;
	private String panNumber;
	
	private List<AddressDto> address;
	private List<ServiceTypeDto> services;
    private List<DocumentResponse> document;
	
	public CustomerDto() {
		
	}
	public CustomerDto(Long customerId, String fullName, String dateOfBirth, String email, String phoneNumber,
			String aadhaarNumber, String panNumber, List<AddressDto> address, List<ServiceTypeDto> services,List<DocumentResponse> document) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.panNumber = panNumber;
		this.address = address;
		this.services = services;
		this.document=document;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public List<AddressDto> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDto> address) {
		this.address = address;
	}
	public List<ServiceTypeDto> getServices() {
		return services;
	}
	public void setServices(List<ServiceTypeDto> services) {
		this.services = services;
	}
	public List<DocumentResponse> getDocument() {
		return document;
	}
	public void setDocument(List<DocumentResponse> document) {
		this.document = document;
	}

	
	
}
