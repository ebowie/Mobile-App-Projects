package com.example.fifaworldcupphotos;

public class Article {
	
	// This is the object for a World Cup news feed article
	String title;
	String description;
	String image;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", description=" + description
				+ ", image=" + image + "]";
	}
	
}
