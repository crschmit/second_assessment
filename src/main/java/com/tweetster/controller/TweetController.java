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

import com.tweetster.dto.TweetDto;
import com.tweetster.service.TweetService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("tweet")
public class TweetController {
	private TweetService tweetService;
	
	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}
	
	@GetMapping
	@ApiOperation(value = "", nickname = "getAllTweets")
	public List<TweetDto> getAll() {
		return tweetService.getVisible();
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "tweetExistsForId")
	public void has(@PathVariable Integer id, HttpServletResponse httpResponse) {
		if(!tweetService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getTweetById")
	public TweetDto get(@PathVariable Integer id) {
		return tweetService.get(id);
	}
	
	@PostMapping
	@ApiOperation(value = "", nickname = "postNewTweet")
	public Integer post(@RequestBody @Validated TweetDto tweetDto, HttpServletResponse httpResponse) {
		Integer id = tweetService.post(tweetDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "putTweetWithId")
	public void put(@PathVariable Integer id, @RequestBody @Validated TweetDto tweetDto,HttpServletResponse httpResponse) {
		tweetService.put(id, tweetDto);

	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteTweetAtId")
	public void delete(@PathVariable Integer id, HttpServletResponse httpResponse) {
		tweetService.markAsDeleted(id);
	}
	
	@DeleteMapping("drop/{id}")
	@ApiOperation(value = "", nickname = "dropTweetAtId")
	public void drop(@PathVariable Integer id, HttpServletResponse httpResponse) {
		tweetService.delete(id);
	}
	
}
