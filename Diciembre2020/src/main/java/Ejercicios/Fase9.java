
// Clase Base 
// Se creo la clase 10 y clase 11 . En la clase 10 se accede a la clase base mediante la creción de un objeto 
// y en la clase 11 se accede a la clase bade mediante la herencia.
package Ejercicios;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Fase9 {
	
	protected WebDriver driver;
	WebDriverWait wait;
	
	public WebDriver startWebDriver(String url) {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
	    driver = new ChromeDriver(option);
		driver.get(url);
	    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	    return driver;
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
	
}
	

