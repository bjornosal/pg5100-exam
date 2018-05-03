package no.salbjo16.exams.selenium.po;

import no.salbjo16.exams.selenium.PageObject;
import no.salbjo16.exams.selenium.po.ui.MessagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public abstract class LayoutPO extends PageObject {

    public LayoutPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public LayoutPO(PageObject other) {
        super(other);
    }

    public SignUpPO toSignUp() {
        clickAndWait("linkToSignUpId");

        SignUpPO signUpPO = new SignUpPO(this);
        assertTrue(signUpPO.isOnPage());
        return signUpPO;
    }

    public IndexPO doLogout(){

        clickAndWait("logoutId");

        IndexPO po = new IndexPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public IndexPO doLogin() {
        clickAndWait("linkToLoginId");
        IndexPO po = new IndexPO(this);
        assertTrue(po.isOnPage());
        return po;

    }

    public MessagePO toMessages() {
        clickAndWait("messagesId");

        MessagePO messagePO = new MessagePO(this);
        assertTrue(messagePO.isOnPage());
        return messagePO;
    }

    public boolean isLoggedIn(){

        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements((By.id("linkToSignupId"))).isEmpty();
    }
}
