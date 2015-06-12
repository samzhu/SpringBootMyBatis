package com.sam.app;

import javax.servlet.Filter;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.filter.CharacterEncodingFilter;
import com.zaxxer.hikari.*;

@Configuration
@MapperScan("com.sam.modules.dao")
//@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true), @PropertySource(value = "file:./application.properties", ignoreResourceNotFound = true) })
public class AppConfig {
	
	@Value("${name:}")
    private String name;
	
	// 用於處理編碼問題
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	//HikariDataSourceConfiguration 有這種神奇的東西再來研究一下
	@Bean(destroyMethod = "close")
	public DataSource dataSource(){
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("org.postgresql.Driver");
		hikariConfig.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/test?charset=UTF8"); 
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("123456");

		hikariConfig.setPoolName("springHikariCP");
		//實驗結果是要把它關掉，不然事務控制會無效
		hikariConfig.setAutoCommit(false);
		
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");
		  
		hikariConfig.setMinimumIdle(20);
		hikariConfig.setMaximumPoolSize(20);
		hikariConfig.setConnectionInitSql("SELECT 1");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
}