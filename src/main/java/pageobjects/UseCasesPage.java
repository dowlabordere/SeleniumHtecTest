package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UseCasesPage {

    @FindBy(xpath = "//div[@class='container']/div/a[1]")
    private WebElement backButton;
    @FindBy(xpath = "//div[@class='container']/div/a[2]")
    private WebElement createUseCaseButton;

    public void backToDashboard(){
        getBackButton().click();
    }

    public void createUseCase(){
        getCreateUseCaseButton().click();
    }

    public WebElement getBackButton() { return backButton; }

    public WebElement getCreateUseCaseButton() { return createUseCaseButton; }
}
