package com.dlx.bnk.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
@Entity
@Table(name="t_bank_services")
public class ServiceType extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private Long serviceId;
	
	@Column(name="savings_account")
	private String savingsAccount;
	
	@Column(name="current_account")
	private String currentAccount;
	
	@Column(name="loan")
	private String Loan;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public ServiceType() {
	}

	public ServiceType(Long serviceId, String savingsAccount, String currentAccount, String loan, Customer customer) {
		super();
		this.serviceId = serviceId;
		this.savingsAccount = savingsAccount;
		this.currentAccount = currentAccount;
		Loan = loan;
		this.customer = customer;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	} 

	
}
