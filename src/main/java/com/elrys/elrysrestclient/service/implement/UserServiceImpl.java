package com.elrys.elrysrestclient.service.implement;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.response.BodyResponse;
import com.elrys.elrysrestclient.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {



    @Override
    public BodyResponse register(UserModel userModel) throws Exception {

        IndexRequest request 
                = new IndexRequest.Builder<>()
                .index(index)
                .id(UUID.randomUUID().toString())
                .document(userModel)
                .build();
        IndexResponse response = client.index(request);
        System.out.println(response.toString());
        return null;
    }

    @Override
    public BodyResponse login(UserModel userModel) throws Exception {
        return null;
    }
}

@Component
class ApiRequest{

    @Autowired
    ElasticsearchClient client;

    @Value("${service.elastic.index}")
    private String index;

    public IndexResponse postRequest(UserModel userModel) throws IOException {
        IndexRequest<Object> request
                = new IndexRequest.Builder<>()
                .index(index)
                .id(UUID.randomUUID().toString())
                .document(userModel)
                .build();
        return client.index(request);
        /*IndexResponse response = client.index(request);*/
    }
}