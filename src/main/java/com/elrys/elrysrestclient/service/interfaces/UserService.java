package com.elrys.elrysrestclient.service.interfaces;

import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.response.BodyResponse;

public interface UserService {
    BodyResponse register(UserModel userModel) throws Exception;
    BodyResponse login(UserModel userModel) throws Exception;
}
