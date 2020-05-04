package regression;

import framework.Base;
import framework.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageobjects.DashboardPage;

public class DashboardPageTests {

    private WebDriver driver;

    @Test
    public void goToUseCasesCard(){
        useCasesCard();
    }

    public void useCasesCard(){
        HomePageTests hpt = new HomePageTests();
        hpt.clickOnLoginPage();
        LoginPageTests lpt = new LoginPageTests();
        lpt.login();
        driver = BrowserFactory.startBrowser("chrome");
        DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        Base.waitForElementToAppear(driver, By.xpath("//h5[text()='Vladimir Jovanovic']"), 5);
        dashboardPage.clickOnUseCasesCard();
        Base.waitForElementToAppear(driver, By.xpath("//a[text()='CREATE USE CASE']"), 5);
    }
}
