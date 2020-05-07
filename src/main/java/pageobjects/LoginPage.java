package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    public WebElement getEmailInputField() {
        return emailInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getSubmitLogin() {
        return submitLogin;
    }

    @FindBy(name = "email")
    private WebElement emailInputField;
    @FindBy(name = "password")
    private WebElement passwordInputField;
    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitLogin;

    public void clickOnSubmitButton(){
        submitLogin.click();
    }

    public void insertUsername(String email){
        emailInputField.sendKeys(email);
    }

    public void insertPassword(String password){
        passwordInputField.sendKeys(password);
    }
}
