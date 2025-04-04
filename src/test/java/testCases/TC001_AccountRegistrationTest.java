package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {


    @Test(groups = {"master","regression"})
    void verify_account_registration(){
        try {
            logger.info("********* Test TC001_AccountRegistrationTest is getting started **********  ");

            HomePage hp = new HomePage(driver);
            logger.info("Clicking on My Account...");
            hp.clickMyAccount();
            logger.info("Clicking on Register");
            hp.clickRegister();

            logger.info("Filling details in Sign-up form...");
            RegistrationPage rp = new RegistrationPage(driver);
            rp.setUserName(getRandomTextString());
            rp.setLastName(getRandomTextString());
            rp.setEmailID(getRandomTextString() + "@gmail.com");
            rp.setPhoneNumber(getRandomNumericString());
            String tmp = getRandomAlphaNumericString();
            rp.setPassword(tmp);
            rp.setConfirmPassword(tmp);
            logger.info("Clicking on privacy policy checkbox....");
            rp.setPrivacyAgreePolicy();
            logger.info("Submitting the details with Continue button...");
            rp.clickContinue();
            logger.info("Validating the message for successful sign-up..");

            if(rp.getSignUpConfirmationText().equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);

            } else {
                logger.error("Test failed..");
                logger.debug("Debug logs..");
                Assert.assertTrue(false);
            }
    }catch (Exception e){
            Assert.fail();
            System.out.println(e);
        }
        logger.info("********* Test TC001_AccountRegistrationTest finished **********  ");
    }
}
