package org.example.roleuser.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.roleuser.Entities.Role;
import org.example.roleuser.Entities.User;
import org.example.roleuser.Repositories.RoleRepository;
import org.example.roleuser.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserid(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);
        if (user == null || role == null) {
            throw new RuntimeException("User or Role not found");
        }
        if (user.getRoles()!=null && !user.getRoles().contains(role)) {
            user.getRoles().add(role);
            role.getUsers().add(user);
            //userRepository.save(user); because of @Transactional that automatically save the user
        }


    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid username or password");
    }
}
