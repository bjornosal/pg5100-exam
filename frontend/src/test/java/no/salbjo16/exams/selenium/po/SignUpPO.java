package no.salbjo16.exams.selenium.po;


import no.salbjo16.exams.selenium.PageObject;

public class SignUpPO extends LayoutPO {

    public SignUpPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Sign Up");
    }

    public IndexPO createUser(String email, String password, String name, String surname){

        setText("email", email);
        setText("name", name);
        setText("surname", surname);
        setText("password", password);
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);

        if(po.isOnPage()){
            return po;
        }

        return null;
    }
}
