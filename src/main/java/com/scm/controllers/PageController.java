package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }
    
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
    //registration page
    @RequestMapping("/register")
    public String signupPage(Model model){
        UserForm userForm = new UserForm();
        //can insert default data also
        // userForm.setName("Ayush");
        // userForm.setAbout("This is about");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    //processing register
    // @PostMapping("/do-register")

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing Register");

        //fetch the form data 
        //userForm
        System.out.println(userForm);
        
        //validate form data TODOS
        if(rBindingResult.hasErrors()){
            return "register";
        }
        
        //save to database
        //userservice
        //created user form userform userform -> user

        // User user = User.builder()
        //     .name(userForm.getName())
        //     .email(userForm.getEmail())
        //     .password(userForm.getPassword())
        //     .phoneNumber(userForm.getPhoneNumber())
        //     .about(userForm.getAbout())
        //     .profilePic("https://lh3.googleusercontent.com/pw/AP1GczPrwDKYsPiEUZiT7r1CYAFIyqzi4VQGufe5rSdpDIdgaNMzLMfKdsfEHZsXpa6I4rlPG4lt4RdwKogNcoXMPgeGGRTTPR5xSeTKzd7H2EBL2fE1gGx_tI5uTwz-KwULZw2M417-a_ZTvTaJsJwRJIuOapxr2R7IFb3cusNwphHBe99IOcghevy6gKC5F_kQJY3KhiQPjWsenM9Lzc0kKGnLiTL2MJ2Nl1IcBrpgYjSDrfKPJycPqJQygPItk6Qf3cLyEYbDN4RnOZumR11tEbF4SlCV-0nvBCYPYO0QuY0-lZby493ObsQPCJFhynzqokevPKOV0edAa4n2f2YewqpNzaVkpFsfORCQe2I4qpb65DC9oPCLw8d8lZFWpXzSLZZGccJO3fzrknPMWPyBth_M5TZCX6H7-KI9_RTwbSQJYpRoTh16gA40cEIWSsC9j6NNCOQebEdEaCga1LTJpoGnc6SaSgE1uI5f0tIr21pwvAZ3v2s1qfIBd2JvcCdb2sRGJTiPeArfj5aGcqh-2Xtf7eQIXgR23Dpcj9SjSjsjkiojgg7IcT7c83c878mp3Pi60zqUyS4_HY_PpnP9QOx-lR7Tx9AbH9H5FdryuHLch3UQxig8Q2AUcsRdetQ3cXIDHjXgTKrJ9bUoFVvyIKc3MM_D0vSkm57jGSLH2Dv4Fp_-SM6enuv0jlNB2prIYHIhHMKq-eHM-5We3Ox9sybCxLChFQUY55_IrGkqdwEqYXEwYKu5wofSEQm3a_Z7OuUuQssiKa-ItY8MWSSrHlZKuTCcJcmD3MzYLn2RvIIcOn0DuJUz7tuLMsw_zXtMHHnPtbXaDdodylMBOpQuLxkwzNr3LCILNeDPxqUX9jsojTKylQfzOMzziydos5ChAsTPQnK6f83eKhUjcPI7xw5eeA8WfwnrovP4i2Sc8SCAowSEzlGw9loMVUlnVNuQVUoGFw7Mh7B1_gNrogWwUIwL2nq1eIWXjw=w528-h889-s-no-gm?authuser=0")
        //     .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());
        user.setEnabled(false);
        user.setProfilePic("https://lh3.googleusercontent.com/pw/AP1GczPrwDKYsPiEUZiT7r1CYAFIyqzi4VQGufe5rSdpDIdgaNMzLMfKdsfEHZsXpa6I4rlPG4lt4RdwKogNcoXMPgeGGRTTPR5xSeTKzd7H2EBL2fE1gGx_tI5uTwz-KwULZw2M417-a_ZTvTaJsJwRJIuOapxr2R7IFb3cusNwphHBe99IOcghevy6gKC5F_kQJY3KhiQPjWsenM9Lzc0kKGnLiTL2MJ2Nl1IcBrpgYjSDrfKPJycPqJQygPItk6Qf3cLyEYbDN4RnOZumR11tEbF4SlCV-0nvBCYPYO0QuY0-lZby493ObsQPCJFhynzqokevPKOV0edAa4n2f2YewqpNzaVkpFsfORCQe2I4qpb65DC9oPCLw8d8lZFWpXzSLZZGccJO3fzrknPMWPyBth_M5TZCX6H7-KI9_RTwbSQJYpRoTh16gA40cEIWSsC9j6NNCOQebEdEaCga1LTJpoGnc6SaSgE1uI5f0tIr21pwvAZ3v2s1qfIBd2JvcCdb2sRGJTiPeArfj5aGcqh-2Xtf7eQIXgR23Dpcj9SjSjsjkiojgg7IcT7c83c878mp3Pi60zqUyS4_HY_PpnP9QOx-lR7Tx9AbH9H5FdryuHLch3UQxig8Q2AUcsRdetQ3cXIDHjXgTKrJ9bUoFVvyIKc3MM_D0vSkm57jGSLH2Dv4Fp_-SM6enuv0jlNB2prIYHIhHMKq-eHM-5We3Ox9sybCxLChFQUY55_IrGkqdwEqYXEwYKu5wofSEQm3a_Z7OuUuQssiKa-ItY8MWSSrHlZKuTCcJcmD3MzYLn2RvIIcOn0DuJUz7tuLMsw_zXtMHHnPtbXaDdodylMBOpQuLxkwzNr3LCILNeDPxqUX9jsojTKylQfzOMzziydos5ChAsTPQnK6f83eKhUjcPI7xw5eeA8WfwnrovP4i2Sc8SCAowSEzlGw9loMVUlnVNuQVUoGFw7Mh7B1_gNrogWwUIwL2nq1eIWXjw=w528-h889-s-no-gm?authuser=0");


        User saveUser = userService.saveUser(user);
        System.out.println("User saved");

        //message = "Register successfully"
        //add the message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);


        //redirect to login page

        return "redirect:/register";
    }
}