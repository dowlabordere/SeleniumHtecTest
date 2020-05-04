package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(name = "email")
    WebElement emailInputField;

    @FindBy(name = "password")
    WebElement passwordInputField;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submitButton;



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
