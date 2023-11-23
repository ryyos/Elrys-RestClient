package com.elrys.elrysrestclient.service.implement;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.ExistsRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;

import com.elrys.elrysrestclient.configuration.UtilsConfiguration;
import com.elrys.elrysrestclient.enums.Index;
import com.elrys.elrysrestclient.model.DataModel;
import com.elrys.elrysrestclient.model.LoginModel;
import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.response.BodyResponse;
import com.elrys.elrysrestclient.service.interfaces.ClientService;
import com.elrys.elrysrestclient.service.interfaces.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ClientService client;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UtilsConfiguration utils;

    @Override
    public BodyResponse<Object> register(UserModel userModel) throws Exception {

        if(client.existRequest(Index.MAIN.getIndex(), userModel)){
            return BodyResponse.<Object>builder()
                    .status("Failed")
                    .message("User Already Exist")
                    .build();
        }

        return BodyResponse.<Object>builder()
                .status("Success")
                .data(client.postRequest(
                        Index.MAIN.getIndex(),
                        utils.encode().idEncoder(userModel) ,
                        userModel))
                .message("Register successfully")
                .build();
    }

    @Override
    public BodyResponse<Object> login(UserModel userModel) throws Exception {
        String idrelations = utils.encode().idEncoder(userModel);

        if(!client.existRequest(Index.MAIN.getIndex(), userModel)){
            return BodyResponse.<Object>builder()
                    .status("Failed")
                    .message("User Not Found")
                    .build();
        }

        return BodyResponse.<Object>builder()
                .status("Success")
                .data(client.postRequest(
                        Index.LOG.getIndex(),
                        idrelations,
                        new LoginModel(userModel, idrelations)))
                .message("Login successfully")
                .build();
    }

    @Override
    public BodyResponse<Object> update(DataModel dataModel) throws Exception {
        return null;
    }

    @Override
    public BodyResponse<Object> delete(UserModel userModel) throws Exception {
        return null;
    }
}