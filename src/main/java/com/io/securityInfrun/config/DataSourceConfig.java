package com.io.securityInfrun.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	@Bean(name="datasource")
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSource dsDemo() {
		return DataSourceBuilder.create().build();
	}
}
