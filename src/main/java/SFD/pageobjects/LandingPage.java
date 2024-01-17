package SFD.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SFD.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement loginButton;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();	
		return new ProductCatalogue(driver);
	}
	
	public void goTo() {
		driver.get("https://www.rahulshettyacademy.com/client");	
	}
	
	public String getErrorMessage() {
		WaitForWebElementToAppear(errorMessage);
		String errormessage =errorMessage.getText();
		return errormessage;
	}
}
