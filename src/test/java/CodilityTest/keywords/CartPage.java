package CodilityTest.keywords;

import org.openqa.selenium.WebDriver;

import CodilityTest.automationutils.GetPage;
import junit.framework.Assert;

public class CartPage extends GetPage{
	
	public CartPage(WebDriver driver) {
        super(driver, "CartPage");
    }
	
	public void verifyCartPageTitle(String title) {
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(element("h1_cartPageTitle").getText().equals(title));
		logMessage("Cart Page: '" + title + "' is appearing");
	}

	public void verifyItemInCart(String productName) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("a_itemInCart",productName);
		logMessage("Item: "+productName+" is appearing in the Cart");

	}
}
