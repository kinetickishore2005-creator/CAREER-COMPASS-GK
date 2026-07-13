package com.careercompass.controller;


import com.careercompass.entity.User;
import com.careercompass.repository.UserRepository;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class AuthController {



    private final UserRepository repository;

    private final PasswordEncoder encoder;



    public AuthController(
            UserRepository repository,
            PasswordEncoder encoder){

        this.repository=repository;
        this.encoder=encoder;

    }




    @GetMapping("/register")
    public String registerPage(Model model){

        model.addAttribute(
                "user",
                new User()
        );


        return "register";

    }




    @PostMapping("/register")
    public String register(
            @ModelAttribute User user){



        user.setPassword(
                encoder.encode(user.getPassword())
        );


        repository.save(user);



        return "redirect:/login";

    }




    @GetMapping("/login")
    public String login(){

        return "login";

    }


}