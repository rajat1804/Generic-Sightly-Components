package com.aem.cwr.framework.core;

public class NewsProperties {

	public String title;
	public String description;
	public String imagePath;
	public String newsPath;
	
	public String getNewsPath() {
		return newsPath;
	}
	public void setNewsPath(String newsPath) {
		this.newsPath = newsPath;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
