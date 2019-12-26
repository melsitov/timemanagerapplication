package com.timemanager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableJpaRepositories(basePackages = "com.timemanager")
@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.timemanager")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${driverClass}")
    private String driverClass;
    @Value("${database.user.name}")
    private String userName;
    @Value("${database.user.password}")
    private String userPassword;
    @Value("${show_sql}")
    private String showSql;
    @Value("${hibernate.hbm2ddl.auto}")
    String hbm2ddl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.driverClassName(driverClass);
        dataSource.url(jdbcUrl);
        dataSource.username(userName);
        dataSource.password(userPassword);
        return dataSource.build();
    }
}
