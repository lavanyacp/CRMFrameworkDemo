import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords{
	static FirefoxDriver driver;
	static Properties prop;
	static FileInputStream file;
	static WebDriverWait wait;

	public Keywords() throws IOException{
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		file=new FileInputStream("E://Lavanya//CRMFramework//src//objectrepositoryy.properties");
		prop=new Properties();
		prop.load(file);
	}

	public void launchBrowser() throws Exception{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,15);
		System.out.println("launchbrowser");
	}

	public void navigate(String data) throws TimeoutException{
		driver.get(data);
		wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable((By.xpath(prop.getProperty("login")))));
		System.out.println("navigate : "+data);
	}

	public void input(String data,String objectname){
		driver.findElement(By.xpath(prop.getProperty(objectname))).sendKeys(data);
		System.out.println("input : "+data+objectname);
	}

	public void click(String type,String objectname){
		if(type=="html"){
			driver.findElementByLinkText(objectname).click();
			System.out.println("click : "+type+objectname);
		}
		if(type=="xpath"||type=="none"){
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(prop.getProperty(objectname)))));
			driver.findElement(By.xpath(prop.getProperty(objectname))).click();
			System.out.println("click : "+type+objectname);
		}
	}
}