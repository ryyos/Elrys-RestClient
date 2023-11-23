package com.elrys.elrysrestclient.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginModel {

    private String id;
    private String email;
    private String timestamp;

    public LoginModel(UserModel userModel, String id) {
        this.id = id;
        this.email = userModel.getEmail();
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));

    }
}