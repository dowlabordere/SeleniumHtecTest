package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Constants;

public class Navigation {

    public static void loginUser(WebDriver driver) {
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.insertUsername(Constants.LOGIN_USER);
        lp.insertPassword(Constants.LOGIN_PASS);
        lp.clickOnSubmitButton();
    }

    public static void goToLoginPage(WebDriver driver) {
        HomePage hm = PageFactory.initElements(driver, HomePage.class);
        hm.clickOnLogin();
    }

    public static void goToUseCasePage(WebDriver driver) {
        DashboardPage dp = PageFactory.initElements(driver, DashboardPage.class);
        dp.clickOnUseCasesCard();
    }
}
