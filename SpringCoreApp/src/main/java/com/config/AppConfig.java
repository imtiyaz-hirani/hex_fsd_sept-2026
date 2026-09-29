package com.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.Clock;

@Configuration
@ComponentScan(basePackages = "com.*")
public class AppConfig {

    @Bean
    public Clock configureClock(){
        return Clock.systemUTC();
    }

    @Bean
    public DataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/fsd_hex_sept_2026");
        config.setUsername("root");
        config.setPassword("deepcoder");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
         return new JdbcTemplate(dataSource);
    }

}
