package regression;

import framework.Base;
import framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageobjects.DashboardPage;
import pageobjects.LoginPage;

public class DashboardPageTests {

//    private WebDriver driver;
//
//    @BeforeSuite
//    public void setupDriver(){
//        driver = DriverManager.getInstance();
//    }
//
//    @BeforeTest
//    public void goToPage(){
//        driver.get("https://qa-sandbox.apps.htec.rs/login");
//    }
//
//    @AfterTest
//    public void clearBrowser(){
//        DriverManager.clearBrowserLogs();
//    }
//
//    @AfterSuite
//    public void closeBrowser(){
//        DriverManager.close();
//    }
//
//    @Test
//    public void useCasesCard(){
//        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//        loginPage.insertUsername("dowlabordere@gmail.com");
//        loginPage.insertPassword("Vlad1m1r");
//        loginPage.clickOnSubmitButton();
//        Base.waitForElementToAppear(driver, By.className("row"), 5);
//    }
}
