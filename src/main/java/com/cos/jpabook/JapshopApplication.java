package com.cos.jpabook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JapshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JapshopApplication.class, args);
		
		Hello hello = new Hello();
		hello.setData("실제 상황");
		String data = hello.getData();
		System.out.println("data : " + data);
		System.out.println("1");
	}

}
  