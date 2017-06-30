package com.tweetster.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//GET validate/tag/exists/{label}
	
	// GET validate/username/exists/@{username}
	
	// GET validate/username/available/@{username}
	
	// GET users
	@GetMapping("/users")
	@ApiOperation(value = "", nickname = "getAllUsers")
	public List<TUserDto> getAllUsers() {
		return tuserService.getActive();
	}	
	
	// POST users
	@PostMapping("/users")
	@ApiOperation(value = "", nickname = "addUser")
	public Integer addUser(@RequestBody @Validated TUserDto usr, HttpServletResponse httpResponse) {
		Integer id = tuserService.addUser(usr);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	// GET users/@{username}
	@GetMapping("/users/{name}")
	@ApiOperation(value = "", nickname = "getUserByName")
	public TUserDto getUser(@PathVariable String name) {
		return tuserService.get(name);
	}
	
	// PATCH users/@{username}
//	@PatchMapping("/users/@{name}")
//	@ApiOperation(value = "", nickname = "updateUserByName")
//	public TUserDto updateUser(@PathVariable String name, , HttpServletResponse httpResponse) {
//		
//	}
	
	
	// DELETE users/@{username}
//	@DeleteMapping("/users/@{name}")
//	@ApiOperation(value = "", nickname = "deleteUserByName")
//	public void deleteUser() {
//		
//	}
	
	// POST users/@{username}/follow
	@PostMapping("/users/@{usr}/@{tgt}/follow")
	@ApiOperation(value = "", nickname = "followByName")
	public void follow(@PathVariable String usr, @PathVariable String tgt, HttpServletResponse httpResponse) {
		tuserService.follow(usr, tgt);
	}
	
	// POST users/@{username}/unfollow
	
	// GET users/@{username}/feed
	
	// GET users/@{username}/tweets
	
	// GET users/@{username}/mentions
	
	// GET users/@{username}/followers
	
	// GET users/@{username}/following
	
	// GET tags
	
	// GET tags/{label}
	
	// GET tweets
	
	// POST tweets
	
	// GET tweets/{id}
	
	// DELETE tweets/{id}
	
	// POST tweets/{id}/like
	
	// POST tweets/{id}/reply
	
	// POST tweets/{id}/repost
	
	// GET tweets/{id}/tags
	
	// GET tweets/{id}/likes
	
	// GET tweets/{id}/context
	
	// GET tweets/{id}/replies
	
	// GET tweets/{id}/reposts
	
	// GET tweets/{id}/mentions
	
	//
	
	
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
