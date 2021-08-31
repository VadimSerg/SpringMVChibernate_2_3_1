package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {


    @GetMapping(value="/login")
    public  String getLoginPage() {
        System.out.println("Log::");
        return "login";
    }

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;
    private  final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, UserDetailsService userDetailsService,
                           RoleService roleService, PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("users",userService.getAll());
        return "admins_pages/list";
    }


    @GetMapping("/showForm")
    public  String showFormForAddingUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("AllRoles",roleService.getAllRoles());
        return "admins_pages/newUser";
    }



    @PostMapping(value = "/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           @RequestParam(value = "roles_checkbox", required = false) String [] authorities) {

        if (bindingResult.hasErrors()) {
            return  "admins_pages/newUser";
        }


        Set<Role> roles = new HashSet<>();
        for (String authority : authorities) {
            roles.add(roleService.getRoleByName(authority));


        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));



        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }




    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("AllRoles",roleService.getAllRoles());
        return "admins_pages/editForm";
    }



   @PostMapping(value = "/{id}")
    public String update(@Valid @ModelAttribute("user") User user,
                            BindingResult bindingResult,
                         @RequestParam(value = "roles_checkbox", required = false) Long [] rolesID) {

        if (bindingResult.hasErrors()) {
            return  "admins_pages/newUser";
        }


       Set<Role> roles = new HashSet<>();
       for (Long roleId : rolesID) {
           roles.add(roleService.getRoleById(roleId));


       }

       user.setRoles(roles);
       userService.update(user);
        return "redirect:/admin";
   }

    @GetMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


    //Code for user's page
    @GetMapping("/user")
    public String showUserPage(Model model, @AuthenticationPrincipal UserDetails logedInUser) {
        User user = (User) userDetailsService.loadUserByUsername(logedInUser.getUsername());
        model.addAttribute("user",user);
        return  "userPage";

    }







}
