package no.salbjo16.exams.selenium.po;

import no.salbjo16.exams.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


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
        return getDriver().getTitle().contains("Quiz Game");
    }

    public boolean isLoggedIn() {
        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements((By.id("linkToSignupId"))).isEmpty();
    }

    //TODO how to add a new "match", anything and wait for click
   /* public MatchPO startNewMatch(){

        clickAndWait("newMatchBtnId");
        MatchPO po = new MatchPO(this);

        assertTrue(po.isOnPage());

        return po;
    }*/
}
