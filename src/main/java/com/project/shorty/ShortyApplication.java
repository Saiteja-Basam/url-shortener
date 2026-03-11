package com.project.shorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ShortyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortyApplication.class, args);
	}

}
