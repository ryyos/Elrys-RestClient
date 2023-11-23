package com.elrys.elrysrestclient.service.interfaces;

import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.elrys.elrysrestclient.model.UserModel;

import javax.annotation.Nullable;
import java.util.Optional;

public interface ClientService {

    IndexResponse postRequest(String index, String id, Object bodyRequest) throws Exception;

    Boolean existRequest(String index, Object bodyRequest) throws Exception;
    DeleteResponse deleteRequest(String index, Object bodyRequest) throws Exception;

}
