package com.rsd_movieshop.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImgDto {

	private long itemNumber;
	private MultipartFile[] file;

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	long getItemNumber() {
		return itemNumber;
	}

	void setItemNumber(long itemNumber) {
		this.itemNumber = itemNumber;
	}

}
