package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@Validated
public class AdminController {


    @GetMapping(value="/login")
    public  String getLoginPage() {
        System.out.println("Log::");
        return "login";
    }

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;


    public AdminController(UserService userService, UserDetailsService userDetailsService,
                           RoleService roleService) {

        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("usersSet",userService.getAll());
        model.addAttribute("RolesSet",roleService.getAllRoles());
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
                            @Valid @RequestParam(value = "roles_checkbox", required = true) String [] authorities,
                            BindingResult bindingResult1) {

        if (bindingResult.hasErrors()) {
            return  "admins_pages/newUser";
        }

        if (bindingResult1.hasErrors()) {
            return  "admins_pages/newUser";
        }


           if (authorities!=null) {
           user.setRoles(roleService.getRolesByRoleNames(authorities));

            userService.saveUser(user);
            return "redirect:/admin";

       }
           return "admins_pages/newUser";


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
                         @RequestParam(value = "roles_checkbox", required = true) String [] roleNames) {

        if (bindingResult.hasErrors()) {
            return  "admins_pages/newUser";
        }

       user.setRoles(roleService.getRolesByRoleNames(roleNames));
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


    //Adding roles





    @GetMapping("/showFormRoles")
    public String showFormForAddingRoles(Model model, Role role) {
        model.addAttribute("Role", role);
        return "admins_pages/newRole";
    }


    @PostMapping("saveRole")
    public String saveRole( @Valid @ModelAttribute("Role") Role role,
                           @RequestParam(value = "roleName",required = true) String roleName,
                            BindingResult bindingResult)  {

        if (bindingResult.hasFieldErrors(roleName)){
            return  "admins_pages/newRole";
        }
        if (roleName.isEmpty()) {return "admins_pages/newRole" ;}

        roleService.saveRole(new Role(roleName));
        return "redirect:/admin ";

    }

    @GetMapping("editRole/{id}")
    public String showEditRoleForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("Role",roleService.getRoleById(id));
        return "admins_pages/editRoleForm";
    }

    @PostMapping("/role{id}")
    public String updateRole(@ModelAttribute("Role") Role role) {

//        if (bindingResult.hasErrors()) {
//            return  "admins_pages/newUser";
//        }


        roleService.update(role);
        return "redirect:/admin";
    }

    @GetMapping("/role{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRoleById(id);
        return "redirect:/admin";
    }









}
