package com.dxc.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource(value = { "classpath:data-access.properties" })
@ImportResource(value = { "classpath:app-jdbc-datasource.xml","classpath:batch-jdbc-datasource.xml" })
public class DatabaseConfig {
}
