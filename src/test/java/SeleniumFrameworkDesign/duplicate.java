package SeleniumFrameworkDesign;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.POM.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class duplicate {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();   //Using WebDruver Manager instead of System.setproperties
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));     // declare implicitly wait
		driver.get("https://rahulshettyacademy.com/client");       // launching URL
		
		
		loginPage loginPage = new loginPage(driver);
		
		driver.manage().window().maximize();

		// Credential Initial
		driver.findElement(By.id("userEmail")).sendKeys("valparaiapril@gmail.com");  
		driver.findElement(By.id("userPassword")).sendKeys("V@lparaiapril01");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));   // Explicitly wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); 
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.xpath("//div[@class='card-body']/button[@class='btn w-10 rounded']")).click();
		
		// Explicity wait for animating logo
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//By clicking cart
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//CartPage Selection
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//Clicking checkout button
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		
		Thread.sleep(10);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		Thread.sleep(10);
		driver.findElement(By.xpath("(//button[contains(@class,'ng-star-inserted')])[2]")).click();
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		
		try {
			WebElement submit = driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
			submit.click();
			
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
		}
		
		//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		//String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		//Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		Thread.sleep(10);
		//driver.close();
		
	}

}
