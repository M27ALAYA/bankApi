package com.dlx.bnk.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dlx.bnk.controller.CustomerOnboardingController;
import com.dlx.bnk.dto.AddressDto;
import com.dlx.bnk.dto.CustomerDto;
import com.dlx.bnk.dto.DocumentResponse;
import com.dlx.bnk.dto.ServiceTypeDto;
import com.dlx.bnk.exception.CustomerNotFoundException;
import com.dlx.bnk.models.Address;
import com.dlx.bnk.models.Customer;
import com.dlx.bnk.models.Document;
import com.dlx.bnk.models.ServiceType;
import com.dlx.bnk.repository.AddressRepository;
import com.dlx.bnk.repository.CustomerOnboardRepository;
import com.dlx.bnk.repository.DocumentsRepository;
import com.dlx.bnk.repository.ServiceTypeRepsitory;
import com.dlx.bnk.service.CustomerOnboardService;

import jakarta.transaction.Transactional;

@Service
public class CustomerOnboardServiceImpl implements CustomerOnboardService{

	private static final Logger log = LoggerFactory.getLogger(CustomerOnboardingController.class);

	@Autowired
	private CustomerOnboardRepository customerOnboardRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ServiceTypeRepsitory serviceTypeRepsitory;
	@Autowired
	private DocumentsRepository documentsRepository;
	
