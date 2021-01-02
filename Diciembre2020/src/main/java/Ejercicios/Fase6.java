 //Fluent Wait 

package Ejercicios;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Fase6
{
	
	String url="https://opensource-demo.orangehrmlive.com";
	WebDriver driver;
	String user="Admin";
	String password="admin123";
	
	
	//Elementos 
	@FindBy(id="txtUsername")
	WebElement txt_usuario;
	@FindBy(name="txtPassword")
	WebElement txt_password;
	@FindBy(id="btnLogin")
	WebElement btn_login; 
	@FindBy(id="welcome")
	WebElement btn_welcome;
	@FindBy(linkText="Logout")
	WebElement btn_logout;
	@FindBy(xpath="//div[@id='mainMenu']")
	WebElement tbl_menu;
	
@BeforeTest
public void startWebDriver() {
	ChromeOptions option = new ChromeOptions();
	option.addArguments("--start-maximized");
	option.addArguments("--incognito");
	System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
    driver = new ChromeDriver(option);
    PageFactory.initElements(driver, this);
	driver.get(url);
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

}

@Test(priority=1)
public void loginFail() {
	txt_usuario.sendKeys("Admin");
	txt_password.sendKeys("admin12");
    btn_login.submit();
    
    //FluentWait
    Wait<WebDriver> wait=new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofSeconds(15))
    .ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.visibilityOf(tbl_menu));
    
    
//    if(driver.getPageSource().contains("Dashboard"))
//    {
//   	Reporter.log("El login fue exitoso", true);
//    }
//    else {
//    	Reporter.log("El login no fue exitoso", true);
//    }
    
//    if(driver.getPageSource().contains("Dashboard"))
//      {
//    	Reporter.log("El login fue exitoso", true);
//     }
//    else {
//    	Assert.assertTrue(false);
//    }
//    
    
}

@Test(priority=2)
public void login() {
	txt_usuario.clear();
	txt_password.clear();
	txt_usuario.sendKeys(user);
    Reporter.log("Usuario Ingresado" + user ,true);
    txt_password.sendKeys(password);
    Reporter.log("Password Ingresado" + password + "\n" ,true);
    btn_login.submit();
    Reporter.log("Click en Login ",true);
    
    
    
    
    if(tbl_menu.isDisplayed()) {
    	Reporter.log("El login fue exitoso", true);
    }
    
    
    
//    if(driver.getPageSource().contains("Dashboard"))
//    {
//   	Reporter.log("El login fue exitoso", true);
//   	Assert.assertTrue(true, "El login fue exitoso");
//    }
    
}

@Test(priority=3)
public void logout() {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	btn_welcome.click();
	Reporter.log("Click en welcome ",true);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	btn_logout.click();
	Reporter.log("Click en Logout ",true);
	    
}



//@AfterTest
//public void closeBrowser() {
//	driver.close();
//	
//}

}

