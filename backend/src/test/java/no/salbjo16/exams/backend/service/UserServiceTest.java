package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.StubApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        assertTrue(userService.createUser("TEST_MAIL@TEST.COM", "PASSWORD","SURNAME", "NAME"));
    }

}
