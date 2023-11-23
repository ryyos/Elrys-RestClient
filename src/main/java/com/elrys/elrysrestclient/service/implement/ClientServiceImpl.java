package com.elrys.elrysrestclient.service.implement;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryFieldBuilders;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.termvectors.Term;
import com.elrys.elrysrestclient.configuration.UtilsConfiguration;
import com.elrys.elrysrestclient.enums.Index;
import com.elrys.elrysrestclient.model.DataModel;
import com.elrys.elrysrestclient.model.LoginModel;
import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.Query;
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
    public void postRequest(String index, String id, Object bodyRequest) throws Exception {

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
        client.index(request);
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
    public void deleteRequest(String index, Object bodyRequest) throws Exception {
        UserModel userModel = (UserModel) bodyRequest;
        DeleteRequest deleteRequest = new DeleteRequest.Builder()
                .index(index)
                .id(utils
                        .encode()
                        .idEncoder(userModel))
                .build();

        client.delete(deleteRequest);
    }

    @Override
    public void deleteByQueryRequest(String index, Object bodyRequest) throws Exception {
        UserModel userModel = (UserModel) bodyRequest;
        String encodeId = utils
                .encode()
                .idEncoder(userModel);


        DeleteByQueryRequest delete = new DeleteByQueryRequest.Builder()
                .index(index)
                .query(query -> query
                        .term(term -> term
                                .field("id.keyword")
                                .value(encodeId)
                        )
                )
                .build();

        client.deleteByQuery(delete);

    }
}
