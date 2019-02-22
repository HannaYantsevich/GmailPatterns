package tests;


import decorator.Browser;
import factory.ChromeDriverCreator;
import factory.WebDriverCreator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import singleton.WebDriverSingleton;


import static utils.RandomString.getRandomString;


public class BaseTest {
    public static final String BASE_URL = "https://www.google.com/intl/ru/gmail/about/#";
    public static final String RECIPIENT_EMAIL = "h.yantsevich@gmail.com";
    public static final String EMAIL_SUBJECT = getRandomString(10);
    public static final String EMAIL_BODY = "Hello, World!";


    protected WebDriver driver;
    protected Browser browser;
    private Logger log = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void startBrowserAndOpenBaseURL() {
        log.info("Start browser");
        WebDriverCreator creator = new ChromeDriverCreator();
        driver = creator.createWebDriver();
        browser = new Browser(driver);

        browser.manage().deleteAllCookies();
        log.info("Navigate to home page");
        browser.get(BASE_URL);
    }

    @AfterClass
    public void closeBrowser() {
        log.info("Close browser");
        browser.quit();
    }
}
