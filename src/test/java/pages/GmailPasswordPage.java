package pages;

import bo.User;
import decorator.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailPasswordPage extends AbstractedPage {
    public GmailPasswordPage(Browser browser) {
        super(browser);
    }

    @FindBy(xpath = "//div[@class='Xb9hP']/input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id='passwordNext']")
    private WebElement nextButton;

    public GmailPasswordPage fillGmailPasswordInput(User user) {
        browser.waitForElementVisible(By.xpath("//div[@class='Xb9hP']/input[@type='password']"));
        passwordInput.sendKeys(user.getPassword());
        return new GmailPasswordPage(browser);
    }

    public GmailMainPage pressPasswordNextButton() {
        browser.waitForElementAndClick(nextButton);
        return new GmailMainPage(browser);
    }
}
