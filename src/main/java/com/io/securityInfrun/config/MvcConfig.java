package com.io.securityInfrun.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@MapperScan(basePackages = "com.io.securityInfrun.web.*.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MvcConfig implements WebMvcConfigurer{

	@Autowired
	private DataSource dataSource;
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/sqlmaps/**/*_SQL.xml");
		Resource cres = new PathMatchingResourcePatternResolver().getResource("classpath:/sqlmaps/sql-mapper-config.xml");
		sqlSessionFactoryBean.setMapperLocations(res); // 기존 res[0]
		sqlSessionFactoryBean.setConfigLocation(cres);
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
    public SqlSessionTemplate mainSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
	return new SqlSessionTemplate(sqlSessionFactory);
}
    

}
