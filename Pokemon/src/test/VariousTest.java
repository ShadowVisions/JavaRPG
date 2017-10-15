package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Main;

public class VariousTest {

	@Test
	public void test() {
		
		int x = Main.fibo(300000000);
		assertEquals(31, x);
	}

}
