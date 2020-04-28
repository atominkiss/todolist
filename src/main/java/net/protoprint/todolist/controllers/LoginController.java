package net.protoprint.todolist.controllers;

import lombok.extern.slf4j.Slf4j;
import net.protoprint.todolist.repr.UserRepr;
import net.protoprint.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class LoginController {

	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@Autowired

	@GetMapping("/login")
	public String loginPage(){

		return "login";
	}

	@GetMapping("/register")
	public String registerPage(Model model){
		model.addAttribute("user", new UserRepr());
		return "register";
	}

	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute("user") @Valid UserRepr userRepr,
	                              BindingResult result){
		log.info("User is:" + userRepr);
		if (result.hasErrors()){
			return "register";
		}
		if (!userRepr.getPassword().equals(userRepr.getMatchingPassword())){
			result.rejectValue("password", "", "Password not matching");
			return "register";
		}
		userService.create(userRepr);
		return "redirect:/login";
	}
}
