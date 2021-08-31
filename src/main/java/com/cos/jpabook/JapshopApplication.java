package com.cos.jpabook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

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
		
		@Bean
		Hibernate5Module hibernate5Module() {
			Hibernate5Module hibernate5Module = new Hibernate5Module();
			//강제 지연 로딩 설정
//			hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
			return hibernate5Module;
		}
	

}
  