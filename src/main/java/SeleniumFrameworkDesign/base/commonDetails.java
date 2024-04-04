package SeleniumFrameworkDesign.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.POM.CartPage;
import SeleniumFrameworkDesign.POM.OrderPage;

public class commonDetails {

	WebDriver driver;

	public commonDetails(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartClick;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderlist;

	public void explicitlyWait(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicitly wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void explicitlyWaitWebElement(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicitly wait
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void explicitlyWaitDisappear(WebElement ele) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage cartbtn() {
		cartClick.click();
		return new CartPage(driver);
	}
	
	public OrderPage gotoOrderPage() { //CartPage
		orderlist.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
}
