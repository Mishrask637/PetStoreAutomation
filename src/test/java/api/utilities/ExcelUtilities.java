package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workBook;
	public XSSFSheet workSheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle cellStyle;
	public String path;
	
	
	public ExcelUtilities(String path){
		this.path = path;
	}
	
	public int getRowsCount(String sheetName) throws FileNotFoundException, IOException{
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		workSheet = workBook.getSheet(sheetName);
		int rowCount = workSheet.getLastRowNum();
		workBook.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName,int rowNum) throws FileNotFoundException, IOException{
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		workSheet = workBook.getSheet(sheetName);
		row = workSheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fi.close();
		return cellCount;
		
	}
	
	public String getCellData(String sheetName, int rowNum,int celNum) throws IOException{
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		workSheet = workBook.getSheet(sheetName);
		row = workSheet.getRow(rowNum);
		cell = row.getCell(celNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data = "";
		}
		workBook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum,int celNum,String data) throws IOException{
		
		File file = new File(path);
		if(!file.exists()) {
			workBook = new XSSFWorkbook();
			fo = new FileOutputStream(file);
			workBook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		
		if(workBook.getSheetIndex(sheetName)==-1) {
			workBook.createSheet(sheetName);
		}
		workSheet = workBook.getSheet(sheetName);
		
		if(workSheet.getRow(rowNum)==null) {
			workSheet.createRow(rowNum);
		}
		row = workSheet.getRow(rowNum);
		
		cell = row.createCell(celNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();
	}

}
