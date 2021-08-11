package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {


    @GetMapping(value="/login")
    public  String getLoginPage() {
        System.out.println("Log::");
        return "login";
    }

    private final UserService userService;

    @Autowired
    public AdminController(@Qualifier("userServiceImpl") UserService userService){
        this.userService = userService;
    }

    @GetMapping("/admin/**")
    public String getUsers(Model model) {
        model.addAttribute("users",userService.getAll());
        return "list";
    }


    @GetMapping("/showForm")
    public  String showFormForAddingUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return  "newUser";
    }



    //    @GetMapping("/showForm")
//    public String showFormForAddingUser(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "newUser";
//    }









}
