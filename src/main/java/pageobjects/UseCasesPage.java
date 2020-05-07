package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import utils.Utils;

import java.util.List;

public class UseCasesPage {

    @FindBy(xpath = "//div[@class='container']/div/a[1]")
    private WebElement backButton;
    @FindBy(xpath = "//div[@class='container']/div/a[2]")
    private WebElement createUseCaseButton;
    @FindBy(xpath = "//div[contains(@class,'list-group')]//a")
    private List<WebElement> useCaseElements;
    @FindBy(name = "title")
    private WebElement useCaseTitle;
    @FindBy(name = "description")
    private WebElement useCaseDescription;
    @FindBy(name = "expected_result")
    private WebElement useCaseExpRes;
    @FindBy(id = "stepId")
    private List<WebElement> useCaseStepElements;

    public WebElement getUseCaseTitle() { return useCaseTitle; }
    public WebElement getUseCaseDescription() { return useCaseDescription; }
    public WebElement getUseCaseExpRes() { return useCaseExpRes; }
    public List<WebElement> getUseCaseStepElements() { return useCaseStepElements; }
    public List<WebElement> getUseCaseElements() { return useCaseElements; }
    public WebElement getBackButton() { return backButton; }
    public WebElement getCreateUseCaseButton() { return createUseCaseButton; }
    public void backToDashboard(){
        getBackButton().click();
    }
    public void createUseCase(){
        getCreateUseCaseButton().click();
    }


}
