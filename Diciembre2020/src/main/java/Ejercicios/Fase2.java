package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Fase2 {
	
	String url="https://opensource-demo.orangehrmlive.com";
	WebDriver driver;
	String user ="Admin";
	String password ="admin123";
	
@BeforeTest
public void startWebDriver() {
	ChromeOptions option = new ChromeOptions();
	option.addArguments("--start-maximized");
	option.addArguments("--incognito");
	
	System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
    driver = new ChromeDriver(option);
	driver.get(url);
	
	
	

}

@Test(priority=1)
public void loginFail() {
	driver.findElement(By.name("txtUsername")).sendKeys("Admin");;
    driver.findElement(By.name("txtPassword")).sendKeys("admin12");
    driver.findElement(By.name("Submit")).click();
    
}

@Test(priority=2)
public void login() {
	driver.findElement(By.name("txtUsername")).clear();
	driver.findElement(By.name("txtPassword")).clear();
	driver.findElement(By.name("txtUsername")).sendKeys(user);
    Reporter.log("Usuario Ingresado" + user +true);
    driver.findElement(By.name("txtPassword")).sendKeys(password);
    Reporter.log("Password Ingresado" + password + "\n" ,true);
    driver.findElement(By.name("Submit")).click();
    Reporter.log("Click en Login "+ "",true);
    
}

@AfterTest
public void closeBrowser() {
	driver.close();
	
}

}
