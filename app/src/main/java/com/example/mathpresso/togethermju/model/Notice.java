package com.example.mathpresso.togethermju.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by choijinjoo on 2016. 11. 9..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "notice")
public class Notice implements Serializable {
    @DatabaseField
    private int id;
    @DatabaseField
    private String tag;
    @DatabaseField
    private String title;
    @DatabaseField
    private String content;

    public Notice() {
    }

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
