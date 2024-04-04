package SeleniumFrameworkDesign.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.base.commonDetails;

public class CheckoutPage extends commonDetails {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement Country;

	@FindBy(xpath = "(//button[contains(@class,'ng-star-inserted')])[2]")
	WebElement selectCountry;

	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement submitbtn;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		explicitlyWait(By.cssSelector(".ta-results"));
		selectCountry.click();
	}

	public ConfirmationPage orderSubmit() {
		submitbtn.click();
		return new ConfirmationPage(driver);
	}

}
