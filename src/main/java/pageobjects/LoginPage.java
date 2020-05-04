package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(name = "email")
    private WebElement emailInputField;
    @FindBy(name = "password")
    private WebElement passwordInputField;
    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;

    public void clickOnSubmitButton(){
        submitButton.click();
    }

    public void insertUsername(String email){
        emailInputField.sendKeys(email);
    }

    public void insertPassword(String password){
        passwordInputField.sendKeys(password);
    }
}
