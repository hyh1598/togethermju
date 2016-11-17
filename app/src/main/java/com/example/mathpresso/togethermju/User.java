package com.example.mathpresso.togethermju;

import java.util.ArrayList;

/**
 * Created by donghyuk on 2016. 11. 17..
 */

public class User {
    String userEmail;
    String userPassword;
    String userGender;
    int userYear;
    int userMonth;
    int userDay;
    String userMajor;
    ArrayList<String> userFavorite;

    public String setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this.userEmail;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this.userPassword;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public String setUserGender(String userSex) {
        this.userGender = userSex;
        return this.userGender;
    }

    public String getUserGender() {
        return this.userGender;
    }

    public int setUserYear(int userYear) {
        this.userYear = userYear;
        return this.userYear;
    }

    public int getUserYear() {
        return this.userYear;
    }

    public int setUserMonth(int userMonth) {
        this.userMonth = userMonth;
        return this.userMonth;
    }

    public int getUserMonth() {
        return this.userMonth;
    }

    public int setUserDay(int userDay) {
        this.userDay = userDay;
        return this.userDay;
    }

    public int getUserDay() {
        return this.userDay;
    }

    public String setUserMajor(String userMajor) {
        this.userMajor = userMajor;
        return this.userMajor;
    }

    public String getUserMajor() {
        return this.userMajor;
    }

    public ArrayList<String> setUserFavorite(ArrayList<String> userFavorite) {
        this.userFavorite = userFavorite;
        return this.userFavorite;
    }

    public ArrayList<String> getUserFavorite() {
        return this.userFavorite;
    }
}
