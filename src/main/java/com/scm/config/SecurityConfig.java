package com.scm.config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.services.Impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    //configuration of authentication provider spring security
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //configuration

        //urls configuration
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //form deafult login
        httpSecurity.formLogin(formLogin -> {
            
            //customize the login page
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");
            // formLogin.defaultSuccessUrl("/home");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });

            formLogin.failureHandler(authFailureHandler);

        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        //OAuth Configuration
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        
        });

        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
