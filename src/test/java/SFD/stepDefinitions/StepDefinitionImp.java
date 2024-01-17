package SFD.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SFD.TestComponents.BaseTest;
import SFD.pageobjects.CartPage;
import SFD.pageobjects.CheckOutPage;
import SFD.pageobjects.ConfirmationPage;
import SFD.pageobjects.LandingPage;
import SFD.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest {

	public LandingPage landingpage ;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage ;
	public CheckOutPage checkoutpage;
	public 	ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingpage=launchApplication();
		
	}
	
	@Given ("^Login with username (.+) and Password (.+)$")
	public void  Login_with_username_name_and_Password_password(String username , String password){
		
		productcatalogue = landingpage.loginApplication(username,password);
	}

	@When ("^Add the valid Product (.+) to the  cart$")
	public void  Add_the_valid_Product_productName_to_the_cart(String productName) throws InterruptedException {
		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		
	}
	
	@When( "^Checkout (.+) and Submit the order$")
	public void Checkout_productName_and_Submit_the_order(String productName) throws InterruptedException{
		cartpage = productcatalogue.goToCartpage();
		Boolean match = cartpage.verifyCartProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry();
		confirmationpage = checkoutpage.submitOrder();
	}
	@Then ("{string} message is Displayed on ConfirmationPage")
	public void message_is_Displayed_on_ConfirmationPage(String string) {
		String ConfirmMessage = confirmationpage.verifytextMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("{string} Message is displayed")
	public void message_is_displayed(String  string) {
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}
}
