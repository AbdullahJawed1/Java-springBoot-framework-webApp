package com.example.User_ManagementSystem.controller;

import com.example.User_ManagementSystem.model.User;
import com.example.User_ManagementSystem.view.UserFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserFunctions userFunctions;

    @Autowired
    public UserController(UserFunctions userFunctions) {
        this.userFunctions = userFunctions;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userFunctions.getAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create_user";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute User user) {
        userFunctions.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userFunctions.getById(id));
        return "update_user";
    }

    @PostMapping("/users/{id}")
    public String editUser(@PathVariable("id") long id, @ModelAttribute User user) {
        user.setId(id);
        userFunctions.update(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id, @ModelAttribute User user) {
        userFunctions.delete(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String deleteAllUsers() {
        int rowsDeleted = userFunctions.deleteAll();
        return "redirect:/users";

    }







}
