package com.scm.helpers;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // AuthenticationPrincipal principal = (AuthenticationPrincipal)authentication.getPrincipal();
        
        if(authentication instanceof OAuth2AuthenticationToken) {

            var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

            var clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = "";
            if(clientId.equalsIgnoreCase("google")){
                //if user is registered using Google
                System.out.println("Getting email form Google");
                username = oauth2User.getAttribute("email").toString();
            }
            else if(clientId.equalsIgnoreCase("github")){
                //if user is registered using Github
                System.out.println("Getting email form Github");
                username = oauth2User.getAttribute("email") != null ? 
                    oauth2User.getAttribute("email").toString() : 
                    oauth2User.getAttribute("login").toString()+"@gmail.com";
            }
            

            return username;

        }else{
            //if user is registered using email and password
            System.out.println("Getting Data from database");
            return authentication.getName();
        }

    }

    public static String getLinkForEmailVerification(String emailToken){
        String link = "http://localhost:8080/auth/verify-email?token=" + emailToken;

        return link;
    }

}
