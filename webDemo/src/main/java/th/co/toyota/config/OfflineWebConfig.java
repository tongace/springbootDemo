package th.co.toyota.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import th.co.toyota.sc2.client.offline.CSC22110OfflineFilter;

@Profile(value="OFFLINE")
@Configuration
public class OfflineWebConfig {
	@Bean
	FilterRegistrationBean offlineLoginFilterRegistration() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new CSC22110OfflineFilter());
		frb.setName("SC2 Offline Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}
}
