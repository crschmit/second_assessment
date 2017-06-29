package com.tweetster.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweetster.dto.TUserDto;
import com.tweetster.dto.TweetDto;
import com.tweetster.service.TUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("tuser")
public class TUserController {
	private TUserService tuserService;
	
	public TUserController(TUserService tuserService) {
		this.tuserService = tuserService;
	}
	
	@GetMapping
	@ApiOperation(value = "", nickname = "getAllTUsers")
	public List<TUserDto> getAll() {
		return tuserService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "tuserExistsForId")
	public void has(@PathVariable Integer id, HttpServletResponse httpResponse) {
		if(!tuserService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getTUserById")
	public TUserDto get(@PathVariable Integer id) {
		return tuserService.get(id);
	}
	
	@GetMapping("/name/{name}")
	@ApiOperation(value = "", nickname = "getUserByName")
	public TUserDto getByName(@PathVariable String name) {
		return tuserService.getByName(name);
	}
	
	@GetMapping("/name/{name}/tweets")
	@ApiOperation(value = "", nickname = "getUserByName")
	public List<TweetDto> getTweetsByName(@PathVariable String name) {
		TUserDto usr = tuserService.getByName(name);
		return tuserService.getTweets(usr.getId());
	}
	
	@GetMapping("/name/{name}/follows")
	@ApiOperation(value = "", nickname = "getFollowsByName")
	public List<TUserDto> getFollowsByName(@PathVariable String name) {
		return tuserService.getFollowsByName(name);
	}

	@GetMapping("/name/{name}/followedBy")
	@ApiOperation(value = "", nickname = "getFollowedByByName")
	public List<TUserDto> getFollowedByByName(@PathVariable String name) {
		return tuserService.getFollowedByByName(name);
	}	
	
	@PostMapping
	@ApiOperation(value = "", nickname = "postNewTUser")
	public Integer post(@RequestBody @Validated TUserDto tuserDto, HttpServletResponse httpResponse) {
		Integer id = tuserService.post(tuserDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "putTUserWithId")
	public void put(@PathVariable Integer id, @RequestBody @Validated TUserDto tuserDto, HttpServletResponse httpResponse) {
		tuserService.put(id, tuserDto);
	}
	
	@PutMapping("/{uname}/follow/{tname}")
	@ApiOperation(value = "", nickname = "putFollowByName")
	public void follow(@PathVariable String uname, @PathVariable String tname, HttpServletResponse httpResponse) {
		tuserService.follow(uname, tname);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteTUserAtId")
	public void delete(@PathVariable Integer id, HttpServletResponse httpResponse) {
		tuserService.delete(id);
	}
}
