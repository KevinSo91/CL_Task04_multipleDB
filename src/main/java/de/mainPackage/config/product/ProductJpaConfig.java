package de.mainPackage.config.product;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = {"de.MainPackage.repository.product"})
public class ProductJpaConfig {

    @Primary
    @Bean(name = "productDataSourceProperties")
    @ConfigurationProperties("spring.datasource-product")
    public DataSourceProperties productDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "productDataSource")
    @ConfigurationProperties("spring.datasource-product.configuration")
    public DataSource productDataSource(@Qualifier("productDataSourceProperties") DataSourceProperties productDataSourceProperties) {
        return productDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(
            EntityManagerFactoryBuilder productEntityManagerFactoryBuilder, @Qualifier("productDataSource") DataSource productDataSource) {

        Map<String, String> productJpaProperties = new HashMap<>();
        productJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        productJpaProperties.put("hibernate.hbm2ddl.auto", "update");

        return productEntityManagerFactoryBuilder
                .dataSource(productDataSource)
                .packages("de.mainPackage.model.product")
                .persistenceUnit("productDataSource")
                .properties(productJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {

        return new JpaTransactionManager(productEntityManagerFactory);
    }
}