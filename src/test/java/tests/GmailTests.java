package tests;

import bo.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GmailLoginPage;
import pages.GmailMainPage;
import pages.GmailPasswordPage;
import org.apache.log4j.Logger;

import static utils.RandomString.getRandomString;

public class GmailTests extends BaseTest {
    private static final String LOGIN = "HannaTest34@gmail.com";
    private static final String PASSWORD = "PasswordPassword";
    private static final String EMAIL_SUBJECT = getRandomString(10);
    private static final String EMAIL_BODY = getRandomString(20);
    private static final String RECIPIENT_EMAIL = "h.yantsevich@gmail.com";

    private Logger log = Logger.getLogger(BaseTest.class);

    @Test
    public void SendEmailFromDraftsTest() {


        log.info("Login into user`s account");
        User testUser = new User.UserBuilder(LOGIN, PASSWORD).withEmailSubject(EMAIL_SUBJECT).withEmailBody(EMAIL_BODY).build();

        GmailLoginPage gmailLoginPage = new GmailLoginPage(browser);
        gmailLoginPage.pressSigninButton()
                .fillEmailIInput(testUser);

        GmailPasswordPage gmailPasswordPage = gmailLoginPage.pressNextButton();
        gmailPasswordPage.fillGmailPasswordInput(testUser);
        GmailMainPage gmailMainPage = gmailPasswordPage.pressPasswordNextButton();

        log.info("Create email and save it in drafts");
        gmailMainPage.scrollToTheTermsElement();

        gmailMainPage.doubleClickMoreButton();
        gmailMainPage.pressComposeButton()
                .fillRecipentInput(RECIPIENT_EMAIL)
                .fillSubjectInput(testUser)
                .fillBodyInput(testUser)
                .saveAndCloseEmail()
                .clickOnDraftsLink();
        browser.takeScreenshot();


        log.info("Verify that email is saved in drafts");
        Assert.assertTrue(gmailMainPage.isEmailAppearedInDrafts(EMAIL_SUBJECT, EMAIL_BODY));
        gmailMainPage.clickOnDraftEmail(EMAIL_SUBJECT, EMAIL_BODY);


        log.info("Send email");
        gmailMainPage.sendEmail()
                .clickOnSentLink();

        log.info("Verify that email is appeared in sent folder");
        Assert.assertTrue(gmailMainPage.isEmailAppearedInSentFolder(EMAIL_SUBJECT, EMAIL_BODY));

        log.info("Verify that email disappeared from Drafts folder");
        gmailMainPage.clickOnDraftsLink();
        Assert.assertFalse(gmailMainPage.isEmailDisappearedFromDrafts(EMAIL_SUBJECT, EMAIL_BODY));



        log.info("Log out from user`s account");
        gmailMainPage.enterSearchFieldSpace() //checking actions using keyboard
                .clickOnImageButton()
                .clickOnSignOutButton();
    }
}
