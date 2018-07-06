package com.dxc.config;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.http.HttpSessionListener;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.google.common.collect.Maps;

import th.co.toyota.sc2.client.web.filter.CSC2110CasSingleSignOutFilter;
import th.co.toyota.sc2.client.web.filter.CSC22110AuthenticationFilter;
import th.co.toyota.sc2.client.web.filter.CSC22110AuthorizationFilter;
import th.co.toyota.sc2.client.web.filter.CSC22110CasAuthenticationFilter;

@Profile("!OFFLINE")
@Configuration
@PropertySource({ "classpath:sc2-online-filter.properties" })
public class OnlineWebConfig extends SpringBootServletInitializer {

	@Value("${casServerUrlPrefix}")
	private String casServerUrlPrefix;
	@Value("${casServerLoginUrl}")
	private String casServerLoginUrl;
	@Value("${serverName}")
	private String serverName;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebDemoApplication.class);
	}

	@Bean
	public HttpSessionListener singleSignOutHttpSessionListener() {
		return new SingleSignOutHttpSessionListener();
	}

	@Bean
	FilterRegistrationBean casSingleSignOutFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter filter = new CSC2110CasSingleSignOutFilter();
		frb.setFilter(filter);
		frb.setName("CAS Single Sign Out Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}

	@Bean
	FilterRegistrationBean sCS22110AuthenticationFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter filter = new CSC22110AuthenticationFilter();
		frb.setFilter(filter);
		frb.setName("SC2 Authentication Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}

	@Bean
	FilterRegistrationBean cSC22110CasAuthenticationFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter filter = new CSC22110CasAuthenticationFilter();
		Map<String, String> filterInitParam = Maps.newHashMap();
		filterInitParam.put("casServerUrlPrefix", casServerUrlPrefix);
		filterInitParam.put("serverName", serverName);
		filterInitParam.put("casServerLoginUrl", casServerLoginUrl);
		frb.setInitParameters(filterInitParam);
		frb.setFilter(filter);
		frb.setName("SC2-CAS Authentication Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}

	@Bean
	FilterRegistrationBean cas20ProxyReceivingTicketValidationFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter filter = new Cas20ProxyReceivingTicketValidationFilter();
		Map<String, String> filterInitParam = Maps.newHashMap();
		filterInitParam.put("casServerUrlPrefix", casServerUrlPrefix);
		filterInitParam.put("serverName", serverName);
		filterInitParam.put("casServerLoginUrl", casServerLoginUrl);
		frb.setInitParameters(filterInitParam);
		frb.setFilter(filter);
		frb.setName("CAS Ticket Validation Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}

	@Bean
	FilterRegistrationBean httpServletRequestWrapperFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter filter = new HttpServletRequestWrapperFilter();
		frb.setFilter(filter);
		frb.setName("CAS HttpServletRequest Wrapper Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}

	@Bean
	FilterRegistrationBean cSC22110AuthorizationFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter filter = new CSC22110AuthorizationFilter();
		frb.setFilter(filter);
		frb.setName("SC2 Authorization Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}
}
