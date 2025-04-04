package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_UserLoginDDT extends BaseClass {


    @Test(dataProvider = "LoginData", dataProviderClass= DataProviders.class, groups = "data driven")
    void verify_user_login(String email, String pwd, String exp){

        logger.info("************ TC002_UserLoginTest Started ************");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            MyAccountPage myAcc = new MyAccountPage(driver);
            boolean myAccountPageFlag = myAcc.isMyAccountPageExists();

            if (exp.equalsIgnoreCase("Valid")) {
                if (myAccountPageFlag==true) {
                    myAcc.logoutUser();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (myAccountPageFlag==true) {
                    myAcc.logoutUser();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail("An exception occurred: " + e.getMessage());
        }
       logger.info("************ TC002_UserLoginTest Completed ************");
    }
}
