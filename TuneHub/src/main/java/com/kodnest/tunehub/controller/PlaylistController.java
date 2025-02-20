package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.PlayList;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.PlayListService;
import com.kodnest.tunehub.service.SongService;

@Controller
public class PlaylistController {
	
	@Autowired
	SongService songService;
	
	@Autowired
	PlayListService playlistservice;
	
	
	@GetMapping("/createplaylists")
	public String createplaylists(Model model) {
	List<Song>songList =songService.fetchAllSongs();
	model.addAttribute("songs",songList);
	return "createplaylists";
	}
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute PlayList playlist) {
		//updating the playlist table
		playlistservice.addplaylist(playlist);
		
		//updating the song table
		List<Song> songlist = playlist.getSongs();
		for(Song s : songlist) {
		s.getPlaylists().add(playlist);
		songService.updateSong(s);
		}
		return "adminhome";
	}
	
	@GetMapping("/viewplaylists")
	public String viewplaylists(Model model) {
		List<PlayList> allplaylists = playlistservice.fetchAllPlaylists();
		model.addAttribute("allplaylists",allplaylists);
		return "displayplaylists";
		
	}

}
