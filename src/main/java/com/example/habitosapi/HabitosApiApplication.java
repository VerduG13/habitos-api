package com.example.habitosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class HabitosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitosApiApplication.class, args);
	}

}
