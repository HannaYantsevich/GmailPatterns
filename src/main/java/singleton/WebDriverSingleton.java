package singleton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static WebDriver instance;

    private static Logger log = Logger.getLogger(WebDriverSingleton.class);
    private WebDriverSingleton() {

    }

    public static WebDriver getWebDriverInstance() {
        if(instance!=null) {
            return instance;
        }
        return instance = init();
    }

    private static WebDriver init() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        return driver;
    }

    public static void kill() {
        if (instance !=null) {
            try {
                instance.quit();
            }catch (Exception e){
                log.error("Cannot kill browser"  +e.getMessage());
            }finally {
                instance=null;
            }
        }
    }
}
