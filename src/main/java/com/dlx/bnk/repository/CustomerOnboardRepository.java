package com.dlx.bnk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlx.bnk.models.Customer;

@Repository
public interface CustomerOnboardRepository  extends JpaRepository<Customer, Long>{

	Customer findByCustomerId(Long customerId);

}
