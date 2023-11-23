package com.elrys.elrysrestclient.configuration;

import com.elrys.elrysrestclient.model.UserModel;
import com.elrys.elrysrestclient.utils.Encode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfiguration{

    @Bean
    public Encode encode(){
        return new Encode();
    }

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }
}
