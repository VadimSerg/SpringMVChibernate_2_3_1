package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AdminController {



    @GetMapping(value="/login")
    public  String getLoginPage() {
        System.out.println("Log::");
        return "login";
    }

//    private final UserService userService;
 //   private final RoleService roleService;

//    @Autowired
//    public AdminController(@Qualifier("userServiceImpl") UserService userService, @Qualifier("roleServiceImpl") RoleService roleService){
//        this.userService = userService;
//        this.roleService = roleService;
//    }


    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public AdminController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("users",userService.getAll());
        return "amins_pages/list";
    }


    @GetMapping("/showForm")
    public  String showFormForAddingUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "amins_pages/newUser";
    }



    @PostMapping(value = "/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)  {

        if (bindingResult.hasErrors()) {
            return "amins_pages/newUser";
        }

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "amins_pages/editForm";
    }



   @PostMapping(value = "/{id}")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "amins_pages/editForm";
        }

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
     //   model.addAttribute("user",userService.loadUserByUsername(logedInUser.getUsername()));
        model.addAttribute("user",user);
        return  "userPage";

    }







}
