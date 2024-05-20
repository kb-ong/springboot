package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public void demoFunc1(){
		int total=0;
		for(int i=1;i<=10;i++){
			total+=i;
		}
	}
	public void demoFunc2(){
		int total=0;
		for(int i=1;i<=10;i++){
			total+=i;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
