package com.dxc.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import th.co.toyota.sc2.client.offline.CSC22110OfflineFilter;

@Profile("OFFLINE")
@Configuration
public class OfflineWebConfig {
	@Bean
	FilterRegistrationBean offlineLoginFilterRegistration() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter standardOfflineFilter = new CSC22110OfflineFilter();
		frb.setFilter(standardOfflineFilter);
		frb.setName("SC2 Offline Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}
}
