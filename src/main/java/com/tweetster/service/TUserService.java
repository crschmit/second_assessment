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
	
	private void mustExist(Integer id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(TUser.class, id);
	}
	
	public List<TUserDto> getAll() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	public boolean has(Integer id) {
		return repo.exists(id);
	}

	public TUserDto get(Integer id) {
		mustExist(id);
		return mapper.toDto(repo.findOne(id));
	}

	public Integer post(TUserDto tuserDto) {
		tuserDto.setId(null);
		return repo.save(mapper.toEntity(tuserDto)).getId();
	}

	public void put(Integer id, TUserDto tuserDto) {
		mustExist(id);
		tuserDto.setId(id);
		repo.save(mapper.toEntity(tuserDto));
	}

	public void delete(Integer id) {
		mustExist(id);
		repo.delete(id);
	}
	
	public List<TweetDto> getTweets(Integer id) {
		mustExist(id);
		TUserDto usr = mapper.toDto(repo.findOne(id));
		return usr.getTweets();
	}
	
	public void putTweet(Integer id, TweetDto twt) {
		mustExist(id);
		TUserDto usr = mapper.toDto(repo.findOne(id));
		List<TweetDto> twts = usr.getTweets();
		twts.add(twt);
		usr.setTweets(twts);
		repo.save(mapper.toEntity(usr));
	}
	
	public TUserDto getByName(String uname) {
		List<TUserDto> matches = getAll().stream()
				.filter(u -> u.getUsername().equals(uname))
				.collect(Collectors.toList());
		
		if(matches.size() == 0)
			throw new ReferencedEntityNotFoundException(TUser.class, uname);
		
		return matches.get(0);
	}
	
	public List<TUserDto> getFollowsByName(String uname) {
		TUserDto ud = getByName(uname);
		TUser usr = repo.findOne(ud.getId());
		return usr.getFollows().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	public List<TUserDto> getFollowedByByName(String uname) {
		TUserDto ud = getByName(uname);
		TUser usr = repo.findOne(ud.getId());
		return usr.getFollowedBy().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}
	
	public void follow(String uname, String tname) {
		TUserDto u = getByName(uname);
		TUserDto t = getByName(tname);
		TUser usr = repo.findOne(u.getId());
		TUser tgt = repo.findOne(t.getId());
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
	
	public void tweet(String uname, TweetDto twt) {
		TUserDto usr = getByName(uname);
		putTweet(usr.getId(), twt);
	}
}
