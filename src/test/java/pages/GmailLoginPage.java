package pages;

import bo.User;
import decorator.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends AbstractedPage {
    private Logger log = Logger.getLogger(GmailLoginPage.class);

    public GmailLoginPage(Browser browser) {
        super(browser);
    }

    @FindBy(xpath = "//div[@class='gmail-nav__nav-links-wrap']/a[@class ='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    private WebElement signInButton;

    @FindBy(css = "input#identifierId.whsOnd.zHQkBf")
    private WebElement emailInput;

    @FindBy(id = "identifierNext")
    private WebElement nextButton;


    public GmailLoginPage pressSigninButton() {
        browser.highlightElement(signInButton);
        signInButton.click();
        return new GmailLoginPage(browser);
    }

    public GmailLoginPage fillEmailIInput(User user) {
        browser.waitForElementVisible(emailInput);
        emailInput.sendKeys(user.getLogin());
        return new GmailLoginPage(browser);
    }

    public GmailPasswordPage pressNextButton() {
        browser.waitForElementVisible(By.id("identifierNext"));
        nextButton.click();
        return new GmailPasswordPage(browser);
    }
}
