package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project.domain.User;
import com.example.project.service.UserManageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserManageController {

	private final UserManageService userManageService;

	@PostMapping("/user/sign-in")
	public String login(User user) {
		return "redirect:/";
	}

	@GetMapping("/user/sign-in")
	public String signIn(User user) {
		return "user/sign-in";
	}

	@GetMapping("/")
	public String index(User user, Model model) {
		if (user == null) {
			return "redirect:/user/sign-in";
		}
		model.addAttribute("user", user);
		return "index";

	}

//	@GetMapping("/")
//	public String index(HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession(false);
//		if (session == null) {
//			return "redirect:/user/sign-in";
//		}
//
//		User loginUser = (User) session.getAttribute("loginUser");
//
//		if (loginUser == null) {
//			return "redirect:/user/sign-in";
//		}
//
//		model.addAttribute("user", loginUser);
//		return "index";
//	}

	@PostMapping("/user/sign-up")
	public String signUpsave(User user) {
		userManageService.save(user);
		return "user/sign-in";
	}

	@GetMapping("/user/sign-up")
	public String signUp(@ModelAttribute("user") User user) {
		return "user/sign-up";
	}
}
