package org.example.roleuser.Service;

import org.example.roleuser.Entities.Role;
import org.example.roleuser.Entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username,String roleName);
    User authenticate(String username, String password);
}
