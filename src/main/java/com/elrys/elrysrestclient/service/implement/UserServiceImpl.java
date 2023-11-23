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
import com.elrys.elrysrestclient.response.DataResponse;
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
        String relations = utils.encode().idEncoder(userModel);

        if(client.existRequest(Index.MAIN.getIndex(), userModel)){
            return BodyResponse.<Object>builder()
                    .status("Failed")
                    .message("User Already Exist")
                    .build();
        }

        client.postRequest(
                Index.MAIN.getIndex(),
                relations,
                userModel);

        return BodyResponse.<Object>builder()
                .status("Success")
                .data(new DataResponse(userModel))
                .message("Register successfully")
                .build();
    }

    @Override
    public BodyResponse<Object> login(UserModel userModel) throws Exception {
        String relations = utils.encode().idEncoder(userModel);

        if(!client.existRequest(Index.MAIN.getIndex(), userModel)){
            return BodyResponse.<Object>builder()
                    .status("Failed")
                    .message("User Not Found")
                    .build();
        }

        client.postRequest(
                Index.LOG.getIndex(),
                String.valueOf(UUID.randomUUID()),
                new LoginModel(userModel, relations));

        return BodyResponse.<Object>builder()
                .status("Success")
                .data(new DataResponse(userModel))
                .message("Login successfully")
                .build();
    }

    @Override
    public BodyResponse<Object> update(DataModel dataModel) throws Exception {
        String relations = utils.encode().idEncoder(dataModel);

        if(!client.existRequest(Index.MAIN.getIndex(), dataModel)){
            return BodyResponse.<Object>builder()
                    .status("Failed")
                    .message("User Not Found")
                    .build();
        }

        client.postRequest(
                Index.MAIN.getIndex(),
                relations,
                dataModel);

        return BodyResponse.<Object>builder()
                .status("Success")
                .data(new DataResponse(dataModel))
                .message("Update successfully")
                .build();
    }


    @Override
    public BodyResponse<Object> delete(UserModel userModel) throws Exception {

        if(!client.existRequest(Index.MAIN.getIndex(), userModel)){
            return BodyResponse.<Object>builder()
                    .status("Failed")
                    .message("User Not Found")
                    .build();
        }

        client.deleteRequest(
                Index.MAIN.getIndex(),
                userModel);

        client.deleteByQueryRequest(
                Index.LOG.getIndex(),
                userModel
        );

        return BodyResponse.<Object>builder()
                .status("Success")
                .data(new DataResponse(userModel))
                .message("Delete successfully")
                .build();
    }

    @Override
    public BodyResponse<Object> findByEmail(String email) throws Exception {
        return null;
    }
}