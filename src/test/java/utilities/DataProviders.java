package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException
    {
        String path="/Users/ritiktripathi/IdeaProjects/opencartV1/testData/LoginTestData.xlsx";//taking xl file from testData

        ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility

        int totalRows =xlutil.getRowCount("Sheet1");
        int totalCols=xlutil.getCellCount("Sheet1",1);

        String loginData[][]=new String[totalRows][totalCols];//created for two dimension array which can store the data user and password

        for(int i = 1; i<= totalRows; i++)  //1   //read the data from xl storing in two deminsional array
        {
            for(int j=0;j<totalCols;j++)  //0    i is rows j is col
            {
                loginData[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
            }
        }
        return loginData;//returning two dimension array
    }



}
