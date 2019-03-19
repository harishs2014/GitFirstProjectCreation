package tests;

import org.junit.Test;
import com.qspiders.frameworkengine.*;

public class CreateCustomer {

	@Test
	public void test() {
		System.out.println("Starting CreateCustomer scenario");
		TestExecutor executor = new TestExecutor();
		executor.executeTest(this.getClass().getSimpleName());
		System.out.println("Ending CreateCustomer scenario");
	}

}
