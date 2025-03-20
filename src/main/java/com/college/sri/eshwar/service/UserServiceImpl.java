package com.college.sri.eshwar.service;


import com.college.sri.eshwar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.sri.eshwar.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    ArrayList<User> al=new ArrayList<>();
    public String createUser(User user)
    {
        boolean val=validateEmail(user.getEmail());
        if(val==true)
        {
            userRepository.save(user);
            return "User created";
        }
        else
        {
            return "email is not valid";
        }

    }

    public List<User> getUser()
    {
            return userRepository.findAll();
    }

    public Optional<User> getUserByid(int id)
    {
        return userRepository.findById(id);
    }

    public String deleteUser(int id)
    {
        userRepository.deleteById(id);
        return "User succesfully deleted";
    }

    public User updateUser(User user)
    {
        userRepository.save(user);
        return user;
    }

    public boolean validateEmail(String email)
    {
        String EMAIL_REGEX = "^[A-Za-z][A-Za-z0-9]*@gmail\\.com$";
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

}
