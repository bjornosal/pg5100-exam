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

    public IndexPO doLogin(String email, String password) {
        clickAndWait("linkToLoginId");
        IndexPO po = new IndexPO(this);

        setText("username", email);
        setText("password", password);
        clickAndWait("submit");

        assertTrue(po.isOnPage());

        return po;

    }

    public MessagePO toMessages() {
        clickAndWait("messagesId");

        MessagePO messagePO = new MessagePO(this);
        assertTrue(messagePO.isOnPage());
        return messagePO;
    }

    public IndexPO doLoginWithDisabledUser(String email, String password) {
        clickAndWait("linkToLoginId");
        IndexPO po = new IndexPO(this);

        setText("username", email);
        setText("password", password);
        clickAndWait("submit");

//Ignoring this line as it will not work with a disabled user.
//      assertTrue(po.isOnPage());

        return po;


    }

    public boolean isLoggedIn(){

        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements((By.id("linkToSignupId"))).isEmpty();
    }
}
