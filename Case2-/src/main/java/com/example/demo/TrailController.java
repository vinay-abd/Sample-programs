package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* @RequestMapping(value = "/test") */
public class TrailController
{
	@RequestMapping(value = "/welcome")
	public String welcome()
	{
		return "welcome to an application one two three four ----5-------6----7------7-----9----9-";
	}

}
