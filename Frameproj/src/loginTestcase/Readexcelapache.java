package loginTestcase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Readexcelapache {
	static List<String> usernamelist=new ArrayList<String>();
	static List<String> passwordlist=new ArrayList<String>();

	public void readexcel() throws IOException {
		
		  

		FileInputStream file = new FileInputStream("E:\\selenium\\Testdata.xls");
		Workbook wbk = new HSSFWorkbook(file);
		Sheet sht = wbk.getSheetAt(0);

		Iterator<Row> rowitr = sht.iterator();

		while (rowitr.hasNext()) {
			Row rowvalue = rowitr.next();

			Iterator<Cell> colmvalue = rowvalue.iterator();
			int i=2;
			while (colmvalue.hasNext()) {

				
				
				
				
				if(i%2==0) {
					usernamelist.add(colmvalue.next().getStringCellValue());
				}
				else
				{
					passwordlist.add(colmvalue.next().getStringCellValue());
				}
				i++;

			}
		}
		

	}
	
	
	public void login(String uname, String paswd) {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(paswd);
	}
	
	public void exect() {
		
		
		for(int i=0;i<usernamelist.size();i++) {
			login(usernamelist.get(i), passwordlist.get(i));
			
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Readexcelapache read=new Readexcelapache();
		read.readexcel();
		System.out.println("usnam"+usernamelist);
		System.out.println("pass"+passwordlist);
		read.exect();
		
		
		

	}

}
