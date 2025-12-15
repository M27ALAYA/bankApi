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
public class CustomerResponseDto {

	private Long customerId;
	private String message;
	
	public CustomerResponseDto() {
	}

	public CustomerResponseDto(Long customerId, String message) {
		super();
		this.customerId = customerId;
		this.message = message;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
