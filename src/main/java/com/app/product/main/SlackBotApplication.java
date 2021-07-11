package com.app.product.main;

import com.app.product.main.common.ArrayTests;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SlackBotApplication {

	public static void main (String[] args) {
		//ArrayTests xx = new ArrayTests();
		SpringApplication.run(SlackBotApplication.class, args);
	}

}
