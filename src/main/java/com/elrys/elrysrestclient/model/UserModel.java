package com.elrys.elrysrestclient.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserModel {
    private String email;
    private String password;
}
