package no.salbjo16.exams.selenium.po.admin;

import no.salbjo16.exams.selenium.PageObject;
import no.salbjo16.exams.selenium.po.LayoutPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UsersPO extends LayoutPO {
    public UsersPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public UsersPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Admin - Users");
    }

    public int getAmountOfUsers() {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='userTable']/tbody/tr"));
        return elements.size();
    }


}
