package org.example.roleuser.Web;

import org.example.roleuser.Entities.User;
import org.example.roleuser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.findUserByUserName(username);
    }

}
