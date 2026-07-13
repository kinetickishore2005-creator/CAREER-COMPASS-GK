package com.careercompass.service;


import com.careercompass.entity.User;
import com.careercompass.repository.UserRepository;


import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService
        implements UserDetailsService {



    private final UserRepository repository;



    public CustomUserDetailsService(
            UserRepository repository){

        this.repository=repository;

    }



    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{


        User user =
                repository.findByUsername(username);



        if(user==null){

            throw new UsernameNotFoundException(
                    "User not found");

        }



        return org.springframework.security.core.userdetails.User

                .withUsername(user.getUsername())

                .password(user.getPassword())

                .roles("USER")

                .build();

    }


}