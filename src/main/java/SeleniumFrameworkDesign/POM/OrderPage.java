package SeleniumFrameworkDesign.POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.base.commonDetails;

public class OrderPage extends commonDetails{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutBtn;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}

