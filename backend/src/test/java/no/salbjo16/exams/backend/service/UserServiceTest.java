package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.StubApplication;
import no.salbjo16.exams.backend.entity.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;
    private final String userOneEmail = "TEST_ONE@TEST.COM";
    private final String userTwoEmail = "TEST_TWo@TEST.COM";

    private void createUsersAndSendMessage() {
        userService.createUser(userOneEmail,"TEST_PASSWORD","TEST_NAME_ONE","TEST_SURNAME_ONE");
        userService.createUser(userTwoEmail,"TEST_PASSWORD","TEST_NAME_TWO","TEST_SURNAME_TWO");
        messageService.sendMessage("TEST_MESSAGE", userOneEmail, userTwoEmail);
    }

    @Test
    public void testCreateUser() {
        assertTrue(userService.createUser("TEST_MAIL@TEST.COM", "PASSWORD","SURNAME", "NAME"));
    }

    @Test
    public void testGetSentMessages() {
        createUsersAndSendMessage();

        assertEquals(1,userService.getSentMessages(userOneEmail).size());
    }

    @Test
    public void testGetReceivedMessages() {
        createUsersAndSendMessage();

        assertEquals(1,userService.getSentMessages(userOneEmail).size());
    }

    @Test
    public void testCreateAdmin() {
        assertTrue(userService.createUser("ADMIN_MAIL@TEST.COM", "PASSWORD","SURNAME", "NAME"));

    }


}
