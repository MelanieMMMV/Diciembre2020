package Ejercicios;

import static org.testng.Assert.expectThrows;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Fase3 {
	
	String url="https://opensource-demo.orangehrmlive.com";
	WebDriver driver;
	WebElement txt_usuario;
	WebElement txt_password;
	String user="Admin";
	String password="admin123";
	
@BeforeTest
public void startWebDriver() {
	ChromeOptions option = new ChromeOptions();
	option.addArguments("--start-maximized");
	option.addArguments("--incognito");
	
	System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
    driver = new ChromeDriver(option);
	driver.get(url);
	txt_usuario=driver.findElement(By.name("txtUsername"));
	txt_password=driver.findElement(By.name("txtPassword"));
	
	

}

@Test(priority=1)
public void loginFail() {

	
	
	txt_usuario.sendKeys("Admin");
	txt_password.sendKeys("admin12");
    driver.findElement(By.name("Submit")).click();
    
}

@Test(priority=2)
public void login() {
	driver.findElement(By.name("txtUsername")).clear();
	driver.findElement(By.name("txtPassword")).clear();
	driver.findElement(By.name("txtUsername")).sendKeys(user);
    Reporter.log("Usuario Ingresado" + user ,true);
    driver.findElement(By.name("txtPassword")).sendKeys(password);
    Reporter.log("Password Ingresado" + password + "\n" ,true);
    driver.findElement(By.name("Submit")).click();
    Reporter.log("Click en Login ",true);
    
}

@Test(priority=3)
public void logout() {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("welcome")).click();
	 Reporter.log("Click en welcome ",true);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Logout")).click();
	 Reporter.log("Click en Logout ",true);
	    
}



//@AfterTest
//public void closeBrowser() {
//	driver.close();
//	
//}

}
