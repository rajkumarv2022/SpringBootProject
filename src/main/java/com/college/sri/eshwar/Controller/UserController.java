package com.college.sri.eshwar.Controller;

import com.college.sri.eshwar.entity.User;
import com.college.sri.eshwar.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserServiceImpl service;

    @GetMapping("/user/getAll")
    public List<User> getUser()
    {
        return service.getUser();
    }

    @GetMapping("/user/get/{id}")
    public Optional<User> getUserByid(@PathVariable("id") String id)
    {
        return service.getUserByid(id);
    }

    @PostMapping("/user/create")
    public String createUser(@RequestBody User user)
    {
        return service.createUser(user);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user)
    {
        return service.updateUser(user);
    }

    @DeleteMapping("user/delete/{id}")
    public String deleteUser(@PathVariable("id") String id)
    {
        return service.deleteUser(id);
    }

}
