package com.openclassrooms.projet9microserviceassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.openclassrooms.projet9microserviceassessment")
@SpringBootApplication
public class Projet9microserviceassessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(Projet9microserviceassessmentApplication.class, args);
	}

}
