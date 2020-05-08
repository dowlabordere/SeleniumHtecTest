package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditUseCasePage {

    @FindBy(name = "title")
    private WebElement title;
    @FindBy(name = "description")
    private WebElement description;
    @FindBy(name = "expected_result")
    private WebElement expectedResult;
    @FindBy(id = "stepId")
    private List<WebElement> steps;
    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitUseCase;
    @FindBy(xpath = "//a[@href='/use-cases']")
    private WebElement backButton;
    @FindBy(xpath = "//span[contains(@class,'delete-button')]//button")
    private WebElement deleteButton;
    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement deleteScenario;
    @FindBy(name = "title")
    private WebElement useCaseTitle;
    @FindBy(name = "description")
    private WebElement useCaseDescription;
    @FindBy(name = "expected_result")
    private WebElement useCaseExpRes;
    @FindBy(id = "stepId")
    private List<WebElement> useCaseStepElements;
    @FindBy(xpath = "//span[text()='ADD STEP']")
    private WebElement addStep;

    public WebElement getSubmitUseCase() { return submitUseCase; }
    public WebElement getBackButton() { return backButton; }
    public WebElement getDeleteButton() { return deleteButton; }
    public WebElement getDeleteScenario() { return deleteScenario; }
    public WebElement getAddStep() { return addStep; }
    public WebElement getTitle() { return title; }
    public WebElement getDescription() {
        return description;
    }
    public WebElement getExpectedResult() {
        return expectedResult;
    }
    public List getSteps() { return steps; }
    public void clickToSubmit() {
        submitUseCase.click();
    }
    public void backToDashboard(){ backButton.click(); }
    public void clickDeleteButton(){ deleteButton.click(); }
    public void deleteScenario(){ deleteScenario.click(); }
    public WebElement getUseCaseTitle() { return useCaseTitle; }
    public WebElement getUseCaseDescription() { return useCaseDescription; }
    public WebElement getUseCaseExpRes() { return useCaseExpRes; }
    public List<WebElement> getUseCaseStepElements() { return useCaseStepElements; }
    public void addStep(){ addStep.click(); }

}
