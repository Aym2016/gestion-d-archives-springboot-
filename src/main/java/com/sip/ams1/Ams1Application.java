package com.sip.ams1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.sip.ams1.controllers.ArticleController;

import java.io.File;

@SpringBootApplication
public class Ams1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ams1Application.class, args);
		//new File(ArticleController.uploadDirectory).mkdir();
		//SpringApplication.run(Ams1Application.class, args);

	}

}
