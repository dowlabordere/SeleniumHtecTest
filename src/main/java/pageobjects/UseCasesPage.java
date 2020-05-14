package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UseCasesPage {

    @FindBy(xpath = "//a[contains(@href,'/dashboard')]")
    private WebElement backButton;
    @FindBy(xpath = "//a[contains(@href,'/create-usecase')]")
    private WebElement createUseCaseButton;
    @FindBy(xpath = "//div[contains(@class,'list-group')]//a")
    private List<WebElement> useCaseElements;


    public List<WebElement> getUseCaseElements() {
        return useCaseElements;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getCreateUseCaseButton() {
        return createUseCaseButton;
    }

    public void backToDashboard() {
        getBackButton().click();
    }

    public void createUseCase() {
        getCreateUseCaseButton().click();
    }

}
