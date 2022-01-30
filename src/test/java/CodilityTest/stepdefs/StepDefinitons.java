package CodilityTest.stepdefs;

import static CodilityTest.automationutils.YamlReader.getYamlValue;

import java.util.Map;

import CodilityTest.hooks.SessonInitiator;
import CodilityTest.webdriverfactory.TestSessionInitiator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;;

public class StepDefinitons {

	private TestSessionInitiator test;
	Map<String, String> productNameAndPrice;
	String lowestPriceProduct;

	public StepDefinitons(SessonInitiator sessonInitiator) {
		test = sessonInitiator.test;
	}

	@Given("I add four different products to my wish list")
	public void addFourDifferentProductsIntoWishList() {
		test.launchApplication(getYamlValue("url"));
		test.homePage.verifyHomePageTitle(getYamlValue("titleText.homePage"));
		test.homePage.clickOnAllCookies();
		test.homePage.clickOnShopOptionFromTopMenu();
		test.shopPage.verifyShopPageTitle(getYamlValue("titleText.shopPage"));
		productNameAndPrice=test.shopPage.getPriceOfProductsInTheList(getYamlValue("productsName.product1"),
				getYamlValue("productsName.product2"), getYamlValue("productsName.product3"),
				getYamlValue("productsName.product4"));
		test.shopPage.addProductsIntoWishlist(getYamlValue("productsName.product1"),
				getYamlValue("productsName.product2"), getYamlValue("productsName.product3"),
				getYamlValue("productsName.product4"));
	}

	@When("I view my wishlist table")
	public void viewMyWishList() {
		test.homePage.clickOnWishlistIcon();
		test.wishlistPage.verifyWishlistPageTitle(getYamlValue("titleText.wishlistPage"));
	}
	
	@Then("I find total four selected items in my Wishlist")
	public void verifyItemsInWishlist() {
		test.wishlistPage.verifyWishlistTable(productNameAndPrice);
	}
 
	@When("I search for lowest price product")
	public void searchLowestPriceItem() {
		lowestPriceProduct = test.wishlistPage.selectLowestPriceProduct(productNameAndPrice);
	}
	
	@When("I am able to add lowest price items to my cart")
	public void addLowestPriceItemToMyCart() {
		test.wishlistPage.addProductToCart(lowestPriceProduct);
	}
	
	@Then("I am able to verify the item to my cart")
	public void verifyProductInCart() {
		test.homePage.clickOnCartIcon();
		test.cartPage.verifyCartPageTitle(getYamlValue("titleText.cartPage"));
	}
}
