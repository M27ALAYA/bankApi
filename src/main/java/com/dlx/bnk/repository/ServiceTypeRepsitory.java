package com.dlx.bnk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dlx.bnk.models.ServiceType;

public interface ServiceTypeRepsitory extends JpaRepository<ServiceType, Long>{

	List<ServiceType> findByCustomerCustomerId(Long customerId);

}
