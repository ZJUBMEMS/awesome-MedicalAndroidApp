package com.example.zjubme.teethmanagement;

public class Article {
    private String title;
    private String date;
    private String author;
    private String url;

    public Article(String title, String date, String author, String url){
        this.author = author;
        this.date = date;
        this.title = title;
        this.url = url;
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
}
