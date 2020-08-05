package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... args) {
        User user = new User("bart","bart@domain.com", "bart","Bart","Simpson",true);
        Role userRole = new Role("bart", "ROLE_USER");

        userRepository.save(user);
        roleRepository.save(userRole);


        User admin = new User ("super","super@domain.com","super","Super", "Hero", true);
        Role adminRole1=new Role("super","ROLE_ADMIN");
        Role adminRole2=new Role("super","ROLE_USER");

        userRepository.save(admin);
        roleRepository.save(adminRole1);
        roleRepository.save(adminRole2);
    }
}
