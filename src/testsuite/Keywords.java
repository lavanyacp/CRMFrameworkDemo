package testsuite;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords{
	ChromeDriver driver;
	Properties prop;
	FileInputStream file;
	WebDriverWait wait;

	public Keywords() throws IOException{
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		System.setProperty("webdriver.chrome.driver", "E:\\workspace\\userlibs\\chromedriver.exe");
		file=new FileInputStream("C:\\Users\\RenJOuS\\Git\\CRMFrameworkDemo\\src\\objectrepository\\objectrepository.properties");
		prop=new Properties();
		prop.load(file);
	}
	
	public void launchBrowser() throws Exception{
		System.out.println("launchbrowser");
		driver = new ChromeDriver();
		//driver=new FirefoxDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,15);
	}

	public void navigateToURL(String data) throws TimeoutException{
		System.out.println("navigate : "+data);
		driver.get("http://www.google.com");
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable((By.xpath(prop.getProperty("login")))));
	}

	public void inputData(String data,String objectname){
		System.out.println("input : "+data+objectname);
		driver.findElement(By.xpath(prop.getProperty(objectname))).sendKeys(data);
	}

	public void click(String type,String objectname){
		if(type=="html"){
			System.out.println("input : "+objectname);
			driver.findElementByLinkText(objectname).click();
		}
		if(type=="xpath"||type=="none"){
			System.out.println("input : "+objectname);
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(prop.getProperty(objectname)))));
			driver.findElement(By.xpath(prop.getProperty(objectname))).click();
		}
	}
}