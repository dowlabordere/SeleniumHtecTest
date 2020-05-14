package utils;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageobjects.EditUseCasePage;
import pageobjects.UseCasesPage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Utils {

    private Utils() {
        throw new IllegalStateException("Can't create instance of static util class");
    }

    public static List<String> getElementNames(List<WebElement> elements) throws InterruptedException {
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText());
        }
        return names;
    }

    public static void writeScenariosToFile(List<JSONObject> scenarios) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("scenarios.json");
            fw.write(scenarios.toString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fw.close();
        }
    }

    public static JSONObject populateObject(JSONObject json, String key, Object value) {
        json.put(key, value);
        return json;
    }

    public static List<JSONObject> readScenariosFromFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader("scenarios.json");
        Object obj = parser.parse(fr);
        JSONArray scenariosObject = (JSONArray) obj;
        List<JSONObject> scenariosObjects = new ArrayList<>();
        for (Object objects : scenariosObject) {
            scenariosObjects.add((JSONObject) objects);
        }
        return scenariosObjects;
    }


    public static void generateString(int number) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder randomString = new StringBuilder(number);
        for (int i = 0; i < number; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            randomString.append(AlphaNumericString.charAt(index));
        }
        writeRandomStringToFile(randomString.toString());
    }

    public static void writeRandomStringToFile(String randomString) {
        try (FileWriter file = new FileWriter("randomString.txt")) {
            file.write(randomString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMemorizedRandomString() {
        File file = new File("randomString.txt");
        String randomString = null;
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
        return " " + randomString;
    }

    public static void writesScenariosToFile(WebDriver driver) throws InterruptedException, IOException {
        UseCasesPage ucp = PageFactory.initElements(driver, UseCasesPage.class);
        List<WebElement> listOfUseCases = ucp.getUseCaseElements();
        List<String> useCaseNames = Utils.getElementNames(listOfUseCases);
        Assert.assertFalse(useCaseNames.isEmpty());
        List<JSONObject> scenarios = new ArrayList<>();

        for (String name : useCaseNames) {
            JSONObject json = new JSONObject();
            driver.findElement(By.xpath("//a[text()='" + name + "']")).click();
            EditUseCasePage eucp = PageFactory.initElements(driver, EditUseCasePage.class);
            Utils.populateObject(json, "title", eucp.getUseCaseTitle().getAttribute("value"));
            Utils.populateObject(json, "description", eucp.getUseCaseDescription().getText());
            Utils.populateObject(json, "expected result", eucp.getUseCaseExpRes().getAttribute("value"));

            List<WebElement> steps = eucp.getUseCaseStepElements();
            List<String> stepsStrings = new ArrayList<>();
            for (WebElement step : steps) {
                stepsStrings.add(step.getAttribute("value"));
            }
            Utils.populateObject(json, "steps", stepsStrings);
            eucp.backToDashboard();
            scenarios.add(json);
        }
        Utils.writeScenariosToFile(scenarios);
    }

}
