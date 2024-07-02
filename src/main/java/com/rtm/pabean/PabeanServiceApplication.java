package com.rtm.pabean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
public class PabeanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PabeanServiceApplication.class, args);
	}

}
