package decorator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Browser implements WebDriver {

    protected WebDriver driver;
    private WebElement element;
    protected JavascriptExecutor jsExecutor;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 15;
    private static final String SCREENSHOTS_NAME_TPL = "Screenshots/src";
    public static org.apache.log4j.Logger log = Logger.getLogger(Browser.class);


    public Browser(WebDriver driver) {
        this.jsExecutor = (JavascriptExecutor) (driver);
        this.driver = driver;
    }

    public void get(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        Reporter.log(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()),
                true);
        return driver.findElement(by);
    }

    public boolean isElementExists(By by) {
        return !driver.findElements(by).isEmpty();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        System.out.println("Browser will be closed now...");
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }

    public Object executeScript(String s, Object... objects) {
        return jsExecutor.executeScript(s, objects);
    }

    public Object executeAsyncScript(String s, Object... objects) {
        return jsExecutor.executeAsyncScript(s, objects);
    }

    public void highlightElement(WebDriver driver, WebElement element) {
        jsExecutor.executeScript("arguments[0].style.border='3px solid green'", element);
    }

    public void unhighlightElement(WebDriver driver, WebElement element) {
        jsExecutor.executeScript("arguments[0].style.border='0px'", element);
    }

    public void takeScreenshot() {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            log.info("Saved screenshot:" + screenshotName);
        } catch (IOException e) {
            log.error("Failed to make screenshot " + e.getMessage());
        }
    }

    public void waitForElementVisible(WebElement element) {
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

    public void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}

