package com.elrys.elrysrestclient.response;

import com.elrys.elrysrestclient.model.DataModel;
import com.elrys.elrysrestclient.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private String email;
    private String times;

    public DataResponse(UserModel userModel){
        this.email = userModel.getEmail();
        this.times =  String.valueOf(new Timestamp(System.currentTimeMillis()));
    }
    public DataResponse(DataModel dataModel){
        this.email = dataModel.getEmail();
        this.times =  String.valueOf(new Timestamp(System.currentTimeMillis()));
    }
}
