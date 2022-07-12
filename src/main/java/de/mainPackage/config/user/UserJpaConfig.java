package de.mainPackage.config.user;

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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager",
        basePackages = {"de.MainPackage.repository.user"})
public class UserJpaConfig {

    @Bean(name = "userDataSourceProperties")
    @ConfigurationProperties("spring.datasource-user")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "userDataSource")
    @ConfigurationProperties("spring.datasource-user.configuration")
    public DataSource userDataSource(@Qualifier("userDataSourceProperties") DataSourceProperties userDataSourceProperties) {
        return userDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
            EntityManagerFactoryBuilder userEntityManagerFactoryBuilder, @Qualifier("userDataSource") DataSource userDataSource) {

        Map<String, String> userJpaProperties = new HashMap<>();
        userJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        userJpaProperties.put("hibernate.hbm2ddl.auto", "update");

        return userEntityManagerFactoryBuilder
                .dataSource(userDataSource)
                .packages("de.mainPackage.model.user")
                .persistenceUnit("userDataSource")
                .properties(userJpaProperties)
                .build();
    }

    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {

        return new JpaTransactionManager(userEntityManagerFactory);
    }
}