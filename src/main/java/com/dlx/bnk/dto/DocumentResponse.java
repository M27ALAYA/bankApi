package com.dlx.bnk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentResponse {

    private Long docId;
    private String documentType;
    private String fileName;
    private String fileType;
    
	public DocumentResponse() {
	}

	public DocumentResponse(Long docId, String documentType, String fileName, String fileType) {
		super();
		this.docId = docId;
		this.documentType = documentType;
		this.fileName = fileName;
		this.fileType = fileType;
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
    
	
    
}
