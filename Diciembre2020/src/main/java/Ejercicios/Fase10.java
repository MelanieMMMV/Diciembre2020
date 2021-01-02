//crear un objeto para acceder a un metodo 

package Ejercicios;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Fase10 

{
	
	String url="https://opensource-demo.orangehrmlive.com";
	WebDriver driver;
	WebDriverWait wait;
	String user="Admin";
	String password="admin123";
	Fase9 obj=new Fase9();
	
	
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

//acceder a un metodo atravez de crear un objeto los metodos fueron creados en la clase Fase9
public void startWebDriver() {
	
    driver=obj.startWebDriver(url);
    PageFactory.initElements(driver, this);
	
}

@Test(priority=1)
public void loginFail() {
	txt_usuario.sendKeys("Admin");
	txt_password.sendKeys("admin12");
    btn_login.submit();
    
    //Explicit Wait
   // WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
   // wait.until(ExpectedConditions.visibilityOf(tbl_menu)); 
   //  wait.until(ExpectedConditions.elementToBeClickable(tbl_menu));
    
    
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
//	txt_usuario.clear();
//	txt_password.clear();
//	txt_usuario.sendKeys(user);
//    Reporter.log("Usuario Ingresado" + user ,true);
//    txt_password.sendKeys(password);
//    Reporter.log("Password Ingresado" + password + "\n" ,true);

	obj.ingresarTexto(txt_usuario,user);
	obj.ingresarTexto(txt_password,password);
	
    btn_login.submit();
    Reporter.log("Click en Login ",true);
    obj.verificarElemento(tbl_menu);
    
    
    //Explicit Wait
    //WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(30));
   // wait.until(ExpectedConditions.visibilityOf(tbl_menu));
   // wait.until(ExpectedConditions.elementToBeClickable(tbl_menu));
    
    if(tbl_menu.isDisplayed()) {
    	Reporter.log("El login fue exitoso", true); 
    }
    
    List<WebElement> list_Menu=driver.findElements(By.xpath("//li[@class='main-menu-first-level-list-item']//b"));
   
    String option="Buzz";
    System.out.println("La cantidad de elementos en la lista es : " + list_Menu.size());
    System.out.println("La opcion seleccionada es : " + option);
    
    for(int i=0; i<list_Menu.size();i++) {
    	System.out.println(list_Menu.get(i).getText());
    	if(list_Menu.get(i).getText().equals(option)) {
    		list_Menu.get(i).click();
    		break;
    		
    	}
    	
    	
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

/*
 *@Descripción :Metodo que revisa que un elemnto exista 
 *@Autor Melanie 
 *@Date 2020 12 26
 *@Parametro WebElement
 **/

public void verificarElemento(WebElement objeto) {
	try {
		wait.until(ExpectedConditions.visibilityOf(objeto));
		wait.until(ExpectedConditions.elementToBeClickable(objeto));
		Reporter.log("El elemento existe", true);
	} catch (Exception e) {
		Reporter.log("El elemento no existe", true);
		e.printStackTrace();
	}
}
	
	
	/*
	 *@Descripción :Metodo hacer scroll a un elemento 
	 *@Autor Melanie 
	 *@Date 2020 12 26
	 *@Parametro WebElement
	 **/
	
	public void scrollToElement(WebElement objeto) {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",objeto);
			Reporter.log("Scroll al elemento", true);
		}catch(Exception e) {
			e.printStackTrace(); 
		
		}
	
}

	/*
	 *@Descripción :Metodo para ingresar xpath
	 *@Autor Melanie 
	 *@Date 2020 12 26
	 *@Parametro WebElement,texto
	 **/
	
	public void ingresarTexto(WebElement objeto,String texto) {
		try {verificarElemento(objeto);
		     scrollToElement(objeto);
		     objeto.clear();
		     objeto.sendKeys(texto);
		     Reporter.log("El texto se ingreso correctamente:" + texto, true);	
		}catch(Exception e) {
			e.printStackTrace(); 
		
		}
	
}
	
}
