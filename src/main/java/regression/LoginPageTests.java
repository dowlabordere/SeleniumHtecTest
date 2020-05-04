package regression;

import framework.Base;
import framework.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

public class LoginPageTests {

    private WebDriver driver;

    @Test
    public void loginToSandbox(){
        HomePageTests homepageTests = new HomePageTests();
        homepageTests.clickOnLoginPage();
        login();
    }

    public void login(){
        driver = BrowserFactory.startBrowser("chrome");
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.insertUsername("dowlabordere@gmail.com");
        loginPage.insertPassword("Vlad1m1r");
        loginPage.clickOnSubmitButton();
        Base.waitForElementToAppear(driver, By.className("row"), 5);
    }

}
