package com.dlx.bnk.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dlx.bnk.dto.CustomerDto;
import com.dlx.bnk.dto.CustomerResponseDto;
import com.dlx.bnk.dto.DocumentResponse;
import com.dlx.bnk.service.CustomerOnboardService;

//@Slf4j
@RestController
@RequestMapping(path="/onBoard/customer")
public class CustomerOnboardingController {
	private static final Logger log = LoggerFactory.getLogger(CustomerOnboardingController.class);

	@Autowired
	private CustomerOnboardService customerOnboardService;
	
	//
	@RequestMapping(path="/create",method=RequestMethod.POST)
	public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerDto customr){
		
		CustomerResponseDto response;
		try {
			CustomerDto saveCustomer=customerOnboardService.saveCustomer(customr);
			
			  response = new CustomerResponseDto(saveCustomer.getCustomerId(),"Customer created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error While Saving Customer Details in createCustomer() at CustomerOnboardingController{} :");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@RequestMapping(path="/upload/{customerId}",method=RequestMethod.POST)
	public ResponseEntity<List<DocumentResponse>> uploadDoc( @PathVariable Long customerId,@RequestParam(required = false) MultipartFile aadhaarFile,
            @RequestParam(required = false) MultipartFile panFile,@RequestParam(required = false) MultipartFile[] otherFiles){
		 try {
	            List<DocumentResponse> response = customerOnboardService.uploadDocuments(customerId, aadhaarFile, panFile, otherFiles);
	            return ResponseEntity.ok(response);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.info("Error While Uploading Customer Documents in uploadDoc() at CustomerOnboardingController{} :");
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	@RequestMapping(path="/fetchByCustomerId/{customerId}",method=RequestMethod.GET)
	public ResponseEntity<CustomerDto> getCustomerDetailsById( @PathVariable Long customerId ){
		
		 try {
		        CustomerDto customerDto = customerOnboardService.getCustomerByCustomerId(customerId);
		        
		        return ResponseEntity.ok(customerDto);

		    } catch (Exception e) {
		        log.error("Error while fetching Customer details", e);
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
}
