package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public void demoFunc1(){
		System.out.println("----------------------demoFunc1----------------------------");
	}
	public void demoFunc2(){
		System.out.println("----------------------demoFunc2----------------------------");
	}

	public static void main(String[] args) {
		System.out.println("----------------------main----------------------------");
		SpringApplication.run(DemoApplication.class, args);
	}

}
