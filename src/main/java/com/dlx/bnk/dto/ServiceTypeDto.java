package com.dlx.bnk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceTypeDto {

	private Long serviceId;
	private String savingsAccount;
	private String currentAccount;
	private String Loan;
	
	public ServiceTypeDto() {
	}

	public ServiceTypeDto(Long serviceId, String savingsAccount, String currentAccount, String loan) {
		super();
		this.serviceId = serviceId;
		this.savingsAccount = savingsAccount;
		this.currentAccount = currentAccount;
		Loan = loan;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(String savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public String getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(String currentAccount) {
		this.currentAccount = currentAccount;
	}

	public String getLoan() {
		return Loan;
	}

	public void setLoan(String loan) {
		Loan = loan;
	}
	
	
}
