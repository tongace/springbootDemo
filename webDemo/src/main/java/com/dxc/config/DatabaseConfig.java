package com.dxc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:data-access.properties" })
public class DatabaseConfig {
	
}
