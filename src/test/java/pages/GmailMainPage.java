package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class GmailMainPage extends AbstractedPage {

    @FindBy(xpath = "//span[@class='ait']/div[@class='G-asx T-I-J3 J-J5-Ji']")
    private WebElement expandMoreButton;

    @FindBy(xpath = "//a[@href='https://www.google.com/intl/en/policies/terms/']")
    private WebElement termsEndConditionsLink;

    @FindBy(css = ".aic .z0 div")
    private WebElement composeButton;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement recipientInput;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    private WebElement bodyInput;

    @FindBy(xpath = "//td[@class='Hm']/img[@class='Ha']")
    private WebElement saveAndClose;

    @FindBy(xpath = "//a[contains(text(), 'Drafts')]")
    private WebElement draftsLink;

    @FindBy(xpath = "//div[text()='Send']")
    private WebElement sendButton;

    @FindBy(xpath = "//*[@title='Sent']")
    private WebElement sentLink;

    @FindBy(xpath = "//tr[@class='TD']/td[@class='TC']")
    private WebElement noDrafts;

    @FindBy(xpath = "//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")
    private WebElement imageButton;

    @FindBy(xpath = "//*[contains(text(), 'Sign out')]")
    private WebElement signOutButton;

    @FindBy(xpath = "//input[@placeholder = 'Search mail']")
    private WebElement searchField;


    public GmailMainPage(WebDriver driver) {
        super(driver);
    }

    public GmailMainPage enterSearchFieldSpace() {
        waitForElementVisible(searchField);
        highlightElement(driver, searchField);
        //takeScreenshot();
        new Actions(driver).sendKeys(Keys.SPACE).build().perform();
        unhighlightElement(driver, searchField);
        return new GmailMainPage(driver);
    }

    public void doubleClickMoreButton() {
        waitForElementVisible(expandMoreButton);
        highlightElement(driver, expandMoreButton);
        new Actions(driver).doubleClick().build().perform();
        unhighlightElement(driver, expandMoreButton);
    }

    public void scrollToTheBottom() {
        scrollUsingJS();
        new Actions(driver).doubleClick().build().perform();
    }

    public void scrollToTheTermsElement() {
        waitForElementVisible(termsEndConditionsLink);
        scrollUsingJJSotTheElement(termsEndConditionsLink);
        new Actions(driver).doubleClick().build().perform();
    }

    public GmailMainPage pressComposeButton() {
        waitForElementVisible(composeButton);
        highlightElement(driver, composeButton);
        composeButton.click();
        unhighlightElement(driver, composeButton);
        return new GmailMainPage(driver);
    }

    public GmailMainPage fillRecipentInput(String recipientQuery) {
        waitForElementVisible(recipientInput);
        highlightElement(driver, recipientInput);
        recipientInput.sendKeys(recipientQuery);
        unhighlightElement(driver, recipientInput);
        return new GmailMainPage(driver);
    }

    public GmailMainPage fillSubjectInput(String subjectQuery) {
        waitForElementVisible(subjectInput);
        highlightElement(driver, subjectInput);
        subjectInput.sendKeys(subjectQuery);
        unhighlightElement(driver, subjectInput);
        return this;
    }

    public GmailMainPage fillBodyInput(String bodyQuery) {
        waitForElementVisible(bodyInput);
        bodyInput.sendKeys(bodyQuery);
        return this;
    }

    public GmailMainPage saveAndCloseEmail() {
        waitForElementAndClick(driver, saveAndClose);
        return this;
    }

    public GmailMainPage clickOnDraftsLink() {
        waitForElementAndClick(driver, draftsLink);
        return this;
    }

    public boolean isEmailAppearedInDrafts(String emailBody) {
        waitForElementVisible(By.xpath(String.format("/descendant::span[contains(text(), '%s')][1]", emailBody)));
        return driver.findElement(By.xpath(String.format("/descendant::span[contains(text(), '%s')][1]", emailBody))).isDisplayed();
    }


    public GmailMainPage clickOnDraftEmail(String emailSubject) {
        waitForElementAndClick(driver, By.xpath(String.format("./descendant::span[contains(text(), '%s')][2]", emailSubject)));
        return this;
    }

    public GmailMainPage sendEmail() {
        waitForElementAndClick(driver, sendButton);
        return this;
    }

    public GmailMainPage clickOnSentLink() {
        waitForElementAndClick(driver, sentLink);
        return this;
    }

    public boolean isEmailAppearedInSentFolder(String emailBody) {
        waitForElementVisible(By.xpath(String.format("/descendant::span[contains(text(), '%s')][5]", emailBody)));
        return driver.findElement(By.xpath(String.format("/descendant::span[contains(text(), '%s')][5]", emailBody))).isDisplayed();
    }

    public boolean isEmailDisappearedFromDrafts(String emailBody) {
        return driver.findElement(By.xpath(String.format("/descendant::span[contains(text(), '%s')][1]", emailBody))).isDisplayed();
    }

    public GmailMainPage clickOnImageButton() {
        waitForElementAndClick(driver, imageButton);
        return this;
    }

    public void clickOnSignOutButton() {
        waitForElementAndClick(driver, signOutButton);
    }
}
