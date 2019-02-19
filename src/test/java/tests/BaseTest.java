package tests;


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
    private Logger log = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void startBrowserAndOpenBaseURL() {
        WebDriverManager.chromedriver().setup();

        log.info("Start browser");
        driver = new ChromeDriver();
        //this.driver = WebDriverSingleton.getWebDriverInstance();
        //WebDriverCreator creator = new ChromeDriverCreator();
        //driver = creator.createWebDriver();
        driver.manage().deleteAllCookies();
        //driver.manage().window().maximize();

        log.info("Navigate to home page");
        driver.get(BASE_URL);
    }

    @AfterClass
    public void closeBrowser() {
        log.info("Close browser");
        driver.quit();
        //WebDriverSingleton.kill();
    }
}
