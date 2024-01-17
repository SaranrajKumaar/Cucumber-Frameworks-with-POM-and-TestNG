package SFD.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import SFD.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	public WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	
	@FindBy(css = ".box h1")
	WebElement textMessage;
	
	public String verifytextMessage() {
		String confirmationMessage=textMessage.getText();
		return confirmationMessage;
	}

}
