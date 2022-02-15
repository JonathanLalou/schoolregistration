package com.github.jonathan.schoolregistration.schoolregistrationparent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SchoolregistrationParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolregistrationParentApplication.class, args);
	}

}
