package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    //add contact page: handler
    @RequestMapping("/add")
    public String addContactView(Model model){

        ContactForm contactForm = new ContactForm();
//        contactForm.setName("Piyush Anand");
//        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String saveContact(@ModelAttribute ContactForm contactForm, Authentication authentication){

        //process the form data

        //1.validate the form
        //add validation logic here

        String username = Helper.getEmailOfLoggedInUser(authentication);

        //form --> contact
        User user = userService.getUserByEmail(username);

        //2.process the contact picture

        Contact contact = new Contact();

        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setFavorite(contactForm.isFavorite());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());

        contactService.save(contact);

        System.out.println(contactForm);

        //3.set the picture url

        //4.set message to be displayed on the view

        return "redirect:/user/contacts/add";
    }
}
