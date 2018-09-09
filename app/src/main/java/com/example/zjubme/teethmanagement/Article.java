package com.example.zjubme.teethmanagement;

public class Article {
    private String title;
    private String date;
    private String author;
    private String url;
    private String image;

    public Article(String title, String date, String author, String url, String image){
        this.author = author;
        this.date = date;
        this.title = title;
        this.url = url;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}
