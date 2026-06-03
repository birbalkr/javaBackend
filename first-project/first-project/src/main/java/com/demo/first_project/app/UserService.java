package com.demo.first_project.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> userdb = new HashMap<>();

    public User createUser(User user) {
        userdb.putIfAbsent(user.getId(), user);
        return user;
    }

    public User updateUser(User user) {
        if (userdb.containsKey(user.getId()))
            return null;
       userdb.put(user.getId(), user);
       return user;
    }

    public boolean deleteUser(int id) {
        if(!userdb.containsKey(id))
            return false;
        userdb.remove(id);
        return true;
    }

    public List<User> getAllUser() {
        return new ArrayList<>(userdb.values());
    }

    public User getSingleUser(int id) {
        return userdb.get(id);
    }

    public List<User> searchUser(String name, String email) {
        return userdb.values().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .toList();
    }
}
