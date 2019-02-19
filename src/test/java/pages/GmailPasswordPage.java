package pages;

import bo.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailPasswordPage extends AbstractedPage {
    public GmailPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='Xb9hP']/input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id='passwordNext']")
    private WebElement nextButton;

    public GmailPasswordPage fillGmailPasswordInput(User user) {
        waitForElementVisible(By.xpath("//div[@class='Xb9hP']/input[@type='password']"));
        passwordInput.sendKeys(user.getPassword());
        return new GmailPasswordPage(driver);
    }

    public GmailMainPage pressPasswordNextButton() {
        waitForElementAndClick(driver, nextButton);
        return new GmailMainPage(driver);
    }
}
