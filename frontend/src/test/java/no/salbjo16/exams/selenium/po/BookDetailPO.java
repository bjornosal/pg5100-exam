package no.salbjo16.exams.selenium.po;


import no.salbjo16.exams.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookDetailPO extends LayoutPO {


    public BookDetailPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public BookDetailPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Details");
    }

    public String getFirstSeller() {
        return getDriver().findElement(By.id("sellerTable:0:sellerEmail")).getText();
    }
}
