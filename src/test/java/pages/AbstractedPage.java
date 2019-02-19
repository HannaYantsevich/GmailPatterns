package pages;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;


public class AbstractedPage {


    protected static WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 15;
    private static final String SCREENSHOTS_NAME_TPL = "Screenshots/src";

    public static org.apache.log4j.Logger log = Logger.getLogger(AbstractedPage.class);

    public AbstractedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public boolean isElementExists(By by) {
        return !driver.findElements(by).isEmpty();
    }

    protected void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementVisible(WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }


    protected void waitForElementPresent(WebElement elem) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOf(elem));
    }


    public void waitForElementAndClick(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void waitForElementAndClick(WebDriver driver, By by) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void clickOnElementByJS(WebElement element) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", element);
    }

    public void scrollUsingJS() {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollUsingJJSotTheElement(WebElement Element) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].value=''", Element);
    }

    protected void highlightElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
    }

    protected void unhighlightElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }

    public static void takeScreenshot() {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            log.info("Saved screenshot:" + screenshotName);
        } catch (IOException e) {
            log.error("Failed to make screenshot " +e.getMessage());
        }
    }
}