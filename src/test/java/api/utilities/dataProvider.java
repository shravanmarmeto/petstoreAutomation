package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProvider {
//get ALL data from excel sheet
	@DataProvider(name = "data")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir")+"//testData//Userdata1.xlsx";
		xlUtility xl = new xlUtility(path);
		int rownum = xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1", 1);
		//string 2D array
		String apidata[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
//get only usernames
	@DataProvider(name = "username")
	public String[] getUsernames() throws IOException {
		String path = System.getProperty("user.dir")+"//testData//Userdata1.xlsx";
		xlUtility xl = new xlUtility(path);
		int rownum = xl.getRowCount("Sheet1");
		String apidata[] = new String[rownum];
		for (int i = 1; i <= rownum; i++) {

			apidata[i - 1] = xl.getCellData("Sheet1", i, 1); //j=1 because username is in 2nd column

		}
		return apidata;
	}
}
