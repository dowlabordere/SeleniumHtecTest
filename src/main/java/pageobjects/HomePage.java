package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(xpath = "//b[text()='QA Sandbox']")
    WebElement navigationBrand;

    @FindBy(xpath = "//div[@id='mobile-nav']//a[1]")
    WebElement fogotPasswordNavBarButton;

    @FindBy(xpath = "//div[@id='mobile-nav']//a[2]")
    WebElement loginNavBarButton;

    @FindBy(xpath = "//p[text()='Quality Assurance testing playgrounds. Play, explore, test...']/following-sibling::a")
    private WebElement forgotPasswordMiddleButton;

    @FindBy(xpath = "//p[text()='Quality Assurance testing playgrounds. Play, explore, test...']/following-sibling::a/following-sibling::a")
    private WebElement loginMiddleButton;

    @FindBy(xpath = "//a[@href='http://htecgroup.com']")
    WebElement htecLogo;

    public void clickOnForgotPassword(){
        forgotPasswordMiddleButton.click();
    }

    public void clickOnLogin(){
        loginMiddleButton.click();
    }

}
