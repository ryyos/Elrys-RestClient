package com.elrys.elrysrestclient.controller;

import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.response.BodyResponse;
import com.elrys.elrysrestclient.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BodyResponse<Object>> register(@RequestBody UserModel userModel) throws Exception {
        BodyResponse<Object> response = service.register(userModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
