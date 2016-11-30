package com.example.mathpresso.togethermju.model;

import java.util.ArrayList;

/**
 * Created by donghyuk on 2016. 11. 17..
 */
public class User {

    public static final String PROJECT_NUMER = "743118472391";
    String rid;
    String email;
    String password;
    String gender;
    String birth;
    String major;
    String name;
    ArrayList<String> interest;

    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    public String getName() {
        return this.name;
    }
    public String setRID(String rid) {
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
