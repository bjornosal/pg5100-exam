package no.salbjo16.exams.frontend.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserInfoController {
/*

    @Inject
    private MatchStatsService matchStatsService;
*/

    public String getUserName(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

//    public MatchStats getStats(){
//        return matchStatsService.getMatchStats(getUserName());
//    }
}
