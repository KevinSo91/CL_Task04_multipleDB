package de.mainPackage.config.order;

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
        entityManagerFactoryRef = "orderEntityManagerFactory",
        transactionManagerRef = "orderTransactionManager",
        basePackages = {"de.MainPackage.repository.order"})
public class OrderJpaConfig {

    @Primary
    @Bean(name = "orderDataSourceProperties")
    @ConfigurationProperties("spring.datasource-order")
    public DataSourceProperties orderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "orderDataSource")
    @ConfigurationProperties("spring.datasource-order.configuration")
    public DataSource orderDataSource(@Qualifier("orderDataSourceProperties") DataSourceProperties orderDataSourceProperties) {
        return orderDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "orderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(
            EntityManagerFactoryBuilder orderEntityManagerFactoryBuilder, @Qualifier("orderDataSource") DataSource orderDataSource) {

        Map<String, String> orderJpaProperties = new HashMap<>();
        orderJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        orderJpaProperties.put("hibernate.hbm2ddl.auto", "update");

        return orderEntityManagerFactoryBuilder
                .dataSource(orderDataSource)
                .packages("de.mainPackage.model.order")
                .persistenceUnit("orderDataSource")
                .properties(orderJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "orderTransactionManager")
    public PlatformTransactionManager orderTransactionManager(
            @Qualifier("orderEntityManagerFactory") EntityManagerFactory orderEntityManagerFactory) {

        return new JpaTransactionManager(orderEntityManagerFactory);
    }
}
