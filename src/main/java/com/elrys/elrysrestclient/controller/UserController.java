package com.elrys.elrysrestclient.controller;

import com.elrys.elrysrestclient.model.DataModel;
import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.response.BodyResponse;
import com.elrys.elrysrestclient.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<BodyResponse<Object>> login(@RequestBody UserModel userModel) throws Exception {
        BodyResponse<Object> response = service.login(userModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<BodyResponse<Object>> login(@RequestBody DataModel dataModel) throws Exception {
        BodyResponse<Object> response = service.update(dataModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BodyResponse<Object>> delete(@RequestBody UserModel userModel) throws Exception {
        BodyResponse<Object> response = service.delete(userModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);
    }
}
