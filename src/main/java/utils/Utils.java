package utils;

import framework.Base;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//    public static void waitForElementToAppear(WebDriver driver, WebElement locator, int seconds){
//        WebDriverWait wait =  new WebDriverWait(driver, seconds);
//        wait.until(ExpectedConditions.visibilityOf(locator));
//    }
//
//    public static void waitForElementToAppear(WebDriver driver, By locator, int seconds){
//        WebDriverWait wait =  new WebDriverWait(driver, seconds);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }


    public static void writeScenariosToFile(List<JSONObject> scenarios) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("scenarios.json");
            fw.write(scenarios.toString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fw.close();
        }
    }

    public static JSONObject populateObject(JSONObject json, String key, Object value){
        json.put(key, value);
        return json;
    }

}
