package com.example.mathpresso.togethermju.model;

import java.util.ArrayList;

/**
 * Created by donghyuk on 2016. 11. 17..
 */

public class User {
    public static final String PROJECT_NUMER = "743118472391";
    String RID;
    String Email;
    String Password;
    String Gender;
    String Birth;
    String Major;
    String Name;
    ArrayList<String> Interest;

    public String setName(String Name) {
        this.Name = Name;
        return this.Name;
    }

    public String getName() {
        return this.Name;
    }
    public String setRID(String RID) {
        this.RID = RID;
        return this.RID;
    }

    public String getRID() {
        return this.RID;
    }

    public String setEmail(String Email) {
        this.Email = Email;
        return this.Email;
    }

    public String getEmail() {
        return this.Email;
    }

    public String setPassword(String Password) {
        this.Password = Password;
        return this.Password;
    }

    public String getPassword() {
        return this.Password;
    }

    public String setGender(String gender) {
        this.Gender = Gender;
        return this.Gender;
    }

    public String getGender() {
        return this.Gender;
    }

    public String setBirth(String Birth) {
        this.Birth = Birth;
        return this.Birth;
    }

    public String getBirth() {
        return this.Birth;
    }

    public String setMajor(String Major) {
        this.Major = Major;
        return this.Major;
    }

    public String getMajor() {
        return this.Major;
    }

    public ArrayList<String> setFavorite(ArrayList<String> Interest) {
        this.Interest = Interest;
        return this.Interest;
    }

    public ArrayList<String> getFavorite() {
        return this.Interest;
    }
}
