package com.scripture.celebratescripture;

public class BlogPost {
	
	String date;
	String title;
	String passage;
	String person;
	String link;
	String time;
	String thumbnail;
	String message;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPassage() {
		return passage;
	}
	public void setPassage(String passage) {
		this.passage = passage;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "BlogPost [date=" + date + ", title=" + title + ", passage="
				+ passage + ", person=" + person + ", link=" + link + ", time="
				+ time + ", thumbnail=" + thumbnail + ", message=" + message
				+ "]";
	}
	
}
