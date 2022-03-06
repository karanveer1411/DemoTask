import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\resources\\data.properties");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\resources\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		prop.load(fis);

		System.out.println(prop.getProperty("browser"));

		String loadUrl = (prop.getProperty("url"));
		driver.get(loadUrl);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_2doB4z")));
		cross.click();
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"))
				.sendKeys("redmi note 8" + Keys.ENTER);

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@data-id='MOBFHFFSMVQJYYWY']//div[@class='col col-7-12']")))
				.click();

		driver.getCurrentUrl();

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();

		String parentWindowId = it.next();

		String childWindow = it.next();

		driver.switchTo().window(childWindow);
		driver.getCurrentUrl();
		String price = driver.findElement(By.xpath("(//div[@class='_30jeq3 _16Jk6d'])[1]")).getText();
		System.out.println("before price" + price);
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ADD TO CART']"))).click();

		String cartData = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='_3s5O6i']"))).getText();
		//commented this code as the product sorry we don't have any more units
//		driver.findElement(By.xpath("//button[normalize-space()='+']")).click();

		System.out.print("In final cart" + cartData);


	}

}
