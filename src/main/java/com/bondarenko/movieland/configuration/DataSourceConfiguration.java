package com.bondarenko.movieland.configuration;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource(@Value("${db.password}") String password,
                                 @Value("${db.username}") String user,
                                 @Value("${db.url}") String url) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}