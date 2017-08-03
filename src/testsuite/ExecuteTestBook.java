package testsuite;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExecuteTestBook {
	static FileInputStream file;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public static void main(String[] args)throws Exception {
		ArrayList<Object> a=new ArrayList<Object>();
		Keywords keywords=new Keywords();
		file = new FileInputStream("C:\\Users\\RenJOuS\\Git\\CRMFrameworkDemo\\src\\objectrepository\\TestSuite.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet("TestLeadSteps");
		Iterator<Row> row=sheet.iterator();
		while(row.hasNext()){
			Row rowitr=(Row) row.next();
			Iterator<Cell> cellitr=rowitr.cellIterator();
			while(cellitr.hasNext()){
				Cell cell=(Cell)cellitr.next();
				switch(cell.getCellTypeEnum()){
				case BLANK:
					a.add(cell.toString());
				case STRING:
					a.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					a.add(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					a.add(cell.getBooleanCellValue());
					break;
				default:
					System.out.println("Undefined Cell Type");
					break;
				}
			}

		}
		for(int i=0;i<a.size();i++)
		{
			if(a.get(i).equals("launchBrowser")){
				String keyword=(String) a.get(i);
				String data=(String) a.get(i+1);
				String objectname=(String) a.get(i+2);
				boolean runmode=(boolean) a.get(i+3);
				if(runmode){
					keywords.launchBrowser();
				}
			}

			if(a.get(i).equals("navigate")){
				String keyword=(String) a.get(i);
				String data=(String) a.get(i+1);
				String objectname=(String) a.get(i+2);
				boolean runmode=(boolean) a.get(i+3);
				if(runmode){
					keywords.navigateToURL(data);
				}
			}



			if(a.get(i).equals("click")){
				String keyword=(String) a.get(i);
				String data=(String) a.get(i+1);
				String objectname=(String) a.get(i+2);
				boolean runmode=(boolean) a.get(i+3);
				String type = (String) a.get(i+4);
				if(runmode){
					keywords.click(type,objectname);
				}
			}

			if(a.get(i).equals("inputData")){
				String keyword=(String) a.get(i);
				String data=(String) a.get(i+1);
				String objectname=(String) a.get(i+2);
				boolean runmode=(boolean) a.get(i+3);
				if(runmode){
					keywords.inputData(data,objectname);
				}
			}
		}
	}

	protected void finalize() throws IOException{
		workbook.close();
		file.close();
	}

}