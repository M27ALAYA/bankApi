package com.dlx.bnk.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dlx.bnk.dto.CustomerDto;
import com.dlx.bnk.dto.DocumentResponse;

public interface CustomerOnboardService {

	CustomerDto saveCustomer(CustomerDto customr);
	
	 List<DocumentResponse> uploadDocuments(Long customerId, MultipartFile aadhaarFile, MultipartFile panFile,MultipartFile[] otherFiles);

	CustomerDto getCustomerByCustomerId(Long customerId);
	 
	/*
	 * List<DocumentResponse> uploadDocuments(Long customerId, MultipartFile
	 * aadhaarFile, MultipartFile panFile, MultipartFile otherFiles);
	 */

}
