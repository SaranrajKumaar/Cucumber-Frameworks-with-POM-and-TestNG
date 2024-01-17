package SFD.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SFD.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	public WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//div[@class='cartSection'])/h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	

	public Boolean verifyCartProductDisplay(String productName) {
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckOutPage goToCheckout() {
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
}
