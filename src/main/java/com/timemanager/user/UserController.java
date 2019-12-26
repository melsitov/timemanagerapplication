package com.timemanager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Long> insertUser (@RequestBody UserDTO userDTO){
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setSecondName(userDTO.getSecondName());
        userService.insertUser(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<User> getAll (){
        return userService.findAll();
    }

    @GetMapping("/id{id}")
    public User getById (@PathVariable("id") Long id){
        return userService.getById(id);
    }

    @GetMapping("/login&{login}")
    public User getByLogin (@PathVariable("login") String login){
        return userService.getByLogin(login);
    }

    @DeleteMapping("/id{id}")
    public void deleteUserById (@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @PatchMapping("/id{id}")
    public ResponseEntity<User> updateUser (@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        User user = userService.getById(id);
        user.setFirstName(userDTO.getFirstName());
        user.setSecondName(userDTO.getSecondName());
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

}
