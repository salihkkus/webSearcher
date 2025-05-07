package com.example.websearcher.model;

public class Article {
    public String url;
    public String title;
    public String imageUrl;
    public int readingTime; // reading time in minutes

    public Article(String url, String title, String imageUrl, int readingTime) {
        this.url = url;
        this.title = title;
        this.imageUrl = imageUrl;
        this.readingTime = readingTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(int readingTime) {
        this.readingTime = readingTime;
    }
}
