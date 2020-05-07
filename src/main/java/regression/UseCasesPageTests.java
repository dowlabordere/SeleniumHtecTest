package regression;

import common.Metode;
import framework.Base;
import framework.DriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageobjects.EditUseCasePage;
import pageobjects.UseCasesPage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UseCasesPageTests {

    private WebDriver driver;
    private List<String> useCaseNames = new ArrayList<String>();
    private List<JSONObject> scenarioObjects = new ArrayList<>();
    private JSONObject scenarioObject;
    private String randomString;


    @BeforeSuite
    public void setupDriver(){ driver = DriverManager.getInstance(); }

    @BeforeTest
    public void goToPage(){ driver.get("https://qa-sandbox.apps.htec.rs/use-cases"); }

    @AfterTest
    public void clearBrowser(){ DriverManager.clearBrowserLogs(); }

    @AfterSuite
    public void closeBrowser(){ DriverManager.close(); }

    @Test
    public void deleteAllUseCasesTestsAndWriteToJson(){
        UseCasesPage ucp = PageFactory.initElements(driver, UseCasesPage.class);
        Base.waitForElementToAppear(driver, ucp.getCreateUseCaseButton(),5);
        Base.waitForElementToAppear(driver, ucp.getBackButton(),5);
        Metode metode = new Metode(driver);
        metode.catchTestCasesNames();
        metode.writeScenariosToJson();
        metode.catchUseCasesNames(driver);
        EditUseCasePage eucp = PageFactory.initElements(driver, EditUseCasePage.class);
        for (int i = 0; i < metode.getUseCaseNames().size(); i++) {
            driver.findElement(By.xpath("//a[text()='" + metode.getUseCaseNames().get(i) + "']")).click();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()=' Edit Use Case']"), 5);
            driver.findElement(By.xpath("//i[contains(@class,'fa-trash-alt')]/parent::button")).click();
            Base.waitForElementToAppear(driver, By.xpath("//button[text()='Delete']"), 5);
            driver.findElement(By.xpath("//button[text()='Delete']")).click();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()='Use Cases']"), 5);
        }
    }

    // Delete all test cases with writing them to txt file
    @Test(priority = 0)
    public void SSSdeleteAllUseCasesTestsAndWriteToJson() {
//        goToUseCasesList();
//        catchTestCasesNames();
//        writeScenarioToJson();
        deleteAllTestCases();
    }

    // Read from txt and write to UI
    @Test(priority = 1)
    public void readFromJsonAndWriteUseCasesOnUi() {
//        goToUseCasesList();
        readFromJson();
        writeToUi();
    }

    // Will edit all properties in all use cases
    @Test(priority = 2)
    public void editPropertiesToAllUseCases() {
//        goToUseCasesList();
        catchUseCasesNames();
        randomString = getRandomString(7);
        writeRandomStringToTxt();
        editProperties();
    }

    // WILL DELETE ALL EDITED USE CASES WHICH MEANS THAT IT WILL DELETE ALL USE CASES
    @Test(priority = 3)
    public void deleteAllEditedUseCases(){
//        goToUseCasesList();
        catchUseCasesNames();
        deleteEditedOnes();
    }

    // Go to use cases list
//    @Test
    public void goToUseCasesTest1() {
        goToUseCasesList();
        driver.close();
    }

    // Delete all test cases without writing them to txt file
//    @Test()
    public void deleteAllUseCasesTest() {
        goToUseCasesList();
        deleteAllTestCases();
    }

    // Catching test cases to txt
