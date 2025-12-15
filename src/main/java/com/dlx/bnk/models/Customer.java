package com.dlx.bnk.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
@Entity
@Table(name="t_mst_customer")
public class Customer extends Auditable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="email")
	private String email;

	@Column(name="phone_no")
	private String phoneNumber;

	@Column(name="aadhar_no")
	private String aadhaarNumber;

	@NotBlank
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "PAN must be 10 characters: 5 letters, 4 digits, 1 letter (uppercase)")
	@Column(name="pan_no")
	private String panNumber;
	
	public Customer() {
	}

	public Customer(Long customerId, String fullName, String dateOfBirth, String email, String phoneNumber,
			String aadhaarNumber, String panNumber) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.panNumber = panNumber;
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
	
	
	
}
