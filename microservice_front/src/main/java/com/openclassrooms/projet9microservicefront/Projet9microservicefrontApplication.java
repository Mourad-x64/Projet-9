package com.openclassrooms.projet9microservicefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.openclassrooms.projet9microservicefront")
@SpringBootApplication
public class Projet9microservicefrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(Projet9microservicefrontApplication.class, args);
	}

}
