package com.github.adetiamarhadi.springcloudribbontimeapp;

import com.github.adetiamarhadi.springcloudribbontimeapp.config.RibbonTimeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RibbonClient(name = "time-service", configuration = RibbonTimeConfig.class)
@RestController
public class SpringCloudRibbonTimeAppApplication {

	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRibbonTimeAppApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@GetMapping
	public String getTime() {
		return this.restTemplate.getForEntity("http://time-service", String.class).getBody();
	}

}
