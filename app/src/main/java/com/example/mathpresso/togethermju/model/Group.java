package com.example.mathpresso.togethermju.model;

import android.media.Image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "group")
public class Group implements Serializable{
    @DatabaseField
    Integer id;
    @DatabaseField
    String name;
    @DatabaseField
    String introduce;
   // Image profile;


    String title;
    String purpose;
    String content;
    int personnel;

    public Group() {
    }

    public Group(Integer id, String name, String introduce,String title, String purpose, String content, int personnel) {
        this.name = name;
        this.id = id;
        //this.profile = profile;
        this.introduce = introduce;
        this.title = title;
        this.purpose = purpose;
        this.content = content;
        this.personnel = personnel;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonnel() {
        return personnel;
    }

    public void setPersonnel(int personnel) {
        this.personnel = personnel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
