package com.dlx.bnk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlx.bnk.models.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long>{

	List<Document> findByCustomerCustomerId(Long customerId);

}
