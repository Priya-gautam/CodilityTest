package CodilityTest.keywords;

import org.openqa.selenium.WebDriver;

import CodilityTest.automationutils.GetPage;
import junit.framework.Assert;

public class HomePage extends GetPage {

	public HomePage(WebDriver driver) {
		super(driver, "HomePage");
	}

	public void verifyHomePageTitle(String title) {
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(getPageTitle().equals(title));
		logMessage("Home Page: '" + title + "' is appearing");
	}

	public void clickOnShopOptionFromTopMenu() {
		wait.waitForElementToBeVisible(element("a_shopMenuOption"));
		clickOnElement(element("a_shopMenuOption"));
		logMessage("User click on 'Shop' menu option");
	}
	
	public void clickOnAllCookies() {
		wait.waitForElementToBeVisible(element("a_accptAllCookies"));
		clickOnElement(element("a_accptAllCookies"));
		logMessage("User click 'All Cookies' button");
	}
	
	public void clickOnWishlistIcon() {
		wait.waitForElementToBeVisible(element("div_wishListIcon"));
		clickOnElement(element("div_wishListIcon"));
		logMessage("User click 'Wishlist' icon");
	}
	
	public void clickOnCartIcon() {
		wait.waitForElementToBeVisible(element("div_cartIcon"));
		clickOnElement(element("div_cartIcon"));
		logMessage("User click 'Cart' icon");
	}
	
}
