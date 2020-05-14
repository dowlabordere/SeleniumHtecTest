package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver init(String browser) {
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    driver.manage().window().maximize();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    ieOptions.setCapability("ignoreZoomSetting", true);
                    driver = new InternetExplorerDriver(ieOptions);
                    break;
                default:
                    System.out.println("Browser not supported");
            }
            return driver;
        }
        return driver;
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
