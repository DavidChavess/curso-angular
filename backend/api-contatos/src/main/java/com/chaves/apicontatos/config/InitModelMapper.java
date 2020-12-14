package com.chaves.apicontatos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitModelMapper {

    @Bean
    public ModelMapper init(){
        return new ModelMapper();
    }
}
