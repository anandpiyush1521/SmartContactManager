package com.scm.controllers;

import java.security.Principal;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.helpers.Helper;
import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    
    //user dashboard page
    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication){
        
        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in : {}", username);

        //fetch data from database: get user from database: email, name, address etc

        System.out.println("User profile page");
        return "user/profile";
    }


    //user add contact page

    //user view contacts

    //user edit contact 

    //user delete contact 

    //user search contact
}