	//for both save and update Customer based upon id
    @Transactional
	@Override
	public CustomerDto saveCustomer(CustomerDto customr) {
		CustomerDto resp = null;
		try {
			 Customer createCustomer;

		            // UPDATE
			 createCustomer = customerOnboardRepository.findByCustomerId(customr.getCustomerId());
		            if (createCustomer == null) {
		                throw new CustomerNotFoundException("Customer Not Found With The Id: " + customr.getCustomerId());
		            }
		         else {
		            // CREATE
		        	 createCustomer = new Customer();
		        }
			
			    createCustomer = new Customer();
				createCustomer.setFullName(customr.getFullName());
				createCustomer.setEmail(customr.getEmail());
				createCustomer.setDateOfBirth(customr.getDateOfBirth());
				createCustomer.setPhoneNumber(customr.getPhoneNumber());
				createCustomer.setPanNumber(customr.getPanNumber());
				createCustomer.setAadhaarNumber(customr.getAadhaarNumber());
				
				Customer savedCustomer = customerOnboardRepository.save(createCustomer);
				log.info("Customer Saved Successfully");
				
					if (customr.getAddress() != null && !customr.getAddress().isEmpty()) {
						/*
						 * List<Address> addressList = new ArrayList<>();
						 */ 
					        for (AddressDto addressDto : customr.getAddress()) {
					        	
					            Address address = new Address();
					            
					         // Update address if ID exists
					            if (addressDto.getAddressId() != null) {
					                address = addressRepository.findById(addressDto.getAddressId()).orElse(address);
					            }
					            address.setLine1(addressDto.getLine1());
					            address.setLine2(addressDto.getLine2());
					            address.setCity(addressDto.getCity());
					            address.setState(addressDto.getState());
					            address.setPincode(addressDto.getPincode());
					            
					            address.setCustomer(savedCustomer);
					            addressRepository.save(address);
					        }
					}
					
					if (customr.getServices() != null) {
						/* List<ServiceType> serviceList = new ArrayList<>(); */
				        
				        for (ServiceTypeDto serviceDto : customr.getServices() ) {
				            ServiceType service = new ServiceType();

				            // Update service if ID exists
				            if (serviceDto.getServiceId() != null) {
				                service = serviceTypeRepsitory.findById(serviceDto.getServiceId()).orElse(service);
				            }
				            service.setSavingsAccount(serviceDto.getSavingsAccount());
				            service.setCurrentAccount(serviceDto.getCurrentAccount());
				            service.setLoan(serviceDto.getLoan());
				            
				            service.setCustomer(savedCustomer);
				            serviceTypeRepsitory.save(service);
				        }
				    }
					
					resp = new CustomerDto();
					resp.setCustomerId(savedCustomer.getCustomerId());
					resp.setFullName(savedCustomer.getFullName());
					resp.setEmail(savedCustomer.getEmail());
					resp.setDateOfBirth(savedCustomer.getDateOfBirth());
					resp.setPhoneNumber(savedCustomer.getPhoneNumber());
					resp.setPanNumber(savedCustomer.getPanNumber());
					resp.setAadhaarNumber(savedCustomer.getAadhaarNumber());

					resp.setAddress(customr.getAddress());
					resp.setServices(customr.getServices());
					

			} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while Saving in saveCustomer() at CustomerOnboardServiceImpl{}: ");
		}
		return resp;
	}

    // fo rboth save and update
	@Transactional
	@Override
	public List<DocumentResponse> uploadDocuments(Long customerId, MultipartFile aadhaarFile, MultipartFile panFile,MultipartFile[] otherFiles) {
		List<DocumentResponse> responseList= new ArrayList<>();
		   List<Document> documents = new ArrayList<>();

		try {
			 Customer customer = customerOnboardRepository.findById(customerId).orElseThrow(
					 						()-> new CustomerNotFoundException("Customer Not Found With The Id: " + customerId));
			 
		        if (aadhaarFile != null && !aadhaarFile.isEmpty()) {
		            Document doc = convertToDocument(aadhaarFile, "AADHAAR", customer);
		            documents.add(doc);
		        }

		        if (panFile != null && !panFile.isEmpty()) {
		        	Document doc = convertToDocument(panFile, "PAN", customer);
		            documents.add(doc);
		        }

		        if (otherFiles != null) {
		            for (MultipartFile file : otherFiles) {
		                if (!file.isEmpty()) {
		                	Document doc = convertToDocument(file, "OTHER", customer);
		                    documents.add(doc);
		                }
		            }
		        }

		        List<Document> saveDocs= documentsRepository.saveAll(documents);
			 
		        for (Document doc : saveDocs) {
		            responseList.add(toResponse(doc));
		        }


		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while Uploading Docs in uploadDocuments() at CustomerOnboardServiceImpl{}:");
		}
		return responseList;
	}
	
	private Document convertToDocument(MultipartFile file, String type, Customer customer) throws IOException {
		Document doc = new Document();
	    doc.setCustomer(customer);
	    doc.setDocumentType(type);
	    doc.setFileName(file.getOriginalFilename());
	    doc.setFileType(file.getContentType());
	    doc.setData(file.getBytes());
	    return doc;
	}
	
	private DocumentResponse toResponse(Document doc) {
	    return new DocumentResponse(
	            doc.getDocId(),
	            doc.getDocumentType(),
	            doc.getFileName(),
	            doc.getFileType()
	    );
	}

	@Override
	public CustomerDto getCustomerByCustomerId(Long customerId) {
		try {
			Customer customer = customerOnboardRepository.findById(customerId) .orElseThrow(() ->
		                    new CustomerNotFoundException("Customer Not Found With The Id: " + customerId));


			CustomerDto response = new CustomerDto();

			    response.setCustomerId(customer.getCustomerId());
			    response.setFullName(customer.getFullName());
			    response.setDateOfBirth(customer.getDateOfBirth());
			    response.setEmail(customer.getEmail());
			    response.setPhoneNumber(customer.getPhoneNumber());
			    response.setAadhaarNumber(customer.getAadhaarNumber());
			    response.setPanNumber(customer.getPanNumber());

			    List<AddressDto> addressDtos = new ArrayList<>();
			    List<Address> addresses = addressRepository.findByCustomerCustomerId(customerId);
			    for (Address address : addresses) {
			        AddressDto dto = new AddressDto();
			        dto.setAddressId(address.getAddressId());
			        dto.setLine1(address.getLine1());
			        dto.setLine2(address.getLine2());
			        dto.setCity(address.getCity());
			        dto.setState(address.getState());
			        dto.setPincode(address.getPincode());
			        addressDtos.add(dto);
			    }
			    response.setAddress(addressDtos);

			    List<ServiceTypeDto> serviceDtos = new ArrayList<>();
			    List<ServiceType> services = serviceTypeRepsitory.findByCustomerCustomerId(customerId);
			    for (ServiceType service : services) {
			        ServiceTypeDto dto = new ServiceTypeDto();
			        dto.setServiceId(service.getServiceId());
			        dto.setSavingsAccount(service.getSavingsAccount());
			        dto.setCurrentAccount(service.getCurrentAccount());
			        dto.setLoan(service.getLoan());
			        serviceDtos.add(dto);
			    }
			    response.setServices(serviceDtos);

			    List<DocumentResponse> documentDtos = new ArrayList<>();
			    List<Document> documents = documentsRepository.findByCustomerCustomerId(customerId);
			    for (Document doc : documents) {
			        DocumentResponse dto = new DocumentResponse();
			        dto.setDocId(doc.getDocId());
			        dto.setDocumentType(doc.getDocumentType());
			        dto.setFileName(doc.getFileName());
			        dto.setFileType(doc.getFileType());
			        documentDtos.add(dto);
			    }
			    response.setDocument(documentDtos);

		        return response;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while fetching Customer Details in getCustomerByCustomerId at CustomerOnboardServiceImpl{}:");
			throw new RuntimeException("Error while fetching customer details", e);
		}
	}


}
