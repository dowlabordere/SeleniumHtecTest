package regression;

import framework.DriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.EditUseCasePage;
import pageobjects.Navigation;
import pageobjects.UseCasesPage;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.Utils.writesScenariosToFile;

public class UseCasesPageTests {

    private WebDriver driver;

    @BeforeTest
    public void init(){
        driver = DriverManager.init();
        driver.get("https://qa-sandbox.apps.htec.rs");
        Navigation.goToLoginPage();
        Navigation.loginUser();
        Navigation.goToUseCasePage();
    }

    @AfterTest
    public void preDestroy(){
        DriverManager.clearBrowserLogs();
        DriverManager.close();
    }

    @Test
    public void deleteAllUseCases() throws IOException, InterruptedException {
        // The method is called for writing scenarios to file before deleting them
        writesScenariosToFile(driver);
        List<WebElement>listOfUseCases = PageFactory.initElements(driver, UseCasesPage.class).getUseCaseElements();
        List<String> useCaseNames = Utils.getElementNames(listOfUseCases);
        Assert.assertFalse(useCaseNames.isEmpty());
        for (String name : useCaseNames) {
            driver.findElement(By.xpath("//a[text()='" + name+ "']")).click();
            EditUseCasePage eucp = PageFactory.initElements(driver, EditUseCasePage.class);
            Assert.assertTrue(eucp.getTitle().isDisplayed());
            Assert.assertTrue(eucp.getDescription().isDisplayed());
            Assert.assertTrue(eucp.getExpectedResult().isDisplayed());
            Assert.assertTrue(eucp.getDescription().isDisplayed());
            Assert.assertTrue(eucp.getBackButton().isDisplayed());
            eucp.clickDeleteButton();
            Assert.assertTrue(eucp.getDeleteScenario().isDisplayed());
            eucp.deleteScenario();
        }
    }

    @Test
    public void recreateDeletedUseCases() throws IOException, ParseException {
        EditUseCasePage eucp = PageFactory.initElements(driver, EditUseCasePage.class);
        for (JSONObject scenario : Utils.readScenariosFromFile()) {
            PageFactory.initElements(driver, UseCasesPage.class).createUseCase();
            Assert.assertTrue(eucp.getTitle().isDisplayed());
            Assert.assertTrue(eucp.getDescription().isDisplayed());
            Assert.assertTrue(eucp.getExpectedResult().isDisplayed());
            Assert.assertTrue(eucp.getDescription().isDisplayed());
            Assert.assertTrue(eucp.getBackButton().isDisplayed());
            eucp.getTitle().sendKeys(scenario.get("title").toString());
            eucp.getDescription().sendKeys(scenario.get("description").toString());
            eucp.getExpectedResult().sendKeys(scenario.get("expected result").toString());
            JSONArray steps = (JSONArray) scenario.get("steps");
            for (int i = 1; i<steps.size(); i++){
                eucp.addStep();
            }
            List<WebElement> stepList = eucp.getSteps();
            for (int i = 0; i<steps.size(); i++) {
                stepList.get(i).sendKeys(steps.get(i).toString());
            }
            eucp.clickToSubmit();
        }
    }

    @Test
    public void changeUseCases() throws InterruptedException {
        Utils.generateString(8);
        UseCasesPage ucp = PageFactory.initElements(driver, UseCasesPage.class);
        List<WebElement>listOfUseCases = ucp.getUseCaseElements();
        List<String> useCaseNames = Utils.getElementNames(listOfUseCases);
        Assert.assertFalse(useCaseNames.isEmpty());
        for (String name : useCaseNames) {
            EditUseCasePage eucp = PageFactory.initElements(driver, EditUseCasePage.class);
            String temp;
            driver.findElement(By.xpath("//a[text()='" + name+ "']")).click();
            temp = eucp.getTitle().getAttribute("value");
            Assert.assertTrue(eucp.getTitle().isDisplayed());
            Assert.assertTrue(eucp.getDescription().isDisplayed());
            Assert.assertTrue(eucp.getExpectedResult().isDisplayed());
            Assert.assertTrue(eucp.getDescription().isDisplayed());
            Assert.assertTrue(eucp.getBackButton().isDisplayed());
            eucp.getTitle().clear();
            eucp.getTitle().sendKeys(temp + Utils.getMemorizedRandomString());
            temp = eucp.getDescription().getText();
            eucp.getDescription().clear();
            eucp.getDescription().sendKeys(temp + Utils.getMemorizedRandomString());
            temp = eucp.getExpectedResult().getAttribute("value");
            eucp.getExpectedResult().clear();
            eucp.getExpectedResult().sendKeys(temp + Utils.getMemorizedRandomString());
            List<WebElement> steps = eucp.getUseCaseStepElements();
            List<String> stepsStrings = new ArrayList<>();
            for (WebElement step : steps) {
                temp = step.getAttribute("value");
                step.clear();
                step.sendKeys(temp + Utils.getMemorizedRandomString());
            }
            eucp.clickToSubmit();
        }
    }

    @Test
    public void deleteChangedUseCases() throws InterruptedException {
        UseCasesPage ucp = PageFactory.initElements(driver, UseCasesPage.class);
        List<WebElement>listOfUseCases = ucp.getUseCaseElements();
        List<String> useCaseNames = Utils.getElementNames(listOfUseCases);
        Assert.assertFalse(useCaseNames.isEmpty());
        for (String name : useCaseNames) {
            if(name.contains(Utils.getMemorizedRandomString())){
                driver.findElement(By.xpath("//a[text()='" + name+ "']")).click();
                EditUseCasePage eucp = PageFactory.initElements(driver, EditUseCasePage.class);
                Assert.assertTrue(eucp.getTitle().isDisplayed());
                Assert.assertTrue(eucp.getDescription().isDisplayed());
                Assert.assertTrue(eucp.getExpectedResult().isDisplayed());
                Assert.assertTrue(eucp.getDescription().isDisplayed());
                Assert.assertTrue(eucp.getBackButton().isDisplayed());
                eucp.clickDeleteButton();
                Assert.assertTrue(eucp.getDeleteScenario().isDisplayed());
                eucp.deleteScenario();
            }
        }
    }
}