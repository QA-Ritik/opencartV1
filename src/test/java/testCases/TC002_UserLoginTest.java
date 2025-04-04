package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_UserLoginTest extends BaseClass {

    @Test(groups = {"sanity", "master"})
    void verify_user_login(){

        logger.info("************ TC002_UserLoginTest Started ************");
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);
        lp.setEmail(properties.getProperty("userEmail"));
        lp.setPassword(properties.getProperty("password"));
        lp.clickLogin();

        MyAccountPage myAcc= new MyAccountPage(driver);
        boolean myAccountPageFlag = myAcc.isMyAccountPageExists();
        if(myAccountPageFlag){
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }

    }

}
