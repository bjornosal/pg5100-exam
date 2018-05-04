package no.salbjo16.exams.selenium;

import no.salbjo16.exams.backend.service.UserService;
import no.salbjo16.exams.selenium.po.BookDetailPO;
import no.salbjo16.exams.selenium.po.IndexPO;
import no.salbjo16.exams.selenium.po.SignUpPO;
import no.salbjo16.exams.selenium.po.admin.BookRegistryPO;
import no.salbjo16.exams.selenium.po.ui.MessagePO;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public abstract class SeleniumTestBase {

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();

    private static final AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    private UserService userService;

    private BookDetailPO detail;
    private BookRegistryPO registry;
    private MessagePO message;
    private IndexPO home;

    private final String BOOKS_TABLE = "booksTable:";
    private final String ROW_TO_TEST = BOOKS_TABLE + "0:";


    private String getUniqueId() {
        return "foo_SeleniumLocalIT_" + counter.getAndIncrement() + "@test.com";
    }

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
        final long ORIGINAL_AMOUNT_OF_BOOKS_FOR_SALE_ON_TEST_ROW = home.getSellersForBookOnRow(ROW_TO_TEST);

        assertFalse(home.isLoggedIn());
        assertTrue(home.isOnPage());
        assertFalse(home.isForSaleMarkerDisplayed(ROW_TO_TEST));
        assertFalse(home.isSellButtonDisplayed(ROW_TO_TEST));

        try {
            home.clickToSellBook(ROW_TO_TEST);
            fail();
        } catch(Exception e) {
            //Do nothing
        }

        String email = getUniqueId();
        String password = "123456789";
        String name = "TestName";
        String surname = "TestSurname";

        home = createNewUser(email,password,name,surname);

        assertTrue(home.isLoggedIn());
        assertTrue(home.getDriver().getPageSource().contains(name));

        assertTrue(home.isForSaleMarkerDisplayed(ROW_TO_TEST));
        assertTrue(home.isSellButtonDisplayed(ROW_TO_TEST));

        //checking other books
        for(int i = 1; i < home.getAmountOfBooks(); i++) {
            assertEquals(ORIGINAL_AMOUNT_OF_BOOKS_FOR_SALE_ON_TEST_ROW, home.getSellersForBookOnRow(ROW_TO_TEST));
        }

        //At original value
        assertEquals(ORIGINAL_AMOUNT_OF_BOOKS_FOR_SALE_ON_TEST_ROW, home.getSellersForBookOnRow(ROW_TO_TEST));
        home.clickToSellBook(ROW_TO_TEST);
        //UP to one
        assertEquals(ORIGINAL_AMOUNT_OF_BOOKS_FOR_SALE_ON_TEST_ROW+1, home.getSellersForBookOnRow(ROW_TO_TEST));

        for(int i = 1; i < home.getAmountOfBooks(); i++) {
            assertEquals(0, home.getSellersForBookOnRow(BOOKS_TABLE+i+":"));
        }

        //Down to original value
        home.clickToSellBook(ROW_TO_TEST);
        assertEquals(ORIGINAL_AMOUNT_OF_BOOKS_FOR_SALE_ON_TEST_ROW, home.getSellersForBookOnRow(ROW_TO_TEST));

        //Back up one
        home.clickToSellBook(ROW_TO_TEST);
        assertEquals(ORIGINAL_AMOUNT_OF_BOOKS_FOR_SALE_ON_TEST_ROW+1, home.getSellersForBookOnRow(ROW_TO_TEST));

        //Asserting counter is 1 after logouts
        home.doLogout();
        assertEquals(ORIGINAL_AMOUNT_OF_BOOKS_FOR_SALE_ON_TEST_ROW+1, home.getSellersForBookOnRow(ROW_TO_TEST));
    }

    @Test
    public void testBookDetails() {
        String emailOne = getUniqueId();
        String passwordOne = "password";
        String nameOne = "nameOne";
        String surnameOne = "surnameOne";

        String emailTwo= getUniqueId();
        String passwordTwo = "passwordTwo";
        String nameTwo = "nameTwo";
        String surnameTwo = "surnameTwo";

        assertFalse(home.isLoggedIn());
        home = createNewUser(emailOne, passwordOne, nameOne, surnameOne);
        assertTrue(home.isLoggedIn());
        home.clickToSellBook(ROW_TO_TEST);
        long sellers = home.getSellersForBookOnRow(ROW_TO_TEST);
        detail = home.toDetailOfBook(ROW_TO_TEST);


        boolean userOneIsMarkedAsSeller = false;
        for(int i = 0; i < sellers ; i++) {
            if(detail.getSeller("sellerTable:"+i+":").equalsIgnoreCase(emailOne)) {
                userOneIsMarkedAsSeller = true;
            }
        }
        assertTrue(userOneIsMarkedAsSeller);

        //Logging out with user one
        home.doLogout();
        assertFalse(home.isLoggedIn());

        //Logging in with user two
        createNewUser(emailTwo, passwordTwo, nameTwo, surnameTwo);
        assertTrue(home.isLoggedIn());

        detail = home.toDetailOfBook(ROW_TO_TEST);

        userOneIsMarkedAsSeller = false;
        for(int i = 0; i < sellers ; i++) {
            if(detail.getSeller("sellerTable:"+i+":").equalsIgnoreCase(emailOne)) {
                userOneIsMarkedAsSeller = true;
            }
        }
        assertTrue(userOneIsMarkedAsSeller);

        home.doLogout();
        assertFalse(home.isLoggedIn());
    }

    @Test
    public void testMessages() {
        String emailOne = getUniqueId();
        String passwordOne = "password";
        String nameOne = "nameOne";
        String surnameOne = "surnameOne";

        String emailTwo = getUniqueId();
        String passwordTwo = "passwordTwo";
        String nameTwo = "nameTwo";
        String surnameTwo = "surnameTwo";

        String messageText = "TEST_MESSAGE";
        String replyText = "TEST_REPLY";

        assertFalse(home.isLoggedIn());
        home = createNewUser(emailOne, passwordOne, nameOne, surnameOne);
        assertTrue(home.isLoggedIn());

        home.clickToSellBook(ROW_TO_TEST);
        home.doLogout();

        //Logging out user one.
        assertFalse(home.isLoggedIn());

        //Logging in user two.
        createNewUser(emailTwo, passwordTwo, nameTwo, surnameTwo);
        assertTrue(home.isLoggedIn());

        long sellers = home.getSellersForBookOnRow(ROW_TO_TEST);
        detail = home.toDetailOfBook(ROW_TO_TEST);

        boolean userOneIsMarkedAsSeller = false;
        int rowUserOneIsOn = -1;
        for (int i = 0; i < sellers; i++) {
            if (detail.getSeller("sellerTable:" + i + ":").equalsIgnoreCase(emailOne)) {
                userOneIsMarkedAsSeller = true;
                rowUserOneIsOn = i;
            }
        }
        assertTrue(userOneIsMarkedAsSeller);

        detail.sendMessageToSellerOnRow("sellerTable:" + rowUserOneIsOn + ":", messageText);
        detail.clickToSendMessage("sellerTable:" + rowUserOneIsOn + ":");

        message = home.toMessages();

        boolean foundMessage = false;


        //checks that message is sent.
        for (int i = 0; i < message.getAmountOfSentMessages(); i++) {

            if (message.findSentMessageWithText(i, messageText))
                foundMessage = true;
        }
        assertTrue(foundMessage);

        home.doLogout();
        //Logging out user two.

        //Logging in user one.
        home = home.doLogin(emailOne, passwordOne);
        message = home.toMessages();
        foundMessage = false;
        //Checks that message is received.
        int rowOfMessage = -1;
        for (int i = 0; i < message.getAmountOfReceivedMessages(); i++) {

            if (message.findReceivedMessageWithText(i, messageText)) {
                foundMessage = true;
                rowOfMessage = i;
            }
        }
        assertTrue(foundMessage);

        message.setReplyMessage(rowOfMessage, replyText);
        message.sendReplyMessage(rowOfMessage);

        home.doLogout();

        home = home.doLogin(emailTwo, passwordTwo);
        message = home.toMessages();

        foundMessage = false;
        for (int i = 0; i < message.getAmountOfSentMessages(); i++) {

            if (message.findSentMessageWithText(i, messageText))
                foundMessage = true;
        }
        assertTrue(foundMessage);

        foundMessage = false;
        for (int i = 0; i < message.getAmountOfReceivedMessages(); i++) {

            if (message.findReceivedMessageWithText(i, replyText))
                foundMessage = true;
        }

        assertTrue(foundMessage);
    }

    @Test
    public void testAddBook() {
        String email = getUniqueId();
        assertTrue(userService.createAdmin(email,"password","admin","admin"));
        assertFalse(home.isLoggedIn());

        home.doLogin(email, "password");
        assertTrue(home.isLoggedIn());
        registry = home.toBookRegistry();
        int amountOfBooks = registry.getAmountOfBooksDisplayed();

        String title = getUniqueId();
        String course = getUniqueId();
        registry.addBook(title, "TEST_BOOK_AUTHOR", course);
        assertEquals(amountOfBooks+1, registry.getAmountOfBooksDisplayed());

    }

    @Test
    public void testDeleteBook() {
        String email = getUniqueId();
        assertTrue(userService.createAdmin(email,"password","admin","admin"));
        assertFalse(home.isLoggedIn());

        home.doLogin(email, "password");
        assertTrue(home.isLoggedIn());
        registry = home.toBookRegistry();
        int amountOfBooks = registry.getAmountOfBooksDisplayed();

        String title = getUniqueId();
        String course = getUniqueId();

        registry.addBook(title, "TEST_BOOK_AUTHOR", course);
        assertEquals(amountOfBooks+1, registry.getAmountOfBooksDisplayed());

        assertNotEquals(-1, registry.getRowToDeleteOn(title));
        registry.deleteBook(registry.getRowToDeleteOn(title));
        assertEquals(amountOfBooks, registry.getAmountOfBooksDisplayed());
    }

}
