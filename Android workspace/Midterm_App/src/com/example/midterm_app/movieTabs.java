package com.example.midterm_app;
/*
 * MidTerm: RottonTomatos app
 * File: MidTerm.zip
 * Name:Eric Bowers
 *
 */

public class movieTabs {
    String title,mpaaRating,thumbnail,theater,detailedImage,links;
    public String getLinks() {
        return links;
    }





    public void setLinks(String links) {
        this.links = links;
    }





    int id,year,runtime,audience_score,critic_score;

    public String getDetailedImage() {
        return detailedImage;
    }





    public void setDetailedImage(String detailedImage) {
        this.detailedImage = detailedImage;
    }






    public String getTheater() {
        return theater;
    }





    public void setTheater(String theater) {
        this.theater = theater;
    }





    public int getRuntime() {
        return runtime;
    }





    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }





    public int getAudience_score() {
        return audience_score;
    }





    public void setAudience_score(int audience_score) {
        this.audience_score = audience_score;
    }





    public int getCritic_score() {
        return critic_score;
    }





    public void setCritic_score(int critic_score) {
        this.critic_score = critic_score;
    }





    public String getThumbnail() {
        return thumbnail;
    }





    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }








    public String getTitle() {
        return title;
    }





    public void setTitle(String title) {
        this.title = title;
    }


    public String getMpaaRating() {
        return mpaaRating;
    }


    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }





    @Override
    public String toString() {
        return "movieTabs [title=" + title + ", mpaaRating=" + mpaaRating
                + ", thumbnail=" + thumbnail + ", theater=" + theater
                + ", detailedImage=" + detailedImage + ", links=" + links
                + ", id=" + id + ", year=" + year + ", runtime=" + runtime
                + ", audience_score=" + audience_score + ", critic_score="
                + critic_score + "]";
    }























}
