package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/processregister")
    public String processRegisterationPage(
            @Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        if(result.hasErrors()){
            user.clearPassword();
            model.addAttribute("user",user);
            return "register";
        }
        else {
            model.addAttribute("user",user);
            model.addAttribute("message","New user account created");

            user.setEnabled(true);
            userRepository.save(user);
            Role role = new Role(user.getUsername(),"ROLE_USER");
            roleRepository.save(role);
        }
        return "index";
    }


    @RequestMapping("/secure")
    public String secure(Principal principal, Model model)
    {
        String username = principal.getName();
        model.addAttribute("user",userRepository.findByUsername(username));
        return "secure";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/logout")
    public String logout(){
      return "redirect:/login?logout=true";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
