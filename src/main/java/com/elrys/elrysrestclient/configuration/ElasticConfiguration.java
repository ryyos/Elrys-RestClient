package com.elrys.elrysrestclient.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfiguration {

    @Value("${service.elastic.host}")
    private String host;

    @Bean
    ElasticsearchClient client(){
        return new ElasticsearchClient(transport());
    }

    public RestClient restClient(){
        return RestClient
                .builder(HttpHost.create(host))
                .build();
    }

    public ElasticsearchTransport transport(){
         return new RestClientTransport(restClient(), new JacksonJsonpMapper());

    }




}
