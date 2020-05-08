package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver init() {

        if(driver==null){
            ChromeOptions chromeOptions = new ChromeOptions();
            if(!System.getProperty("os.name").contains("Windows")){
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            } else System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            chromeOptions.addArguments("--start-maximized");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            return driver;

        } return driver;
    }

    public static void close() {
        driver.close();
        driver = null;
    }

    public static void clearBrowserLogs() {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        jsDriver.executeScript("console.clear();");
    }
}
