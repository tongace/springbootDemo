package th.co.toyota.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import th.co.toyota.sc2.client.offline.CSC22110OfflineFilter;

import javax.servlet.Filter;

@Profile(value="OFFLINE")
@Configuration
public class OfflineWebConfig {
	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	@Bean
	FilterRegistrationBean offlineLoginFilterRegistration() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		Filter standardOfflineFilter = new CSC22110OfflineFilter();
		beanFactory.autowireBean(standardOfflineFilter);
		frb.setFilter(standardOfflineFilter);
		frb.setName("SC2 Offline Filter");
		frb.setUrlPatterns(Arrays.asList("/*"));
		return frb;
	}
}
