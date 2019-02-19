package pages;


import bo.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends AbstractedPage {
    private Logger log = Logger.getLogger(GmailLoginPage.class);

    public GmailLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='gmail-nav__nav-links-wrap']/a[@class ='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    private WebElement signInButton;

    @FindBy(css = "input#identifierId.whsOnd.zHQkBf")
    private WebElement emailInput;

    @FindBy(id = "identifierNext")
    private WebElement nextButton;


    public GmailLoginPage pressSigninButton() {
        highlightElement(driver, signInButton);
        signInButton.click();
        return new GmailLoginPage(driver);
    }

    public GmailLoginPage fillEmailIInput(User user) {
        waitForElementVisible(emailInput);
        emailInput.sendKeys(user.getLogin());
        return new GmailLoginPage(driver);
    }

    public GmailPasswordPage pressNextButton() {
        waitForElementVisible(By.id("identifierNext"));
        nextButton.click();
        return new GmailPasswordPage(driver);
    }
}
