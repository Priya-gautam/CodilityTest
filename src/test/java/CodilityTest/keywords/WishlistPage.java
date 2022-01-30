package CodilityTest.keywords;

import org.openqa.selenium.WebDriver;

import CodilityTest.automationutils.GetPage;

public class WishlistPage extends GetPage{
	
	public WishlistPage(WebDriver driver) {
        super(driver, "pages");
    }
	
	public void click() {
		System.out.println("Click");
	}
	

}
