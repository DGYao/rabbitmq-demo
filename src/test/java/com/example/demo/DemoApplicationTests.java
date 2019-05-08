package com.example.demo;

import com.example.demo.redirct.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private Sender sender;
	@Test
	public void hello() throws Exception {
		for (int i=0;i<10;i++) {
			sender.send(i);
		}
	}

}
