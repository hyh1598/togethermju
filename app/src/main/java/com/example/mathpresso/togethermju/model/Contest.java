package com.example.mathpresso.togethermju.model;

import java.io.Serializable;

/**
 * Created by choijinjoo on 2016. 11. 9..
 */
public class Contest implements Serializable{
    private int id;
    private String title;
    private String content;

    public Contest(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
