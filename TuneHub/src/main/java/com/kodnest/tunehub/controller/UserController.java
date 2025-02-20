package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl ServiceImpl;
	
	@Autowired
	SongService songservice;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
	
		//email taken from registration form
		String email = user.getEmail();
		
		//checking if email as entered in registration form is present or not
	boolean	 status = ServiceImpl.emailExists(email);
	if(status == false) {
		ServiceImpl.addUser(user);
		System.out.println("User Added");
		
	}
	else {
		System.out.println("User already exists");
	}
		
		
return "login";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		
		if (ServiceImpl.ValidateUser(email,password) == true){
			String role = ServiceImpl.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminhome";
			}
			else {
				User user = ServiceImpl.getUser(email);
		boolean userstatus = user.isIspremium();
		
		List<Song> fetchAllSongs = songservice.fetchAllSongs();
		model.addAttribute("songs",fetchAllSongs);
		
		model.addAttribute("ispremium",userstatus);
			return "customerhome";
		}
		}
		else {
			return "login";
		}
	}
	
	@GetMapping("/lagout")
	public String lagout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
