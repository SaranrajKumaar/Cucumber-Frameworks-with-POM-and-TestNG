package SFD.Test;

import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.annotations.Until;

import SFD.TestComponents.BaseTest;
import SFD.pageobjects.CartPage;
import SFD.pageobjects.CheckOutPage;
import SFD.pageobjects.ConfirmationPage;
import SFD.pageobjects.LandingPage;
import SFD.pageobjects.OrderPage;
import SFD.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	public String productName = "ZARA COAT 3";

	@Test(dataProvider = "getdata",groups = {"purchase"})
	public void sumbitOrderTest(HashMap<String, String> input)throws IOException, InterruptedException {

		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productName"));
		CartPage cartpage = productcatalogue.goToCartpage();
		Boolean match = cartpage.verifyCartProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry();
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String ConfirmMessage = confirmationpage.verifytextMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "sumbitOrderTest" })
	public void OrderHistoryTest() {
		ProductCatalogue productcatalogue = landingpage.loginApplication("mamathasaran@gmail.com", "Mamatha@123");
		OrderPage orderpage = productcatalogue.goToOrderPage();
		assertTrue(orderpage.verifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getdata() throws IOException {
		
		List<HashMap<String, String>>data =getReadJson(System.getProperty("user.dir")+"\\src\\test\\java\\SFD\\data\\purchase.json");
		
		
		/**HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email", "mamathasaran@gmail.com");
		map1.put("password", "Mamatha@123");
		map1.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map2 = new HashMap<String,String>();
		map2.put("email", "mamatha@gmail.com");
		map2.put("password", "Saran@123");
		map2.put("productName", "ADIDAS ORIGINAL");**/
		
		
		

		return new Object[][] { {data.get(0)},
				{data.get(1)} };

	}

}
