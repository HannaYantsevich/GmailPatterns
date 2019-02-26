package tests;


import decorator.Browser;
import factory.ChromeDriverCreator;
import factory.WebDriverCreator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import static utils.RandomString.getRandomString;


public class BaseTest {
    public static final String BASE_URL = "https://www.google.com/intl/ru/gmail/about/#";



    protected Browser browser;
    private Logger log = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void startBrowserAndOpenBaseURL() {
        log.info("Start browser");
        WebDriverCreator creator = new ChromeDriverCreator();
        WebDriver driver = creator.createWebDriver();
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
