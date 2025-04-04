package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver){
    super(driver);
}
    @FindBy(xpath = "//input[@id='input-firstname']") WebElement txtUserName;
    @FindBy(xpath = "//input[@id='input-lastname']") WebElement txtLastName;
    @FindBy(xpath = "//input[@id='input-email']") WebElement txtEmail;
    @FindBy(xpath = "//input[@id='input-telephone']") WebElement txtPhoneNum;
    @FindBy(xpath = "//input[@id='input-password']") WebElement txtPassword;
    @FindBy(xpath = "//input[@id='input-confirm']") WebElement txtConfirmPassword;
    @FindBy(xpath = "//input[@name='agree']") WebElement chkBoxPolicy;
    @FindBy(xpath = "//input[@value='Continue']") WebElement btnContinue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmationMsg;

   public void setUserName(String username){
        txtUserName.sendKeys(username);
    }
    public void setLastName(String lastname){
        txtLastName.sendKeys(lastname);
    }
    public void setEmailID(String email){
        txtEmail.sendKeys(email);
    }
    public void setPhoneNumber(String phoneNumber){
        txtPhoneNum.sendKeys(phoneNumber);
    }
    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }
    public void setConfirmPassword(String cnfPassword){
        txtConfirmPassword.sendKeys(cnfPassword);
    }
    public void setPrivacyAgreePolicy(){
        chkBoxPolicy.click();
    }
    public void clickContinue(){
        btnContinue.click();
    }

    public String getSignUpConfirmationText(){
        try {
            return confirmationMsg.getText();
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
