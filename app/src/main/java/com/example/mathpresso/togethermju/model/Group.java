package com.example.mathpresso.togethermju.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {
    Integer id;
    String name;
    String introduce;

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
