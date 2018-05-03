package no.salbjo16.exams.selenium.po.ui;

import no.salbjo16.exams.selenium.PageObject;
import no.salbjo16.exams.selenium.po.LayoutPO;
import org.openqa.selenium.WebDriver;


public class MessagePO extends LayoutPO {

    public MessagePO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public MessagePO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Messages");
    }


}
