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
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteTUserAtId")
	public void delete(@PathVariable Integer id, HttpServletResponse httpResponse) {
		tuserService.delete(id);
	}
}
