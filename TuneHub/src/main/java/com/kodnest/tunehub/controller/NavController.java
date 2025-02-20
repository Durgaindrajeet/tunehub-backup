package com.kodnest.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	
@GetMapping("/registration")
public String registration() {
	return "registration";
}

@GetMapping("/addsong")
public String addsong() {
	return "addsong";
}

//@GetMapping("/viewsongs")
//public String viewsongs() {
//	return "viewsongs";
//}

}