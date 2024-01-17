package SFD.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import SFD.TestComponents.BaseTest;
import SFD.TestComponents.IRetry;
import SFD.pageobjects.CartPage;
import SFD.pageobjects.CheckOutPage;
import SFD.pageobjects.ConfirmationPage;
import SFD.pageobjects.LandingPage;
import SFD.pageobjects.ProductCatalogue;

public class ErrorvalidationTest extends BaseTest{
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = IRetry.class)
	public void errorValidation() {
		landingpage.loginApplication("mamsaran@gmail.com", "Mamaa@123");
		Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage()); 
	}
	@Test( retryAnalyzer = IRetry.class)
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.loginApplication("saran123@gmail.com", "Mamatha@123");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCartpage();
		Boolean match = cartpage.verifyCartProductDisplay(productName);
		Assert.assertFalse(match);
		
		

	}
	
	

}
