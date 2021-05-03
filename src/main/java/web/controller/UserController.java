package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users",userService.getUsers());
        return "list";
    }

    @GetMapping("/showForm")
    public String showFormForAddingUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user)  {
//        if (bindingResult.hasErrors()) {
//            return "newUser";
//        }
        userService.saveUser(user);
        return "redirect:/ ";
    }

    @GetMapping("/edit/{id}")
    public  String showEditForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("user",userService.getUserById(id));
        return  "editForm";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return  "redirect:/";
    }

    @GetMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}