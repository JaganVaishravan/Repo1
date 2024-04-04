package SeleniumFrameworkDesign.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.base.commonDetails;


public class loginPage extends commonDetails {

	WebDriver driver;
	
	public loginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement pwd;
	
	@FindBy(id="login")
	WebElement loginbtn;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;

	
	public productCatalogPage loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		pwd.sendKeys(password);
		loginbtn.click();
		return new productCatalogPage(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		explicitlyWaitWebElement(errorMessage);
		return errorMessage.getText();
	}

	
}
