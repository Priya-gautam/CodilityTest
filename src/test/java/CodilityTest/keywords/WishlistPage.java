package CodilityTest.keywords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import CodilityTest.automationutils.GetPage;
import junit.framework.Assert;

public class WishlistPage extends GetPage {

	public WishlistPage(WebDriver driver) {
		super(driver, "WishlistPage");
	}

	public void verifyWishlistPageTitle(String title) {
		wait.waitForPageToLoadCompletely();
		Assert.assertTrue(element("h2_wishlistTitle").getText().equals(title));
		logMessage("Wishlist Page: '" + title + "' is appearing");
	}

	public void verifyWishlistTable(Map<String, String> productNameAndPrice) {
		for (Map.Entry<String, String> entry : productNameAndPrice.entrySet()) {
			isElementDisplayed("bdi_productAndPrice", entry.getKey(), entry.getValue());
		}
	}

	public String selectLowestPriceProduct(Map<String, String> productNameAndPrice) {
		List<java.math.BigDecimal> listOfPrice = new ArrayList<>();
		String productName = null;
		for (Map.Entry<String, String> entry : productNameAndPrice.entrySet()) {
			String price = entry.getValue().replace("£", "");
			java.math.BigDecimal v1 = new java.math.BigDecimal(price);
			listOfPrice.add(v1);
		}
		Collections.sort(listOfPrice);
		for (Map.Entry<String, String> entry : productNameAndPrice.entrySet()) {
			if (entry.getValue().equals("£" + listOfPrice.get(0))) {
				productName = entry.getKey();
			}
		}
		return productName;
	}

	public void addProductToCart(String productName) {
		isElementDisplayed("a_addToCardIcon", productName);
		clickOnElement(element("a_addToCardIcon", productName));
		logMessage("User click on 'Add to Cart' icon for product: "+productName);
	}
}
