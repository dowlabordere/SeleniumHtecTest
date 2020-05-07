package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Base {

    public static void waitForElementToAppear(WebDriver driver, WebElement locator, int seconds){
        WebDriverWait wait =  new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public static void waitForElementToAppear(WebDriver driver, By locator, int seconds){
        WebDriverWait wait =  new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementToDisappear(WebDriver driver, By locator, int seconds){
        WebDriverWait wait =  new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


}
