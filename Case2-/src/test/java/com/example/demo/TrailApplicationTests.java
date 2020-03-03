package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrailApplicationTests {

	@Test
	void contextLoads() 
	{
		
	}
	
	@Test
	public void returningTrue()
	{
		int a=1;
		
		int b=2;
		assertTrue(a+b==3);
		
		
	}
	
	@Test
	public void returningOneMoreTrue()
	{
		int a=4;
		
		int b=6;
		assertTrue(a+b==10);
		
		
	}

}
