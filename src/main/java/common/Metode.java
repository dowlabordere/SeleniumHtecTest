package common;

import framework.Base;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.UseCasesPage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Metode {

    WebDriver driver;

    public Metode(WebDriver driver) {
        driver = this.driver;
    }

    public List<String> getUseCaseNames() {
        return useCaseNames;
    }

    private List<String> useCaseNames = new ArrayList<String>();
    List<WebElement> listOfUseCases;
    private JSONObject scenarioObject;

    public void catchUseCasesNames(WebDriver driver) {
        useCaseNames.clear();
        Base.waitForElementToAppear(driver, By.xpath("//div[contains(@class,'list-group')]//a"), 5);
        listOfUseCases = driver.findElements(By.xpath("//div[contains(@class,'list-group')]//a"));
        for (WebElement we : listOfUseCases) {
            useCaseNames.add(we.getText());
        }
        scenarioObject = new JSONObject();
    }

    public void catchTestCasesNames() {
        catchUseCasesNames(driver);
        scenarioObject = new JSONObject();
        for (int i = 0; i < useCaseNames.size(); i++) {
            Base.waitForElementToAppear(driver, By.xpath("//a[text()='" + useCaseNames.get(i) + "']"), 5);
            driver.findElement(By.xpath("//a[text()='" + useCaseNames.get(i) + "']")).click();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()=' Edit Use Case']"), 5);

            JSONObject scenarioDetails = new JSONObject();
            JSONObject scenarioSteps = new JSONObject();
            scenarioDetails.put("title", driver.findElement(By.name("title")).getAttribute("value"));
            scenarioDetails.put("description", driver.findElement(By.name("description")).getText());
            scenarioDetails.put("expected result", driver.findElement(By.name("expected_result")).getAttribute("value"));
            List<WebElement> steps = driver.findElements(By.id("stepId"));
            String stepId;
            for (int j = 0; j < steps.size(); j++) {
                stepId = "testStepId-" + (j);
                scenarioSteps.put(stepId, driver.findElement(By.name(stepId)).getAttribute("value"));
            }
            String scenario = "Scenario " + (i + 1);
            scenarioDetails.put("test steps", scenarioSteps);
            scenarioObject.put(scenario, scenarioDetails);

            driver.findElement(By.xpath("//a[@href='/use-cases']")).click();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()='Use Cases']"), 5);
        }
    }

    public void writeScenariosToJson(){
        JSONArray scenarioList = new JSONArray();
        scenarioList.add(scenarioObject);
        try (FileWriter file = new FileWriter("scenarios.json")) {
            file.write(scenarioList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
