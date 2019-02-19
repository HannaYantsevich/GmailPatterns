package decorator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class WebDriverDecorator {

    private WebDriver driver;

    @Test
    public void testCustomDriver() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver = new CustomDriverDecorator(driver);
        driver.navigate().to("https://www.google.com/intl/ru/gmail/about/#");
        driver.findElement(By.xpath("//div[@class='gmail-nav__nav-links-wrap']/a[@class ='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")).click();
        driver.quit();
    }
}
