package com.example.mathpresso.togethermju.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "group")
public class Group {
    @DatabaseField
    Integer id;
    @DatabaseField
    String name;
    @DatabaseField
    String introduce;

    public Group() {
    }

    public Group(Integer id, String name, String introduce) {
        this.name = name;
        this.id = id;
        this.introduce = introduce;
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
}
