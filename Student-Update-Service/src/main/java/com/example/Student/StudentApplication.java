package com.example.Student;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableFeignClients
public class StudentApplication {


	//Bean to make H2 db share by microservices
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseServer() throws SQLException {
		return Server.createTcpServer(
				"-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}

	//RestTemplate Bean
	@Bean
	@LoadBalanced
	public RestTemplate createRestTemplate(){
		return new RestTemplate();
	}


	//WebClient Bean
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}


	//Cache Manager
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache cache = new ConcurrentMapCache("Student");
		cacheManager.setCaches(Arrays.asList(cache));
		return cacheManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);

	}

}
