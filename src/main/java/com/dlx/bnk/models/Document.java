package com.dlx.bnk.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_cstmr_docs")
public class Document extends Auditable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_id")
	private Long docId;

	@Column(name="document_type")
    private String documentType;  //  "AADHAAR", "PAN"

	@Column(name="file_name")
    private String fileName;

	@Column(name="file_type")
    private String fileType;      //    pdf, image,jpeg, etc.

    @Lob
    private byte[] data; 

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

	public Document() {
	}

	public Document(Long docId, String documentType, String fileName, String fileType, byte[] data, Customer customer) {
		super();
		this.docId = docId;
		this.documentType = documentType;
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.customer = customer;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
	
    
}
