package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Test
	void mainTest(){
		DemoApplication demoApplication = new DemoApplication();
		demoApplication.demoFunc1();
		demoApplication.demoFunc2();
	}

}
