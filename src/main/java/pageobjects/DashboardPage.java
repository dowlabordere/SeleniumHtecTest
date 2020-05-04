package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    @FindBy(xpath = "//h5[text()='Vladimir Jovanovic']")
    private WebElement profileCard;

    @FindBy(xpath = "//h5[text()='Use Cases']")
    WebElement useCasesCard;

    public void clickOnProfileCard(){
        profileCard.click();
    }

    public void clickOnUseCasesCard(){
        useCasesCard.click();
    }


}
