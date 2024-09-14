package com.example.pro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(){
		return runner->{
			//createCustomer(customerDAO);
			System.out.println("DONE");
		};
	}

}
