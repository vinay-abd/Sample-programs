package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "start")
public class Controller
{

	@RequestMapping(value="/fetch")
	public String fetchData()
	{
		return "hello all....you are successfully done!!!!!!!!!!!!!!!!!!!!!";
	}
}
