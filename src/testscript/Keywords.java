package testscript;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords{
	ChromeDriver driver;
	Properties prop;
	FileInputStream file;
	BufferedInputStream propfile;
	WebDriverWait wait;
	String objecttype;
	Logger logger;

	public Keywords(){
		//System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		System.setProperty("webdriver.chrome.driver", "E:\\workspace\\userlibs\\chromedriver.exe");
		try {
			file=new FileInputStream("src/objectrepository/objectrepository.properties");
		} catch (FileNotFoundException fnfe) {
			logger.error("FileNotFoundException in Keywords constructor() : properties file not found", fnfe);
		}
		propfile = new BufferedInputStream(file);
		prop=new Properties();
		try {
			prop.load(file);
		} catch (IOException ioe) {
			logger.error("IOException in ExecuteTests.init() : cant read xlfile", ioe);
		}
	}

	public void launchBrowser(){
		System.out.println("Launch Browser");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		wait = new WebDriverWait(driver,10);
	}

	public void navigateToURL(String data){
		System.out.println("navigate : "+data);
		driver.get(data);
	}

	public void inputData(String data,String selectortype, String selectorname, boolean waitmode){
		System.out.println("input : "+data+selectortype+selectorname+waitmode);
		driver.findElement(By.xpath(prop.getProperty(selectorname))).sendKeys(data);
	}

	public void clickElement(String selectortype, String selectorname, boolean waitmode){
		System.out.println("click : "+selectortype+selectorname+waitmode);
		switch(selectortype){
		case "id":
			driver.findElementById((prop.getProperty(selectorname))).click();
			break;
		case "name":
			driver.findElementByName(prop.getProperty(selectorname)).click();
			break;
		case "xpath":
			driver.findElementByXPath(prop.getProperty(selectorname)).click();
			break;
		case "linktext":
			driver.findElementByLinkText(prop.getProperty(selectorname)).click();
			break;
		default:
			System.out.println("Invalid/null selector type");
			break;

		}
	}

	public void selectItem(String data, String selectortype, String selectorname, boolean waitmode) {
		System.out.println("select : "+data+selectortype+selectorname+waitmode);
		Select selector = new Select(driver.findElement(By.xpath(prop.getProperty(selectorname))));
		selector.selectByVisibleText(data);
	}
	
	protected void finalize(){
		prop.clear();
		try {
			file.close();
		} catch (IOException ioe) {
			logger.error("IOException in Keywords.finalize() : Error while closing InputStream", ioe);
		}
	}

}