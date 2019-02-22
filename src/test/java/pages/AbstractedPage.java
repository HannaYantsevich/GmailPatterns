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


    protected WebDriver driver;

    private static final String SCREENSHOTS_NAME_TPL = "Screenshots/src";

    public static org.apache.log4j.Logger log = Logger.getLogger(AbstractedPage.class);

    public AbstractedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }







}