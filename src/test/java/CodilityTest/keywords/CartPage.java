package CodilityTest.keywords;

import org.openqa.selenium.WebDriver;

import CodilityTest.automationutils.GetPage;

public class CartPage extends GetPage{
	
	public CartPage(WebDriver driver) {
        super(driver, "pages");
    }
	
	public void click() {
		System.out.println("Click");
	}
	

}
