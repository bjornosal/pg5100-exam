package no.salbjo16.exams.selenium.po;

import no.salbjo16.exams.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertTrue;


public class IndexPO extends LayoutPO {

    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public IndexPO(PageObject other) {
        super(other);
    }

    public void toStartingPage(){
        getDriver().get(host + ":" + port);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("USED BOOKS");
    }

    public boolean isLoggedIn() {
        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements((By.id("linkToSignupId"))).isEmpty();
    }

    public boolean defaultBooksAreDisplayed() {
        return getDriver().findElement(By.id("booksTable:0:title")).isDisplayed() &&
                getDriver().findElement(By.id("booksTable:1:title")).isDisplayed();
    }

    public boolean isForSaleMarkerDisplayed(String row) {
        return getDriver().findElements(By.id(row+"checkBox")).size() > 0;
    }

    public boolean isSellButtonDisplayed(String row) {
        return getDriver().findElements(By.id(row+"sellBtn")).size() > 0;
    }

    public long getSellersForBookOnRow(String row) {
        String sellers = getDriver().findElement(By.id(row+"seller")).getText();
        return Long.parseLong(sellers);

    }

    public void clickToSellBook(String row) {
        clickAndWait(row+"sellBtn");
    }

    public BookDetailPO toDetailOfBook(String row) {
        clickAndWait(row+"detailBtn");

        BookDetailPO po = new BookDetailPO(this);

        assertTrue(po.isOnPage());
        return po;
    }

}
