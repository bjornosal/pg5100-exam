package no.salbjo16.exams.selenium.po.admin;

import no.salbjo16.exams.selenium.PageObject;
import no.salbjo16.exams.selenium.po.LayoutPO;
import org.openqa.selenium.WebDriver;

public class AdminPO extends LayoutPO {

    public AdminPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public AdminPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }
}
