package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement btnMyAccount;
    @FindBy(xpath = "//a[text()='Register']")
    WebElement btnRegister;
    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
    WebElement btnLogin;

    public void clickMyAccount(){
        btnMyAccount.click();
    }
    public void clickRegister(){
        btnRegister.click();
    }
    public void clickLogin(){
        btnLogin.click();
    }

}
