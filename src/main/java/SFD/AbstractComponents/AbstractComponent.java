package SFD.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SFD.pageobjects.CartPage;
import SFD.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderButton;
	

	public void WaitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	public void WaitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	
	public CartPage goToCartpage() {
		cartButton.click();
		return new CartPage(driver);
		
	}
	
	public OrderPage goToOrderPage() {
		orderButton.click();
		return new OrderPage(driver);
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));	
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	
	
}
