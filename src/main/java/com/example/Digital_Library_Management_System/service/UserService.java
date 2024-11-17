package com.example.Digital_Library_Management_System.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.Constants.AuthoritiesListProvider;
import com.example.Digital_Library_Management_System.Constants.Constants;
import com.example.Digital_Library_Management_System.model.User;
import com.example.Digital_Library_Management_System.repository.UserRepo;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByUserName(username);
    }



    public User save(String userType, User user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String authorities = AuthoritiesListProvider.getAuthorities(userType);

        if(authorities.equals(Constants.INVALID_USER)) {
            // TODO - Exception Handling
            return new User();
        }

        user.setPassword(encodedPassword);
        user.setAuthorities(authorities);


        return userRepo.save(user);

    }







    
}
