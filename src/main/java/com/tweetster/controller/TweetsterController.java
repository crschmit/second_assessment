package com.tweetster.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetster.dto.TUserDto;
import com.tweetster.dto.TweetDto;
import com.tweetster.service.TUserService;
import com.tweetster.service.TweetService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("tweetster")
public class TweetsterController {
	private TUserService tuserService;
	private TweetService tweetService;
	
	public TweetsterController(TUserService tuserService, TweetService tweetService) {
		this.tuserService = tuserService;
		this.tweetService = tweetService;
	}
	
	@GetMapping("/users")
	@ApiOperation(value = "", nickname = "getAllUsers")
	public List<TUserDto> getAllUsers() {
		return tuserService.getAll();
	}
	
	@GetMapping("/users/{uid}")
	@ApiOperation(value = "", nickname = "getUserById")
	public TUserDto getUser(@PathVariable Integer uid) {
		return tuserService.get(uid);
	}
	
	
	@GetMapping("/tweets")
	@ApiOperation(value = "", nickname = "getAllTweets")
	public List<TweetDto> getAllTweets() {
		return tweetService.getAll();
	}

	@GetMapping("/tweets/{tid}")
	@ApiOperation(value = "", nickname = "getTweetById")
	public TweetDto getTweet(@PathVariable Integer tid) {
		return tweetService.get(tid);
	}
	
}
