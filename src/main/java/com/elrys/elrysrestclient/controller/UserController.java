package com.elrys.elrysrestclient.controller;

import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v3")
@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/register")
    public void register(@RequestBody UserModel userModel) throws Exception {
        service.register(userModel);
    }
}
