package com.vyapaar.vyapaarservice;

import com.vyapaar.vyapaarservice.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class VyapaarserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VyapaarserviceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<JwtFilter> registerFilterBean() {
		FilterRegistrationBean<JwtFilter> jwtFilterBean = new FilterRegistrationBean<>();
		jwtFilterBean.setFilter(new JwtFilter());
		jwtFilterBean.addUrlPatterns("/vyapaar/user/*");
		return jwtFilterBean;
	}
}
