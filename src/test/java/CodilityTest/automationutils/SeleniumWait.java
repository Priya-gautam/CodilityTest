package CodilityTest.automationutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait {

	WebDriver driver;
	WebDriverWait wait;

	int timeout;

	public SeleniumWait(WebDriver driver, int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout;
	}

}
