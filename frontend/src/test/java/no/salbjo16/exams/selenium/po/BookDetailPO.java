package no.salbjo16.exams.selenium.po;


import no.salbjo16.exams.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public String getSeller(String row) {
        return getDriver().findElement(By.id(row+"sellerEmail")).getText();
    }
    public void sendMessageToSellerOnRow(String row, String message) {
        WebElement element = getDriver().findElement(By.id(row+"messageBox"));
        element.sendKeys(Keys.TAB);
        element.clear();
        element.sendKeys(message);

    }
    public void clickToSendMessage(String row) {
        clickAndWait(row+"sendBtnId");
    }
}
