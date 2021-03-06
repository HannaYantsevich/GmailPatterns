package pages;

import decorator.Browser;
import org.openqa.selenium.support.PageFactory;


public abstract class AbstractedPage {

    protected final Browser browser;

    public AbstractedPage(Browser browser) {
        this.browser = browser;
        PageFactory.initElements(this.browser, this);
    }
}