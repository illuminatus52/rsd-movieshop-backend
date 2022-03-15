package com.rsd_movieshop.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImgDto {

	private MultipartFile[] file;

	public ImgDto() {
		super();
	}

	public ImgDto(MultipartFile[] file) {
		super();
		this.file = file;
	}

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	
	
}
