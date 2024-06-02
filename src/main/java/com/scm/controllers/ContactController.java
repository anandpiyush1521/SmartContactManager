package com.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

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
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result ,Authentication authentication, HttpSession session){

        //process the form data

        //1.validate the form
        if(result.hasErrors()){

            result.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Message.builder()
                .content("Please correct the follwing errors")
                .type(MessageType.red)
                .build());
            return "user/add_contact";
        }

        //add validation logic here

        String username = Helper.getEmailOfLoggedInUser(authentication);

        //form --> contact
        User user = userService.getUserByEmail(username);

        //2.process the contact picture
        //image process
        //upload file

        String filename = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);


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
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);

        contactService.save(contact);

        System.out.println(contactForm);

        //3.set the picture url

        //4.set message to be displayed on the view

        session.setAttribute("message", Message.builder()
            .content("You have successfully added a new contact")
            .type(MessageType.green)
            .build());
        return "redirect:/user/contacts/add";
    }
}
