package com.iege.cryptocurrency.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("mongodb://admin:password@ds229388.mlab.com:29388");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "crypto_viewer");
    }
}
