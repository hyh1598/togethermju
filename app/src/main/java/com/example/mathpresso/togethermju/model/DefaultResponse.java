package com.example.mathpresso.togethermju.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sonjiho on 2016. 12. 1..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultResponse {
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
