package pageobjects;

import framework.DriverManager;
import org.openqa.selenium.support.PageFactory;
import utils.Constants;

public class Navigation {

    public static void loginUser(){
        LoginPage lp = PageFactory.initElements(DriverManager.init(), LoginPage.class);
        lp.insertUsername(Constants.LOGIN_USER);
        lp.insertPassword(Constants.LOGIN_PASS);
        lp.clickOnSubmitButton();
    }

    public static void goToLoginPage(){
        HomePage hm = PageFactory.initElements(DriverManager.init(), HomePage.class);
        hm.clickOnLogin();
    }

    public static void goToUseCasePage() {
        DashboardPage dp = PageFactory.initElements(DriverManager.init(), DashboardPage.class);
        dp.clickOnUseCasesCard();
    }
}
