package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

    private static WebDriver driver;

    public static WebDriver startBrowser(String browserName) {
        if(driver==null){
            if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("IE")) {
                driver = new InternetExplorerDriver();
            }
            return driver;
        } else return driver;
    }

    public WebDriver getDriver(){
        return driver;
    }

}
