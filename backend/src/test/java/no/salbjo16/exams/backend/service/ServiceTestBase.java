package no.salbjo16.exams.backend.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServiceTestBase {

    @Autowired
    private ResetService resetService;

    @Before
    public void resetDatabase() {
        resetService.resetDatabase();
    }

}
