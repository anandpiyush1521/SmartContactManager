package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Inside home controller");

        //sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("githubRepo", "https://github.com/anandpiyush1521");
        return "home";
    }
    //about route
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", true);
        System.out.println("About Page Loading");
        return "about";
    }
    //about services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services Page Loading");
        return "services";
    }
    // contact page
    @RequestMapping("/contact")
    public String contactPage(){
        System.out.println("Contact Page Loading");
        return "contact";
    }
    //login page
    @RequestMapping("/login")
    public String loginPage(){
        System.out.println("Login Page Loading");
        return "login";
    }
    //signup page
    @RequestMapping("/register")
    public String signupPage(){
        System.out.println("Signup Page Loading");
        return "register";}
}
