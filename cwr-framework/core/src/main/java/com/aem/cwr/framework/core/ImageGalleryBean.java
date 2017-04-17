package com.aem.cwr.framework.core;

import java.util.ArrayList;

public class ImageGalleryBean {

	private ArrayList<String> imageList;
	private ArrayList<String> folderList;
	private String mode;
	private String imgPerRow;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public ArrayList<String> getImageList() {
		return imageList;
	}
	public void setImageList(ArrayList<String> imageList) {
		this.imageList = imageList;
	}
	
	public ArrayList<String> getFolderList() {
		return folderList;
	}
	public void setFolderList(ArrayList<String> folderList) {
		this.folderList = folderList;
	}
	public String getImgPerRow() {
		return imgPerRow;
	}
	public void setImgPerRow(String imgPerRow) {
		this.imgPerRow = imgPerRow;
	}
	
}
