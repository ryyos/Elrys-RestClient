package com.elrys.elrysrestclient.service.implement;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.ExistsRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.elrys.elrysrestclient.configuration.UtilsConfiguration;
import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.response.BodyResponse;
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
    ApiClient client;

    @Override
    public BodyResponse<Object> register(UserModel userModel) throws Exception {

        if(client.existsRequest(userModel)){
            return BodyResponse.<Object>builder()
                    .status("Failed")
                    .message("User Already Exist")
                    .build();
        }

        return BodyResponse.<Object>builder()
                .status("Success")
                .data(client.postRequest(userModel))
                .message("")
                .build();
    }

    @Override
    public BodyResponse login(UserModel userModel) throws Exception {
        return null;
    }
}

@Component
class ApiClient{

    @Autowired
    ElasticsearchClient client;

    @Autowired
    UtilsConfiguration utils;

    @Value("${service.elastic.index}")
    private String index;

    public IndexResponse postRequest(Object bodyRequest) throws IOException {
        IndexRequest<Object> request = new IndexRequest.Builder<>()
                .index(index)
                .id(utils
                        .encode()
                        .idEncoder((UserModel) bodyRequest))
                .document(bodyRequest)
                .build();
        return client.index(request);
        /**
         *  @return
         *  IndexResponse response = client.index(request);;
         */
    }

    public Boolean existsRequest(Object bodyRequest) throws IOException {
       ExistsRequest request = new ExistsRequest.Builder()
               .index(index)
               .id(utils
                       .encode()
                       .idEncoder((UserModel) bodyRequest))
               .refresh(true)
               .build();

        return client.exists(request).value();
        /**
         *  @return
         *  BooleanResponse exists = client.exists(request);
         */
    }


}