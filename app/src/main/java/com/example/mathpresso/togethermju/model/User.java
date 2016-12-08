package com.example.mathpresso.togethermju.model;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by donghyuk on 2016. 11. 17..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "user")
public class User implements Serializable {
    String rid;//for GCM register ID
    String email;
    String password;
    String gender;
    String birth;
    String major;
    String name;
    String pic;//image path in server
    Bitmap bitmap_pic;//image
    public void setBitmap_pic(Bitmap bitmap_pic) {
        this.bitmap_pic = bitmap_pic;
    }

    public Bitmap getBitmap_pic() {
        return bitmap_pic;
    }



    public String getPic() {
        return pic;
    }

    ArrayList<String> interest;//don`t use
    public User(){

    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public User(String name, String email,String major){
        this.name = name;
        this.email = email;
        this.major = major;
    }
    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public String setRid(String rid) {
        this.rid = rid;
        return this.rid;
    }

    public String getRid() {
        return this.rid;
    }

    public String setEmail(String email) {
        this.email = email;
        return this.email;
    }

    public String getEmail() {
        return this.email;
    }

    public String setPassword(String password) {
        this.password = password;
        return this.password;
    }

    public String getPassword() {
        return this.password;
    }

    public String setGender(String gender) {
        this.gender = this.gender;
        return this.gender;
    }

    public String getGender() {
        return this.gender;
    }

    public String setBirth(String birth) {
        this.birth = birth;
        return this.birth;
    }

    public String getBirth() {
        return this.birth;
    }

    public String setMajor(String major) {
        this.major = major;
        return this.major;
    }

    public String getMajor() {
        return this.major;
    }

    public ArrayList<String> setFavorite(ArrayList<String> interest) {
        this.interest = interest;
        return this.interest;
    }

    public ArrayList<String> getFavorite() {
        return this.interest;
    }
}
