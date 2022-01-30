package CodilityTest.keywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CodilityTest.automationutils.GetPage;
import junit.framework.Assert;

public class ShopPage extends GetPage {

	public ShopPage(WebDriver driver) {
		super(driver, "ShopPage");
	}

	public void verifyShopPageTitle(String title) {
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(element("h1_shopPageTitle").getText().equals(title));
		logMessage("Shop Page: '" + title + "' is appearing");
	}

	public ArrayList<String> getListOfFourProducts() {
		ArrayList<String> listOf4DifferentProducts = new ArrayList<>();
		int count = 1;
		for (WebElement item : elements("h2_listOfItems")) {
			if (count < 5) {
				listOf4DifferentProducts.add(item.getText());
				count++;
			}
		}
		return listOf4DifferentProducts;
	}

	public Map<String, String> getPriceOfProductsInTheList(String... listOfProducts) {
		Map<String, String> mapOfProductPrice = new HashMap<String, String>();
		for (String productName : listOfProducts) {
			mapOfProductPrice.put(productName, element("bdi_itemPrice", productName).getText());
		}
		return mapOfProductPrice;
	}

	public void addProductsIntoWishlist(String... listOfProducts) {
		for (String productName : listOfProducts) {
			isElementDisplayed("div_wishListButton", productName);
			clickOnElement(element("div_wishListButton", productName));
			logMessage("User click on wishlist button for product: '" + productName + "'");
			isElementDisplayed("span_addedToWishListIcon", productName);
			logMessage(productName + " added to Wishlist");

		}
	}
}
