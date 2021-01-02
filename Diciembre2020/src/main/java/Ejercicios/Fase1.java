package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Fase1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	    driver.get("https://www.google.com/");
	    driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
	    driver.findElement(By.name("txtUsername")).sendKeys("Admin");;
	    driver.findElement(By.name("txtPassword")).sendKeys("admin123");
	    driver.findElement(By.name("Submit")).click();
	    
	     
	   

	}

}
