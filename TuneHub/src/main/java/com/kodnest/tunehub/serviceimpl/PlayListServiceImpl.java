package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.PlayList;
import com.kodnest.tunehub.repository.PlayListRepository;
import com.kodnest.tunehub.service.PlayListService;

@Service
public class PlayListServiceImpl implements PlayListService {
	
	@Autowired
	PlayListRepository playListRepository;
	
	@Override
	public void addplaylist(PlayList playlist) {
		
		playListRepository.save(playlist);
	}

	@Override
	public List<PlayList> fetchAllPlaylists() {
	 List<PlayList> allplaylist=	playListRepository.findAll();
		
		return allplaylist;
	}

}
