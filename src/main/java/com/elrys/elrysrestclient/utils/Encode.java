package com.elrys.elrysrestclient.utils;

import com.elrys.elrysrestclient.model.UserModel;
import org.apache.commons.codec.digest.DigestUtils;

public class Encode {
    public String idEncoder(UserModel userModel) {
        String dataToEncode = userModel.getEmail() + userModel.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }
}
