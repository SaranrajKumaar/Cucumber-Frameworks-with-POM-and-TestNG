package SFD.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SFD.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	public WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		
	}
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectingCountry;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderButton;
	
	@FindBy(css=".form-group input")
	WebElement countrynameInput;
	
	By listofCountry =By.cssSelector(".list-group");


	public void selectCountry() {
		
		Actions action =new Actions(driver);
		action.sendKeys(countrynameInput,"India").build().perform();
		WaitForElementToAppear(listofCountry);
		selectingCountry.click();
	}

	public ConfirmationPage submitOrder() throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,450)");
	
		Thread.sleep(3000);
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
}
