package com.example.mathpresso.togethermju.model;

/**
 * Created by sonjiho on 2016. 12. 6..
 */

public class Reply {
    private String username;
    private String date;
    private String content;

    public Reply(String name,String date,String content){

        this.username  = name;
        this.date = date;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {

        return content;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }
}
