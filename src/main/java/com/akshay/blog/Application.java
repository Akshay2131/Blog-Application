package com.akshay.blog;

import com.akshay.blog.config.AppConstants;
import com.akshay.blog.entities.Role;
import com.akshay.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

// Application class to run spring application
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("xyz"));
        try {
            Role role = new Role();
            role.setId(AppConstants.ADMIN_USER);
            role.setName("ADMIN_USER");

            Role role1 = new Role();
            role1.setId(AppConstants.ADMIN_USER);
            role1.setName("ADMIN_USER");

            List<Role> roles = List.of(role, role1);
            List<Role> result = this.roleRepo.saveAll(roles);

            result.forEach(r -> {
                System.out.println(r.getName());
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
