package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.StubApplication;
import no.salbjo16.exams.backend.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageServiceTest extends ServiceTestBase {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Test
    public void testSendMessage() {
        final String TEST_USER_ONE_EMAIL = "TEST_ONE@TEST.COM";
        final String TEST_USER_TWO_EMAIL = "TEST_TWO@TEST.COM";

        userService.createUser("TEST_ONE", "TEST_ONE_SURNAME", "TEST_PASSWORD", TEST_USER_ONE_EMAIL);
        userService.createUser("TEST_TWO", "TEST_TWO_SURNAME", "TEST_PASSWORD", TEST_USER_TWO_EMAIL);

        assertNotNull(messageService.sendMessage("TEST_MESSAGE", TEST_USER_ONE_EMAIL, TEST_USER_TWO_EMAIL));
    }

}
