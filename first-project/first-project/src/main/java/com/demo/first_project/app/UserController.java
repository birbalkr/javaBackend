package com.demo.first_project.app;

import jakarta.websocket.server.PathParam;
import org.apache.coyote.Request;
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

//    http://localhost:8080/user
    @PostMapping
    public String createUser(@RequestBody User user){
        userdb.put(user.getId(),user);
        return  "User Created";
    }

//    http://localhost:8080/user
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user){
        if (userdb.containsKey(user.getId(  )))
            userdb.put(user.getId(), user);
        else return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Update User Data", HttpStatus.OK);
    }

//    http://localhost:8080/user
    @GetMapping
    public List<User> getUser(){
        return new ArrayList<>(userdb.values());
    }

//    http://localhost:8080/user/7
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        userdb.remove(id);
        return "Delete User";
    }


//    http://localhost:8080/user/2
    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable int id){
        if(!userdb.containsKey(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(userdb.get(id));
    }

//    http://localhost:8080/user/search?name=rahul
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsername(
            @RequestParam(required=false, defaultValue="amit") String name
    ){
        List<User> users=userdb.values().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .toList();
        return ResponseEntity.ok(users);
    }


//    http://localhost:8080/user/searchne?name=rahul&email=rahul@gmail.com
    @GetMapping("/searchne")
    public ResponseEntity<List<User>> searchUserboth(
            @RequestParam(required=false, defaultValue="amit") String name,
            @RequestParam(required=false, defaultValue="amit") String email

    ){
        List<User> users=userdb.values().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .filter(u -> u.getEmail().equalsIgnoreCase(email))

                .toList();
        return ResponseEntity.ok(users);
    }

//    http://localhost:8080/user/info/1?name=admin
    @GetMapping("/info/{id}")
    public String getInfo(
            @PathVariable int id,
            @RequestParam String name,
            @RequestHeader("User-Agent") String userAgent){
        return "User Agent: " + userAgent + " : " + id +" : "+name;
    }

}
