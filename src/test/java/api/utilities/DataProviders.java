package api.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	public static void main(String[] args) {
		
	}
	
	@DataProvider(name = "Data")
	public String[][] getAllData() throws FileNotFoundException, IOException{
		String path = System.getProperty("user.dir")+"//src//test//resources//testdata//TestData.xlsx";
		ExcelUtilities utility = new ExcelUtilities(path);
		
		int rownum = utility.getRowsCount("Sheet1");
		int colCount = utility.getCellCount("Sheet1", 1);
		System.out.println("Row num is "+rownum +" and col numis "+colCount);
		String apiData[][] = new String[rownum][colCount];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colCount;j++) {
				apiData[i-1][j] = utility.getCellData("Sheet1", i, j);
				System.out.println("App Data of " + "["+(i-1)+"]"+"["+j+"]" +" = "+apiData[i-1][j]);
			}
		}
		
		
		
		return apiData;
	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws FileNotFoundException, IOException{
		String path = System.getProperty("user.dir")+"//src//test//resources//testdata//TestData.xlsx";
		ExcelUtilities utility = new ExcelUtilities(path);
		
		int rownum = utility.getRowsCount("Sheet1");
		String apiData[] = new String[rownum];
		
		for(int i=1;i<=rownum;i++) {
				apiData[i-1] = utility.getCellData("Sheet1", i, 1);
		}
		return apiData;
	}
	
}
