package com.example.mathpresso.togethermju.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "group")
public class Group implements Serializable{

    @DatabaseField
    String id;
    @DatabaseField
    String name;
    @DatabaseField
    String introduce;
    @DatabaseField
    String notice;
    @DatabaseField
    ArrayList<String> students;

    public void setId(String id) {
        this.id = id;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }



    public Group() {
    }

    public String getId() {
        return id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getNotice() {
        return notice;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getStudents() {
        return students;
    }








}
