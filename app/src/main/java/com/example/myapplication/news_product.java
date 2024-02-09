package com.example.myapplication;



public class news_product {
    private String image;
    private String title;
    private String date;
    private String content;
    private String day;
    private String month;
    private String year;
    private String id;

    public news_product(String image, String title, String date, String day, String month, String year,String content,String id) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.day= day;
        this.month= month;
        this.year= year;
        this.content = content;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDay(String day){
        this.day=day;
    }
    public void setMonth(String month){
        this.day=month;
    }
    public void setYear(String year){
        this.day=year;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public String getId() {
        return id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDay(){
        return day;
    }
    public String getMonth(){
        return month;
    }
    public String getYear(){
        return year;
    }
}

