package com.dlx.bnk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dlx.bnk.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	List<Address> findByCustomerCustomerId(Long customerId);

}
