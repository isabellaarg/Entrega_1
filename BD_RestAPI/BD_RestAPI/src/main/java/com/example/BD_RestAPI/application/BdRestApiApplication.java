package com.example.BD_RestAPI.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableMongoRepositories("com.example.BD_RestAPI.repository")
public class BdRestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BdRestApiApplication.class, args);
	}

}
