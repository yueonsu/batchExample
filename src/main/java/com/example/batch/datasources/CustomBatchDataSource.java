package com.example.batch.datasources;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.batch.batch",
        entityManagerFactoryRef = "configEntityManager",
        transactionManagerRef = "configTransactionManager"
)
public class CustomBatchDataSource {

    @Bean
    @BatchDataSource
    @ConfigurationProperties(prefix = "spring.datasource-batch")
    public DataSource configDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean configEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(configDataSource());
        em.setPackagesToScan("com.example.batch.model.batch");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> prop = new HashMap<>();
        prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        prop.put("hibernate.hbm2ddl.auto", "create");
        prop.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(prop);

        return em;
    }

    @Bean
    @BatchTransactionManager
    public PlatformTransactionManager configTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(configEntityManager().getObject());
        return transactionManager;
    }
}
