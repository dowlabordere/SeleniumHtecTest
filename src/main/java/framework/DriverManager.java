package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getInstance() {

        if(driver==null){
            ChromeOptions chromeOptions = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            chromeOptions.addArguments("--start-maximized");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            return driver;
        } return driver;
    }

    public static void close() {
        driver.quit();
    }

    public static void waitFor(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearBrowserLogs(){
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        jsDriver.executeScript("console.clear();");

    }

    public static boolean driverExists(){
        return driver != null;
    }
    public static void waitForSeconds(int sec) {
        waitFor(sec*1000);
    }
    public static String getTitle(){
        return driver.getTitle();
    }
    public static String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}
