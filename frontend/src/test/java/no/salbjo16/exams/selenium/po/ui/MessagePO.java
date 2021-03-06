package no.salbjo16.exams.selenium.po.ui;

import no.salbjo16.exams.selenium.PageObject;
import no.salbjo16.exams.selenium.po.LayoutPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


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

    public boolean findReceivedMessageWithText(int row, String messageText) {
        return getDriver().findElement(By.id("receivedMessagesTable:"+row+":senderMessageText")).getText().equalsIgnoreCase(messageText);
    }

    public boolean findSentMessageWithText(int row, String messageText) {
        return getDriver().findElement(By.id("sentMessagesTable:"+row+":messageText")).getText().equalsIgnoreCase(messageText);
    }

    public void setReplyMessage(int row, String replyText) {
        setText("receivedMessagesTable:"+row+":messageBox", replyText);
    }

    public void sendReplyMessage(int row) {
        clickAndWait("receivedMessagesTable:"+row+":sendReplyBtnId");
    }

    public int getAmountOfSentMessages() {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='sentMessagesTable']/tbody/tr"));
        return elements.size();
    }

    public int getAmountOfReceivedMessages() {
        List<WebElement> elements = driver.findElements(
                By.xpath("//table[@id='receivedMessagesTable']/tbody/tr"));
        return elements.size();
    }
}
