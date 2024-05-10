package com.scm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.services.Impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // user create and login user using java code with in memory service
    
    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1 =  User 
    //         .withDefaultPasswordEncoder()
    //         .username("admin123")
    //         .password("admin123")
    //         .roles("ADMIN", "USER")   
    //         .build();

    //     UserDetails user2 =  User
    //         .withDefaultPasswordEncoder()
    //         .username("user123")
    //         .password("user123")
    //         .roles("USER")   
    //         .build();


    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        //object of user details service
        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        //object of password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
