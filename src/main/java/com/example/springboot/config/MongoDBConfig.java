package com.example.springboot.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;


/**
 * @author: lingjun.jlj
 * @Date: 2018/7/10 18:14
 * @Description:
 */
@Configuration
public class MongoDBConfig {

    @Value("${mongo.database}")
    private String databaseName;

    @Value("${mongo.host}")
    private String uri;

    @Value("${mongo.username}")
    private String userName;

    @Value("${mongo.password}")
    private String password;

    @Value("${mongo.port}")
    private String port;

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        String uriStr = "mongodb://" + userName + ":" + password + "@" + uri + ":" + port + "/" + databaseName;
        System.out.println(uriStr);
        MongoClientURI mongoClientURI = new MongoClientURI(uriStr);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);
        return mongoDbFactory;
    }
}
