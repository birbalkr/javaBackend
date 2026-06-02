package com.demo.first_project.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private Map<Integer, User> userdb = new HashMap<>();

    @PostMapping
    public String createUser(@RequestBody User user){
        userdb.put(user.getId(),user);
        return  "User Created";
    }
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user){
        if (userdb.containsKey(user.getId(  )))
            userdb.put(user.getId(), user);
        else return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Update User Data", HttpStatus.OK);
    }

    @GetMapping
    public List<User> getUser(){
        return new ArrayList<>(userdb.values());
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        userdb.remove(id);
        return "Delete User";
    }

}
