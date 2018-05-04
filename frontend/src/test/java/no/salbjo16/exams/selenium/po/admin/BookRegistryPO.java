package no.salbjo16.exams.selenium.po.admin;

import no.salbjo16.exams.selenium.PageObject;
import no.salbjo16.exams.selenium.po.LayoutPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookRegistryPO extends LayoutPO {

    public BookRegistryPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public BookRegistryPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Admin - Registry");
    }

    public void addBook(String title, String authors, String course) {
        setText("addTitle", title);
        setText("addAuthors", authors);
        setText("addCourse", course);
        clickAndWait("addBtn");
    }

    /** Source for alert click in alertDialog: https://www.guru99.com/alert-popup-handling-selenium.html
     */
    public void deleteBook(int row) {
        WebElement element = driver.findElement(By.id("booksTable:"+row+":deleteBtn"));
        element.click();
        getDriver().switchTo().alert().accept();
        waitForPageToLoad();
    }

    public int getAmountOfBooksDisplayed() {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='booksTable']/tbody/tr"));
        return elements.size();
    }

    public int getRowToDeleteOn(String title) {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='booksTable']/tbody/tr"));

        for(int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getText().contains(title)){
                return i;
            }
        }
        return -1;
    }
}
