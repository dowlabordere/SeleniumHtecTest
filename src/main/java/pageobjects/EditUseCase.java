package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditUseCase {

    @FindBy(name = "title")
    private WebElement title;
    @FindBy(name = "description")
    private WebElement description;
    @FindBy(name = "expected_result")
    private WebElement expectedResult;
    @FindBy(id = "stepId")
    private List<WebElement> steps;
    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submit;

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
        submit.click();
    }

}
