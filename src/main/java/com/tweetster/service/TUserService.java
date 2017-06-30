package com.tweetster.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tweetster.dto.TUserDto;
import com.tweetster.dto.TweetDto;
import com.tweetster.entity.TUser;
import com.tweetster.exception.ReferencedEntityNotFoundException;
import com.tweetster.mapper.TUserMapper;
import com.tweetster.repository.TUserRepository;

@Service
public class TUserService {

	private TUserRepository repo;
	private TUserMapper mapper;
	
	public TUserService(TUserRepository repo, TUserMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	// Validate
	private void mustExist(Integer id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(TUser.class, id);
	}

	private void mustExist(String name) {
		if(!has(name))
			throw new ReferencedEntityNotFoundException(TUser.class, name);
	}
	
	public boolean has(Integer id) {
		return repo.exists(id);
	}

	public boolean has(String name) {
		return repo.findByUsername(name).size() > 0;
	}
	
	// Get
	public TUserDto get(Integer id) {
		mustExist(id);
		return mapper.toDto(repo.findOne(id));
	}
	public TUserDto get(String name) {
		mustExist(name);
		return mapper.toDto(repo.findByUsername(name).get(0));
	}
	public List<TUserDto> getAll() {
		return repo.findAll().stream()
				.map(mapper::toDto).collect(Collectors.toList());
	}
	public List<TUserDto> getActive() {
		return repo.findAll().stream()
				.filter(u -> u.isActive())
				.map(mapper::toDto).collect(Collectors.toList());
	}

	
	public List<TweetDto> getTweets(Integer id) {
		mustExist(id);
		TUserDto usr = get(id);
		return usr.getTweets().stream()
				.filter(t -> !t.isDeleted())
				.collect(Collectors.toList());
	}
	public List<TweetDto> getTweets(String name) {
		mustExist(name);
		TUserDto usr = get(name);
		return usr.getTweets().stream()
				.filter(t -> !t.isDeleted())
				.collect(Collectors.toList());
	}

	public List<TUserDto> getFollows(Integer id) {
		mustExist(id);
		TUser usr = mapper.toEntity(get(id));
		return usr.getFollows().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}
	public List<TUserDto> getFollows(String name) {
		mustExist(name);
		TUser usr = mapper.toEntity(get(name));
		return usr.getFollows().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	public List<TUserDto> getFollowedBy(Integer id) {
		mustExist(id);
		TUser usr = mapper.toEntity(get(id));
		return usr.getFollowedBy().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}
	public List<TUserDto> getFollowedBy(String name) {
		mustExist(name);
		TUser usr = mapper.toEntity(get(name));
		return usr.getFollowedBy().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}
	
	// Create Users
	public Integer addUser(TUserDto tuserDto) {
		tuserDto.setId(null);
		tuserDto.setActive(true);
		return repo.save(mapper.toEntity(tuserDto)).getId();
	}

	public void updateUser(Integer id, TUserDto tuserDto) {
		mustExist(id);
		tuserDto.setId(id);
		repo.save(mapper.toEntity(tuserDto));
	}
	public void updateUser(String name, TUserDto tuserDto) {
		mustExist(name);
		Integer id = get(name).getId();
		tuserDto.setId(id);
		repo.save(mapper.toEntity(tuserDto));
	}
	
	// Delete Users
	public void delete(Integer id) {
		mustExist(id);
		TUserDto usr = get(id);
		usr.setActive(false);
		updateUser(id, usr);
	}
	public void delete(String name) {
		mustExist(name);
		TUserDto usr = get(name);
		usr.setActive(false);
		updateUser(name, usr);
	}
		
	// Tweet
	public void tweet(Integer id, TweetDto twt) {
		mustExist(id);
		TUserDto usr = get(id);
		List<TweetDto> twts = usr.getTweets();
		twts.add(twt);
		usr.setTweets(twts);
		repo.save(mapper.toEntity(usr));
	}
	public void tweet(String name, TweetDto twt) {
		mustExist(name);
		TUserDto usr = get(name);
		List<TweetDto> twts = usr.getTweets();
		twts.add(twt);
		usr.setTweets(twts);
		repo.save(mapper.toEntity(usr));
	}
	
	// Follow
	public void follow(String uname, String tname) {
		mustExist(uname);
		mustExist(tname);
		TUser usr = repo.findByUsername(uname).get(0);
		TUser tgt = repo.findByUsername(tname).get(0);
		// add target to user's follows list
		List<TUser> follows = usr.getFollows();
		follows.add(tgt);
		usr.setFollows(follows);
		repo.save(usr);
		// add user to target's followedBy list
		List<TUser> followedBy = tgt.getFollowedBy();
		followedBy.add(usr);
		tgt.setFollowedBy(followedBy);
		repo.save(tgt);
	}
	
	public List<TUserDto> matchName(String name) {
		return repo.findByUsername(name).stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}
}
