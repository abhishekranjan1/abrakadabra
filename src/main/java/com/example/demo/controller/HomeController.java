package com.example.demo.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.User;

import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="Insta_Domain Home Controller")
@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	@ApiOperation(value="Home page")
    public String index() {
        return "index";
    }
	
	@GetMapping(value = "/signup")
	@ApiOperation(value="Sign-Up Page GET end Point")
    public String signup(Model model) {
        User user = new User();
       model.addAttribute("user", user);
        return "signup";
    }
	
	@PostMapping(value = "/signup")
	@ApiOperation(value="Sign-Up Page POST end Point")
    public String signupPost(@ModelAttribute("user") User user, Model model) throws Exception {

        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {

            userService.createUser(user);

            return "redirect:/";
        }
    }
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        //PrimaryAccount primaryAccount = user.getPrimaryAccount();
        //SavingsAccount savingsAccount = user.getSavingsAccount();

        //model.addAttribute("primaryAccount", primaryAccount);
        //model.addAttribute("savingsAccount", savingsAccount);

        return "userFront";
    }
}
