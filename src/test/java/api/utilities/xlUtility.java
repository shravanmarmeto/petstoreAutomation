package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook book;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	// constructor
	public xlUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetname) throws IOException {
		fi = new FileInputStream(path);
		book = new XSSFWorkbook(fi);
		sheet = book.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		book.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetname, int rownum) throws IOException {
		fi = new FileInputStream(path);
		book = new XSSFWorkbook(fi);
		sheet = book.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		book.close();
		fi.close();
		return cellcount;

	}
	
	public String getCellData(String sheetname, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		book = new XSSFWorkbook(fi);
		sheet = book.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell=row.getCell(colnum);
		DataFormatter form=new DataFormatter();
		String data;
		try {
			data=form.formatCellValue(cell); //It will return string value regardless of the datatype
		}
		catch (Exception e) {
			data="";
		}
		book.close();
		fi.close();
		return data;
	}
	//there are other methods, check out 20th vieo, Timestamp: 30
}
