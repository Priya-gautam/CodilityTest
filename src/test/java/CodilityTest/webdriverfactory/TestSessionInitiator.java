package CodilityTest.webdriverfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import CodilityTest.automationutils.ConfigPropertyReader;
import CodilityTest.automationutils.YamlReader;
import CodilityTest.keywords.CartPage;
import CodilityTest.keywords.HomePage;
import CodilityTest.keywords.ShopPage;
import CodilityTest.keywords.WishlistPage;

public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	protected static String product, browser;

	// Initiating page objects
	public HomePage homePage;
	public CartPage cartPage;
	public ShopPage shopPage;
	public WishlistPage wishlistPage;

	private void _initPage() {
		homePage = new HomePage(driver);
		cartPage = new CartPage(driver);
		shopPage = new ShopPage(driver);
		wishlistPage = new WishlistPage(driver);
	}

	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		testInitiator();
	}

	public void testInitiator() {
		YamlReader.setYamlFilePath();
		_configureBrowser();
		_initPage();
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigPropertyReader.getProperty("timeout")),
				TimeUnit.SECONDS);
	}

	public static Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "product", "timeout" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			try {
				if (System.getProperty(string).isEmpty())
					config.put(string, ConfigPropertyReader.getProperty(string));
				else
					config.put(string, System.getProperty(string));

			} catch (NullPointerException e) {
				config.put(string, ConfigPropertyReader.getProperty(string));

			}
		}
		return config;
	}

	public static String getProduct() {
		if (System.getProperty("product") != null) {
			product = System.getProperty("product");
		} else {
			product = _getSessionConfig().get("product");
		}
		return product;
	}

	public static String getBrowser() {
		if (System.getProperty("browser") != null) {
			browser = System.getProperty("browser");
		} else {
			browser = _getSessionConfig().get("browser");
		}
		return browser;
	}

	public static String getEnv() {
		String tier = System.getProperty("tier");
		if (tier == null) {
			tier = _getSessionConfig().get("tier");
		}
		return tier;
	}

	public void launchApplication(String url) {
		driver.get(url);
	}

	public void closeTestSession() {
		driver.quit();
	}

}
