package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.SongService;

@Controller
public class SongController {
	@Autowired
	SongService songservice;
	
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song){
boolean songStatus	=	songservice.songExists(song.getName());
if(songStatus==false) {
	songservice.addSong(song);
	System.out.println("Song added successfully");
	
}
else {
	System.out.println("Song alredy exists");
}
		
		
		 return "adminhome";
	}
	
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
	List<Song>songList =	songservice.fetchAllSongs();
	model.addAttribute("songs",songList);
	return "displaysongs";
	}
	
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premium = false;
		if(premium) {
			List<Song>songList =	songservice.fetchAllSongs();
			model.addAttribute("songs",songList);
			return "displaysongs";
			
		} else {
			
			return "subscriptionform";
		}
			
		
	
	}
}
