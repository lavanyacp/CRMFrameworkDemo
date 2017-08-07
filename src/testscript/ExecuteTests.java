package testscript;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExecuteTests {
	private FileInputStream file;
	private BufferedInputStream xlfile;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private ArrayList<Object> datalist;
	static Logger logger;
	public static void main(String[] args){
		logger = Logger.getLogger(ExecuteTests.class);
		PropertyConfigurator.configure("config/log4j.properties");
		logger.info("This is my first log4j statement");
		ExecuteTests test = new ExecuteTests();
			test.init();
			test.populateList();
			test.executeKeywords();
			test.finalize();
	}

	public void init(){
		datalist=new ArrayList<Object>();
		try {
			file = new FileInputStream("res/TestSuite.xlsx");
		} catch (FileNotFoundException fnfe) {
			logger.error("FileNotFoundException in ExecuteTests.init() : TestSuite file not found", fnfe);
		}
		xlfile = new BufferedInputStream(file);
		try {
			workbook = new XSSFWorkbook(xlfile);
		} catch (IOException ioe) {
			logger.error("IOException in ExecuteTests.init() : can't read xlfile", ioe);
		}
		sheet = workbook.getSheet("TestLeadSteps");
	}

	public void populateList(){
		Iterator<Row> row=sheet.iterator();
		while(row.hasNext()){
			Row rowitr=(Row) row.next();
			Iterator<Cell> cellitr=rowitr.cellIterator();
			while(cellitr.hasNext()){
				Cell cell=(Cell)cellitr.next();
				switch(cell.getCellTypeEnum()){
				case BLANK:
					datalist.add(cell.toString());
					break;
				case STRING:
					datalist.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					datalist.add(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					datalist.add(cell.getBooleanCellValue());
					break;
				default:
					System.out.println("Undefined Cell Type");
					break;
				}
			}
		}

	}

	public void executeKeywords(){
		Keywords keywords=new Keywords();
		for(int i=0;i<datalist.size();i++)
		{
			if(datalist.get(i).equals("launchBrowser")){
				//String keyword=(String) datalist.get(i);
				//String data=(String) datalist.get(i+1);
				//String objecttype=(String) datalist.get(i+2);
				//String objectname=(String) datalist.get(i+3);
				boolean runmode=(boolean) datalist.get(i+4);
				//boolean waitmode=(boolean) datalist.get(i+5);
				if(runmode){
					keywords.launchBrowser();
				}
			}

			if(datalist.get(i).equals("navigateToURL")){
				String data=(String) datalist.get(i+1);
				boolean runmode=(boolean) datalist.get(i+4);
				if(runmode){
					keywords.navigateToURL(data);
				}
			}

			if(datalist.get(i).equals("inputData")){
				String data=(String) datalist.get(i+1);
				String objecttype=(String) datalist.get(i+2);
				String objectname=(String) datalist.get(i+3);
				boolean runmode=(boolean) datalist.get(i+4);
				boolean waitmode=(boolean) datalist.get(i+5);
				if(runmode){
					keywords.inputData(data, objecttype, objectname, waitmode);
				}
			}

			if(datalist.get(i).equals("clickElement")){
				String objecttype=(String) datalist.get(i+2);
				String objectname=(String) datalist.get(i+3);
				boolean runmode=(boolean) datalist.get(i+4);
				boolean waitmode=(boolean) datalist.get(i+5);
				if(runmode){
					keywords.clickElement(objecttype, objectname, waitmode);
				}
			}
		}
	}

	protected void finalize(){
		try {
			workbook.close();
			file.close();
		} catch (IOException ioe) {
			logger.error("IOException in ExecuteTests.finalize() : Error while closing InputStream", ioe);
		}
	}

}