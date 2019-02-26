package pages;

import bo.User;
import decorator.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    public GmailMainPage(Browser browser) {
        super(browser);
    }

    public GmailMainPage enterSearchFieldSpace() {
        browser.waitForElementVisible(searchField);
        browser.highlightElement(searchField);
        browser.takeScreenshot();
        new Actions(browser).sendKeys(Keys.SPACE).build().perform();
        browser.unhighlightElement(searchField);
        return new GmailMainPage(browser);
    }

    public void doubleClickMoreButton() {
        browser.waitForElementVisible(expandMoreButton);
        browser.highlightElement(expandMoreButton);
        new Actions(browser).doubleClick().build().perform();
        browser.unhighlightElement(expandMoreButton);
    }

    public void scrollToTheTermsElement() {
        browser.waitForElementVisible(termsEndConditionsLink);
        browser.scrollUsingJJSotTheElement(termsEndConditionsLink);
        new Actions(browser).doubleClick().build().perform();
    }

    public GmailMainPage pressComposeButton() {
        browser.waitForElementVisible(composeButton);
        browser.highlightElement(composeButton);
        composeButton.click();
        browser.unhighlightElement(composeButton);
        return new GmailMainPage(browser);
    }

    public GmailMainPage fillRecipentInput(String recipientQuery) {
        browser.waitForElementVisible(recipientInput);
        browser.highlightElement(recipientInput);
        recipientInput.sendKeys(recipientQuery);
        browser.unhighlightElement(recipientInput);
        return new GmailMainPage(browser);
    }

    public GmailMainPage fillSubjectInput(User user) {
        browser.waitForElementVisible(subjectInput);
        browser.highlightElement(subjectInput);
        subjectInput.sendKeys(user.getSubjectInput());
        browser.unhighlightElement(subjectInput);
        return this;
    }

    public GmailMainPage fillBodyInput(User user) {
        browser.waitForElementVisible(bodyInput);
        bodyInput.sendKeys(user.getBodyInput());
        return this;
    }

    public GmailMainPage saveAndCloseEmail() {
        browser.waitForElementAndClick(saveAndClose);
        return this;
    }

    public GmailMainPage clickOnDraftsLink() {
        browser.waitForElementAndClick(draftsLink);
        return this;
    }

    public boolean isEmailAppearedInDrafts(String emailSubject, String emailBody) {
        browser.waitForElementVisible(By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%s')]/following::span[contains(text(), '%2s')]", emailSubject, emailBody)));
        return browser.findElement(By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%s')]/following::span[contains(text(), '%2s')]", emailSubject, emailBody))).isDisplayed();
    }


    public GmailMainPage clickOnDraftEmail(String emailSubject, String emailBody) {
        browser.waitForElementAndClick(browser, By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]", emailSubject, emailBody)));
        return this;
    }

    public GmailMainPage sendEmail() {
        browser.waitForElementAndClick(sendButton);
        return this;
    }

    public GmailMainPage clickOnSentLink() {
        browser.waitForElementAndClick(sentLink);
        return this;
    }

    public boolean isEmailAppearedInSentFolder(String emailSubject, String emailBody) {
        browser.waitForElementVisible(By.xpath(String.format(
                "//span[contains(text(), 'h.yantsevich')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]", emailSubject, emailBody)));
        return browser.findElement(By.xpath(String.format("//span[contains(text(), 'h.yantsevich')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]", emailSubject, emailBody))).isDisplayed();
    }

    public boolean isEmailDisappearedFromDrafts(String emailSubject, String emailBody) {
        browser.waitForElementInvisible(By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]", emailSubject, emailBody)));
        return browser.findElement(By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]", emailSubject, emailBody))).isDisplayed();
    }


    public GmailMainPage clickOnImageButton() {
        browser.waitForElementAndClick(imageButton);
        return this;
    }

    public void clickOnSignOutButton() {
        browser.waitForElementAndClick(signOutButton);
    }
}
