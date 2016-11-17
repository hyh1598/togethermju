package com.example.mathpresso.togethermju.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by choijinjoo on 2016. 11. 9..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notice implements Serializable {
    private int id;
    private String tag;
    private String title;
    private String content;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTag() { return tag; }

    public void setTag(String tag) { this.tag = tag; }

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
