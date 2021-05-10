package com.data.covid19.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;
import lombok.Setter;


/**
 * @EnableTransactionManagement tells the spring framework that clasees with @Transactional annotation should be wrapped with Transactionnal Aspect
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySources({
    @PropertySource("classpath:/hikari.properties"),
    @PropertySource("classpath:/springJPA.properties")
})
public class JPAConfig {
	
	    @Setter
	    @Getter
	    @Value("${spring.datasource.hikari.username}")
	    String username;
	    
	    @Setter
	    @Getter
	    @Value("${spring.datasource.hikari.password}")
	    private String password;
	    
	    @Setter
	    @Getter
	    @Value("${spring.datasource.hikari.data-source-class-name}")
	    private String className;
	    
	    @Setter
	    @Getter
	    @Value("${spring.datasource.hikari.jdbc-url}")
	    private String jdbcURL;
	    
	    @Setter
	    @Getter
	    @Value("${spring.datasource.hikari.ipTypes}")
	    private String ipTypes;
	    
	    
	    @Setter
		@Getter
	    @Value("${spring.datasource.hikari.data-source-properties.socketFactory}")
		private String socketFactory;
		 
		 @Setter
		 @Getter
		 @Value("${spring.datasource.hikari.data-source-properties.cloudSqlInstance}")
		 private String cloudSqlInstance;
		 
	 
	  @Bean
	  public LocalContainerEntityManagerFactoryBean sessionFactory() {
		  LocalContainerEntityManagerFactoryBean entityManagerFactoty = new LocalContainerEntityManagerFactoryBean();
		  HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		  
	        entityManagerFactoty.setDataSource(dataSource());
	        entityManagerFactoty.setPackagesToScan("com.data.covid19");
	        entityManagerFactoty.setJpaVendorAdapter(jpaVendorAdapter);
	        return entityManagerFactoty;
	    }
	  
	  @Bean
	  /** The Java configuration of the database connection **/
	  public DataSource dataSource() {	      
	   // Note: For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections
	   // which is preferred to using the Cloud SQL Proxy with Unix sockets.
	   // The configuration object specifies behaviors for the connection pool.
	   HikariConfig config = new HikariConfig();

	   // The following URL is equivalent to setting the config options below:
	   // jdbc:mysql:///<DB_NAME>?cloudSqlInstance=<CLOUD_SQL_CONNECTION_NAME>&
	   // socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=<DB_USER>&password=<DB_PASS>
	   // See the link below for more info on building a JDBC URL for the Cloud SQL JDBC Socket Factory
	   // https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory#creating-the-jdbc-url

	   // Configure which instance and what database user to connect with.
	   config.setJdbcUrl(getJdbcURL());
	   config.setUsername(getUsername()); // e.g. "root", "mysql"
	   config.setPassword(getPassword()); // e.g. "my-password"
       config.setDriverClassName(getClassName());
	   config.addDataSourceProperty("socketFactory", getSocketFactory());
	   config.addDataSourceProperty("cloudSqlInstance", getCloudSqlInstance());
	   //config.addDataSourceProperty("databaseName", "test");
	   // The ipTypes argument can be used to specify a comma delimited list of preferred IP types 
	   // for connecting to a Cloud SQL instance. The argument ipTypes=PRIVATE will force the 
	   // SocketFactory to connect with an instance's associated private IP. 
	   config.addDataSourceProperty("ipTypes", getIpTypes());

	   // ... Specify additional connection properties here.
	   // ...

	   // Initialize the connection pool using the configuration object.
	   DataSource pool = new HikariDataSource(config);
	   return pool;
	  }
	  
	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		    return jpaTransactionManager;
	  }
}
