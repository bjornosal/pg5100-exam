package no.salbjo16.exams.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DetailController  {


    @Autowired
    private UserInfoController userInfoController;

    @Autowired
    private HomeController homeController;

    public boolean sellerIsCurrentUser(String email){
        return userInfoController.getUserName().equalsIgnoreCase(email);
    }

    public boolean isSellerOnlyCurrentUser() {
        return (homeController.getBook().getSellers().size() == 1) &&
                userInfoController.getUserName() != null &&
                homeController.getBook().getSellers().get(0).getEmail().equalsIgnoreCase(userInfoController.getUserName());
    }
}
