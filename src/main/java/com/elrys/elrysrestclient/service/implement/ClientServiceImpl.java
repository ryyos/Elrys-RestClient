package com.elrys.elrysrestclient.service.implement;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.ExistsRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.elrys.elrysrestclient.configuration.UtilsConfiguration;
import com.elrys.elrysrestclient.model.DataModel;
import com.elrys.elrysrestclient.model.LoginModel;
import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ElasticsearchClient client;

    @Autowired
    UtilsConfiguration utils;


    @Override
    public IndexResponse postRequest(String index, String id, Object bodyRequest) throws Exception {

        IndexRequest<Object> request = switch (bodyRequest.getClass().getSimpleName()) {
            case "UserModel" -> {
                UserModel userModel = (UserModel) bodyRequest;
                yield new IndexRequest.Builder<>()
                        .index(index)
                        .id(id)
                        .document(new DataModel((userModel)))
                        .build();
            }

            case "LoginModel" -> {
                LoginModel loginModel = (LoginModel) bodyRequest;
                yield new IndexRequest.Builder<>()
                        .index(index)
                        .id(id)
                        .document(loginModel)
                        .build();
            }

            case "DataModel" -> {
                DataModel dataModel = (DataModel) bodyRequest;
                yield new IndexRequest.Builder<>()
                        .index(index)
                        .id(id)
                        .document(dataModel)
                        .build();
            }

            default -> throw new IllegalArgumentException("Invalid model type");

        };

        /**
         *  @return
         *  IndexResponse response = client.index(request);
         */

        return client.index(request);
    }

    @Override
    public Boolean existRequest(String index, Object bodyRequest) throws Exception {

        ExistsRequest request = switch (bodyRequest.getClass().getSimpleName()) {
            case "UserModel" -> {
                UserModel userModel = (UserModel) bodyRequest;
                yield new ExistsRequest.Builder()
                        .index(index)
                        .id(utils
                                .encode()
                                .idEncoder(userModel))
                        .refresh(true)
                        .build();
            }

            case "DataModel" -> {
                DataModel dataModel = (DataModel) bodyRequest;
                yield new ExistsRequest.Builder()
                        .index(index)
                        .id(utils
                                .encode()
                                .idEncoder(dataModel))
                        .refresh(true)
                        .build();
            }
            default -> throw new IllegalStateException("Unexpected value: " + bodyRequest.getClass().getSimpleName());
        };

        /**
         *  @return
         *  BooleanResponse exists = client.exists(request);
         */
        return client.exists(request).value();
    }

    @Override
    public DeleteResponse deleteRequest(String index, Object bodyRequest) throws Exception {
        return null;
    }
}
