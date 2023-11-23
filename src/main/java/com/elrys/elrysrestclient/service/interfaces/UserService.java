package com.elrys.elrysrestclient.service.interfaces;

import com.elrys.elrysrestclient.model.DataModel;
import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.response.BodyResponse;

public interface UserService {
    BodyResponse<Object> register(UserModel userModel) throws Exception;
    BodyResponse<Object> login(UserModel userModel) throws Exception;
    BodyResponse<Object> update(DataModel dataModel) throws Exception;
    BodyResponse<Object> delete(UserModel userModel) throws Exception;
    BodyResponse<Object> findByEmail(String email) throws Exception;
}
