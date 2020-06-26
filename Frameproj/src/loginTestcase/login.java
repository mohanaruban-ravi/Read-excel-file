package loginTestcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class login {

	String[][] data = { { "Admin", "admin123" }, { "Admin1", "admin123" }, { "Admin", "admin1231" },
			{ "Admin1", "admin1231" } };

	@DataProvider(name = "logindata")
	public String[][] logindataprovider() {
		return data;
	}

	@Test(dataProvider = "logindata")
	public void loginapp(String uname, String paswd) {

		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(paswd);

		driver.quit();
	}

}
