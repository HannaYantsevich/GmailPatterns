package tests;

import bo.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GmailLoginPage;
import pages.GmailMainPage;
import pages.GmailPasswordPage;
import org.apache.log4j.Logger;

public class GmailTests extends BaseTest {
    public Logger log = Logger.getLogger(BaseTest.class);


    @Test
    public void SendEmailFromDraftsTest() {

/*
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
*/
        log.info("Login into user`s account");
        GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
        gmailLoginPage.pressSigninButton()
                .fillEmailIInput(new User());

        GmailPasswordPage gmailPasswordPage = gmailLoginPage.pressNextButton();
        gmailPasswordPage.fillGmailPasswordInput(new User());
        GmailMainPage gmailMainPage = gmailPasswordPage.pressPasswordNextButton();

        log.info("Create email and save it in drafts");
        gmailMainPage.scrollToTheTermsElement();

        gmailMainPage.doubleClickMoreButton();
        gmailMainPage.pressComposeButton()
                .fillRecipentInput(RECIPIENT_EMAIL)
                .fillSubjectInput(EMAIL_SUBJECT)
                .fillBodyInput(EMAIL_BODY)
                .saveAndCloseEmail()
                .clickOnDraftsLink()
                .takeScreenshot();


        log.info("Verify that email is saved in drafts");
        gmailMainPage.clickOnDraftEmail(EMAIL_SUBJECT);
        Assert.assertTrue(gmailMainPage.isEmailAppearedInDrafts(EMAIL_BODY));

        log.info("Send email");
        gmailMainPage.sendEmail()
                .clickOnSentLink();

        log.info("Verify that email is appeared in sent folder");
        Assert.assertTrue(gmailMainPage.isEmailAppearedInSentFolder(EMAIL_BODY));

        log.info("Verify that email disappeared from Drafts folder");
        Assert.assertFalse(gmailMainPage.isEmailDisappearedFromDrafts(EMAIL_BODY));
        gmailMainPage.clickOnDraftsLink();


        log.info("Log out from user`s account");
        gmailMainPage.enterSearchFieldSpace() //checking actions using keyboard

                .clickOnImageButton()
                .clickOnSignOutButton();
    }
}