//    @Test
    public void catchAllUseCasesToJson() {
        goToUseCasesList();
//        catchTestCases();
        writeScenarioToJson();
        readFromJson();
    }

    // Will get random string that has been written in randomString.txt file
    private void getMemorizedRandomString(){
        File file = new File("randomString.txt");
        if (file.exists()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scanner.hasNextLine()) {
                randomString = scanner.nextLine();
            }
            scanner.close();
        }
    }

    // Will delete all use cases that have been edited
    private void deleteEditedOnes() {
        getMemorizedRandomString();
        for (int i = 0; i < useCaseNames.size(); i++) {
            Base.waitForElementToAppear(driver, By.xpath("//a[text()='" + useCaseNames.get(i) + "']"), 5);
            if(useCaseNames.get(i).contains(randomString)){
                driver.findElement(By.xpath("//a[text()='" + useCaseNames.get(i) + "']")).click();
                Base.waitForElementToAppear(driver, By.xpath("//b[text()=' Edit Use Case']"), 5);
                driver.findElement(By.xpath("//i[contains(@class,'fa-trash-alt')]/parent::button")).click();
                Base.waitForElementToAppear(driver, By.xpath("//button[text()='Delete']"), 5);
                driver.findElement(By.xpath("//button[text()='Delete']")).click();
                Base.waitForElementToAppear(driver, By.xpath("//b[text()='Use Cases']"), 5);
            }
        }
    }

    // Will edit all properties
    private void editProperties() {
        for (int i = 0; i < useCaseNames.size(); i++) {
            Base.waitForElementToAppear(driver, By.xpath("//a[text()='" + useCaseNames.get(i) + "']"), 5);
            driver.findElement(By.xpath("//a[text()='" + useCaseNames.get(i) + "']")).click();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()=' Edit Use Case']"), 5);
            EditUseCasePage euc = PageFactory.initElements(driver, EditUseCasePage.class);
            editProperty(euc.getTitle());
            editProperty(euc.getDescription());
            editProperty(euc.getExpectedResult());
            editStepProperty(euc.getSteps());
            euc.clickToSubmit();
        }
    }

    // Will edit properties based on tag name
    private void editProperty(WebElement we) {
        String temp = null;
        if (we.getTagName().equals("input")) {
            if (we.getAttribute("value") != null) {
                temp = we.getAttribute("value");
            }
        } else if (we.getTagName().equals("textarea")) {
            if (we.getText() != null) {
                temp = we.getText();
            }
        }
        we.clear();
        we.sendKeys(temp + randomString);
    }

    // Will edit steps properties
    public void editStepProperty(List steps) {
        String temp = null;
        WebElement we;
        Iterator<WebElement> itr = steps.iterator();
        while (itr.hasNext()) {
            we = itr.next();
            if (we.getAttribute("value") != null) {
                temp = we.getAttribute("value");
            }
            we.clear();
            we.sendKeys(temp + randomString);
        }
    }

    // Will make random string
    private String getRandomString(int number) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(number);
        for (int i = 0; i < number; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return " " + sb.toString();
    }

    // Will go to use cases list
    private void goToUseCasesList() {
//        DashboardPageTests dpt = new DashboardPageTests();
//        dpt.useCasesCard();
//        driver = DriverManager.getInstance();
//        Base.waitForElementToAppear(driver, By.xpath("//a[text()='CREATE USE CASE']"), 5);
//        UseCasesPage ucp = PageFactory.initElements(driver, UseCasesPage.class);
//        Base.waitForElementToAppear(driver, By.xpath("//b[text()='Use Cases']"), 5);
    }

    // Will catch use cases names
    public void catchUseCasesNames() {
        useCaseNames.clear();
        Base.waitForElementToAppear(driver, By.xpath("//div[contains(@class,'list-group')]//a"), 5);
        List<WebElement> listOfUseCases = driver.findElements(By.xpath("//div[contains(@class,'list-group')]//a"));
        for (WebElement we : listOfUseCases) {
            useCaseNames.add(we.getText());
        }
    }

    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    ////// WILL DELETE ALL USE CASES WITHOUT WRITING THEM TO JSON ///////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    // IN CASE OF ACCIDENTAL DELETE THERE IS scenariosBACKUP.json THAT //
    // NEEDS TO BE RENAMED TO scenarios.json AND START //////////////////
    // readFromJsonAndWriteUseCasesOnUi() test //////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    private void deleteAllTestCases() {
        catchUseCasesNames();
        for (int i = 0; i < useCaseNames.size(); i++) {
            driver.findElement(By.xpath("//a[text()='" + useCaseNames.get(i) + "']")).click();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()=' Edit Use Case']"), 5);
            driver.findElement(By.xpath("//i[contains(@class,'fa-trash-alt')]/parent::button")).click();
            Base.waitForElementToAppear(driver, By.xpath("//button[text()='Delete']"), 5);
            driver.findElement(By.xpath("//button[text()='Delete']")).click();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()='Use Cases']"), 5);
        }
    }

    // Will take all of properties inside use cases
    private void catchTestCasesNames() {
        catchUseCasesNames();
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

    // Will write scenario to json for future needs
    private void writeScenarioToJson(){
        JSONArray scenarioList = new JSONArray();
        scenarioList.add(scenarioObject);
        try (FileWriter file = new FileWriter("scenarios.json")) {
            file.write(scenarioList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Will write random generated string to txt file for future needs
    private void writeRandomStringToTxt(){
        try (FileWriter file = new FileWriter("randomString.txt")) {
            file.write(randomString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Will read use case from json
    private void readFromJson() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("scenarios.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray scenarioList = (JSONArray) obj;
            scenarioList.forEach(emp -> parseAndWrite((JSONObject) emp));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Will parse and write json
    private void parseAndWrite(JSONObject object) {
        String scenario = "Scenario ";
        for (int i = 0; i < object.size(); i++) {
            scenarioObjects.add((JSONObject) object.get(scenario + (i + 1)));
        }
    }

    // Will write use case on UI
    private void writeToUi() {
        UseCasesPage ucp = PageFactory.initElements(driver, UseCasesPage.class);
        EditUseCasePage euc = PageFactory.initElements(driver, EditUseCasePage.class);
        for (int i = 0; i < scenarioObjects.size(); i++) {
            scenarioObject = scenarioObjects.get(i);
            Base.waitForElementToAppear(driver, By.xpath("//div[@class='container']/div/a[2]"), 5);
            ucp.createUseCase();
            Base.waitForElementToAppear(driver, By.xpath("//b[text()=' New Use Case']"), 5);

            euc.getTitle().sendKeys(scenarioObject.get("title").toString());
            euc.getDescription().sendKeys(scenarioObject.get("description").toString());
            euc.getExpectedResult().sendKeys(scenarioObject.get("expected result").toString());

            JSONObject stepsObject = (JSONObject) scenarioObject.get("test steps");

            for (int j = 1; j < stepsObject.size(); j++) {
                Base.waitForElementToAppear(driver, By.xpath("//span[text()='ADD STEP']"), 5);
                driver.findElement(By.xpath("//span[text()='ADD STEP']")).click();
            }
            String step = "testStepId-";
            for (int j = 0; j < stepsObject.size(); j++) {
                driver.findElement(By.name(step + j)).sendKeys(stepsObject.get(step + j).toString());
            }
            driver.findElement(By.xpath("//button[text()='Submit']")).click();
        }
    }

}