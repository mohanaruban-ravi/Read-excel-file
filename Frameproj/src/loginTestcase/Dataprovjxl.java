package loginTestcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Dataprovjxl {

	String[][] data;
	WebDriver driver;

	public String[][] logindata() throws BiffException, IOException {

		FileInputStream excl = new FileInputStream("E:\\selenium\\Testdata.xls");
		Workbook wbk = Workbook.getWorkbook(excl);
		Sheet sh = wbk.getSheet(0);

		int rowcount = sh.getRows();
		int colmcount = sh.getColumns();

		String arry[][] = new String[rowcount - 1][colmcount];

		for (int i = 1; i < rowcount; i++) {
			for (int j = 0; j < colmcount; j++) {
				arry[i - 1][j] = sh.getCell(j, i).getContents();
			}
		}
		return arry;

	}

	@DataProvider(name = "logindata")
	public String[][] logindatamethod() throws BiffException, IOException {

		return data = logindata();
	}

	@BeforeTest
	public void beforemento() {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@AfterTest
	public void afteremthod() {
		driver.quit();
	}

	@Test(dataProvider = "logindata")
	public void logindataprov(String uname, String paswd) {

		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(paswd);

	}

}
