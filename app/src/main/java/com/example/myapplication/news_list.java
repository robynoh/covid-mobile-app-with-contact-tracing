package com.example.myapplication;

class news_List {

    private int id;
    private String title;
    private String content;
    private String day;
    private String month;
    private String year;
    private String image;

    public void setId(int id){
        this.id=id;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String content){
        this.content=content;
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

    public void setImg(String image){
        this.image=image;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
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

    public String getImg(){
        return image;
    }

}
