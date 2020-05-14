package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    public WebElement getProfileCard() {
        return profileCard;
    }

    public WebElement getUseCasesCard() {
        return useCasesCard;
    }

    @FindBy(xpath = "//h5[text()='Vladimir Jovanovic']")
    private WebElement profileCard;
    @FindBy(xpath = "//h5[text()='Use Cases']")
    private WebElement useCasesCard;

    public void clickOnProfileCard() {
        profileCard.click();
    }

    public void clickOnUseCasesCard() {
        useCasesCard.click();
    }


}
