package com.demo.first_project.app.controller;

import com.demo.first_project.app.model.User;
import com.demo.first_project.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService = new UserService();

    //    http://localhost:8080/user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //    http://localhost:8080/user
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User update = userService.updateUser(user);
        if (update == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(update);
    }

    //    http://localhost:8080/user


    //    http://localhost:8080/user/7
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean isDelete = userService.deleteUser(id);
        if (!isDelete)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }


    //    http://localhost:8080/user/2
    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable int id) {
        User user=userService.getSingleUser(id);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(user);
    }




    //    http://localhost:8080/user/searchne?name=rahul&email=rahul@gmail.com
    @GetMapping("/searchne")
    public ResponseEntity<List<User>> searchUserboth(
            @RequestParam(required = false, defaultValue = "amit") String name,
            @RequestParam(required = false, defaultValue = "amit") String email
    ) {
        return ResponseEntity.ok(userService.searchUser(name,email));
    }

    //    http://localhost:8080/user/info/1?name=admin
    @GetMapping("/info/{id}")
    public String getInfo(
            @PathVariable int id,
            @RequestParam String name,
            @RequestHeader("User-Agent") String userAgent) {
        return "User Agent: " + userAgent + " : " + id + " : " + name;
    }

}
