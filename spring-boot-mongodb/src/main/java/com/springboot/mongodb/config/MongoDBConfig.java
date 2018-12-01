package com.springboot.mongodb.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;


/**
 * @author: Lucifer
 * @Date: 2018/7/10 18:14
 * @Description:
 */
@Configuration
@PropertySource(value = "classpath:mongo/mongo.properties")
public class MongoDBConfig{

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

    //@Bean
    public MongoDbFactory mongoDbFactory() {
        String uriStr = "mongodb://" + userName + ":" + password + "@" + uri + ":" + port + "/" + databaseName;

        MongoClientURI mongoClientURI = new MongoClientURI(uriStr);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);
        return mongoDbFactory;
    }

    //@Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory());
    }
}
