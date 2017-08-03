import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExecuteTestBook {
	public static void main(String[] args)throws Exception {
		ArrayList<Object> a=new ArrayList<Object>();
		Keywords key=new Keywords();
		FileInputStream file=new FileInputStream("E://Lavanya//TestBook.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("TestPage");
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
				}
			}

		}
		for(int i=0;i<a.size();i++)
		{
			if(a.get(i).equals("launchBrowser")){
				//String keyword=(String) a.get(i);
				//String data=(String) a.get(i+1);
				//String objectname=(String) a.get(i+2);
				String runmode=(String) a.get(i+3);
				if(runmode.equals("yes")){
					key.launchBrowser();
				}
			}

			if(a.get(i).equals("navigate")){
				//String keyword=(String) a.get(i);
				String data=(String) a.get(i+1);
				//String objectname=(String) a.get(i+2);
				String runmode=(String) a.get(i+3);
				if(runmode.equals("yes")){
					key.navigate(data);
				}
			}

			

			if(a.get(i).equals("click")){
				//String keyword=(String) a.get(i);
				//String data=(String) a.get(i+1);
				String objectname=(String) a.get(i+2);
				String runmode =(String) a.get(i+3);
				String type = (String) a.get(i+4);
				if(runmode=="yes"){
						key.click(type,objectname);
				}
			}
			
			if(a.get(i).equals("input")){
				//String keyword=(String) a.get(i);
				String data=(String) a.get(i+1);
				String objectname=(String) a.get(i+2);
				String runmode=(String) a.get(i+3);
				if(runmode.equals("yes")){
					key.input(data,objectname);
				}
			}
		}
	}
}