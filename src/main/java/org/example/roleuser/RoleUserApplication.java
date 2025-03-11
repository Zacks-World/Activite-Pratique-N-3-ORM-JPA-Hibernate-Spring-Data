package org.example.roleuser;

import org.example.roleuser.Entities.Role;
import org.example.roleuser.Entities.User;
import org.example.roleuser.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class RoleUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleUserApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService){
        return args ->{
            System.out.println("**********************************");
            User user = new User();
            user.setUsername("user1");
            user.setPassword("123456");
            User user_b=userService.addNewUser(user);
            System.out.println(user_b.toString());
            System.out.println("**********************************");
            User user1 = new User();
            user1.setUsername("admin");
            user1.setPassword("123456");
            User user_b1=userService.addNewUser(user1);
            System.out.println(user_b1.toString());
            System.out.println("**********************************");
            Stream.of("ADMIN", "STUDENT", "USER").forEach(roleName -> {
                Role role = new Role();
                role.setRoleName(roleName);
                Role role_b=userService.addNewRole(role);
                System.out.println(role_b.toString());
                System.out.println("**********************************");
            });
            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("admin", "USER");

            try {
                User user_auth =userService.authenticate("user1", "123456");
                System.out.println(user_auth.getUserid());
                System.out.println("**********************************");
                System.out.println(user_auth.getUsername());
                System.out.println("**********************************");
                System.out.println("Roles=>");
                user_auth.getRoles().forEach(role -> {
                    System.out.println(role);
                });
                userService.authenticate("admin", "123456");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
