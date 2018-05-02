package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.StubApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageServiceTest extends ServiceTestBase {
}
