package com.hcl.bpm.dcworks.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.hcl.bpm.dcworks.*"})
@EnableTransactionManagement
public class JPAConfig {

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/DCWORKSLIVE");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("poet");
	    return driverManagerDataSource;
	}
	
	 @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.hcl.bpm.dcworks.entity" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	 
	 @Bean
	    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory);
	        return transactionManager;
	    } 
	 
	 Properties additionalProperties() {
		    Properties properties = new Properties();
		    properties.setProperty("hibernate.hbm2ddl.auto", "update");
		    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		    properties.setProperty("hibernate.use-new-id-generator-mappings","true");
		    properties.setProperty("hibernate.show_sql","true");
	    	
		    
		    //properties.setProperty("hibernate.event.merge.entity_copy_observer", "allow");
		        
		    return properties;
		}
}
