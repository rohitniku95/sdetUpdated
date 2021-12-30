package practice;

import org.testng.annotations.Test;

public class TestNgPracticeTest {
	
	@Test
	public void createCustomerTest() {
		System.out.println("Customer created");
	}
	@Test
	public void modifyCustomerTest() {
		System.out.println("Customer modified");
	}
	@Test
	public void deleteCustomerTest() {
		System.out.println("Customer deleted");
	}
}