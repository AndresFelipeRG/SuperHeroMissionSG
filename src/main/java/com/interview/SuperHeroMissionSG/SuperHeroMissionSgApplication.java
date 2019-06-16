package com.interview.SuperHeroMissionSG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages ="com.interview.SuperHeroMissionSG")
public class SuperHeroMissionSgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperHeroMissionSgApplication.class, args);
	}

}
