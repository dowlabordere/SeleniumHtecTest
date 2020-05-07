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

    public WebElement getTitle() {
        return title;
    }
    public WebElement getDescription() {
        return description;
    }
    public WebElement getExpectedResult() {
        return expectedResult;
    }
    public List getSteps() {
        return steps;
    }
    public void clickToSubmit() {
        submitUseCase.click();
    }
    public void backToDashboard(){ backButton.click(); }
    public void clickDeleteButton(){ deleteButton.click(); }
    public void deleteScenario(){ deleteScenario.click(); }

}
