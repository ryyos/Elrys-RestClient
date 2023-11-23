package com.elrys.elrysrestclient.utils;

import com.elrys.elrysrestclient.model.DataModel;
import com.elrys.elrysrestclient.model.UserModel;
import org.apache.commons.codec.digest.DigestUtils;

public class Encode {
    public String idEncoder(UserModel userModel) {
        String dataToEncode = userModel.getEmail() + userModel.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

    public String idEncoder(DataModel userModel) {
        String dataToEncode = userModel.getEmail() + userModel.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

    public String idEncoder(String email, String password) {
        String dataToEncode = email + password;
        return DigestUtils.md5Hex(dataToEncode);
    }
}
