package com.sedost;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.sedost.mapper")
public class SedostApplication {
	
	public static Logger logger = Logger.getLogger(SedostApplication.class);
	
	/**
	 * DataSource配置
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}
	
	/**
	 * 提供SqlSeesion
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory salSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	/**
	 * Main Start
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SedostApplication.class, args);
	}
}
