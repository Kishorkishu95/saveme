package com.kishor.saveme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.kishor.saveme.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SavemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavemeApplication.class, args);
	}

}
