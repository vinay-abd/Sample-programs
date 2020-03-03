package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TrailApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		
		SpringApplication.run(TrailApplication.class, args);
		System.out.println("Application is started......");
		System.out.println("******************************************");
		System.out.println("******************************************");

	}
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(TrailApplication.class);
	    }

}
