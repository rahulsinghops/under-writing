package com.ipru.mca.underwriting.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ipru.mca.underwriting.entity.BikeDetails;
import com.ipru.mca.underwriting.entity.UnderWriting;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "bikeEntityManagerFactory", transactionManagerRef = "bikeTransactionManager", basePackages = {
"com.ipru.mca.underwriting.dao" })
@RefreshScope
public class UWDbConfiguration {
	@Primary
	@Bean(name = "bikeDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource getLoggingDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "bikeEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("bikeDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages(BikeDetails.class,UnderWriting.class).persistenceUnit("db1")
				.build();
	}

	@Primary
	@Bean(name = "bikeTransactionManager")
	public PlatformTransactionManager customerTransactionManager(
			@Qualifier("bikeEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
		return new JpaTransactionManager(customerEntityManagerFactory);
	}

}
