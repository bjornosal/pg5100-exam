package no.salbjo16.exams.selenium;

import no.salbjo16.exams.selenium.po.IndexPO;
import no.salbjo16.exams.selenium.po.SignUpPO;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public abstract class SeleniumTestBase {

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();

    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId() {
        return "foo_SeleniumLocalIT_" + counter.getAndIncrement() + "@test.com";
    }


    private IndexPO home;


    private IndexPO createNewUser(String email, String password, String name, String surname){

        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.createUser(email,password,name,surname);
        assertNotNull(indexPO);

        return indexPO;
    }

    @Before
    public void initTest() {

        /*
            we want to have a new session, otherwise the tests
            will share the same Session beans
         */
        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());

        home.toStartingPage();

        assertTrue("Failed to start from Home Page", home.isOnPage());
    }


    @Test
    public void testCreateAndLogoutUser() {

        assertFalse(home.isLoggedIn());

        String email = getUniqueId();
        String password = "123456789";
        String name = "TestName";
        String surname = "TestSurname";

        home = createNewUser(email,password,name,surname);

        assertTrue(home.isLoggedIn());
        assertTrue(home.getDriver().getPageSource().contains(name));

        home.doLogout();

        assertFalse(home.isLoggedIn());
        assertFalse(home.getDriver().getPageSource().contains(name));
    }

    @Test
    public void testDefaultBooks() {
        assertTrue(home.isOnPage());
        assertFalse(home.isLoggedIn());
        assertTrue(home.defaultBooksAreDisplayed());
    }

    @Test
    public void testRegisterSelling() {
        assertFalse(home.isLoggedIn());
        assertTrue(home.isOnPage());
        assertFalse(home.isForSaleMarkerDisplayed("booksTable:0:"));
        assertFalse(home.isSellButtonDisplayed("booksTable:0:"));


    }




}
