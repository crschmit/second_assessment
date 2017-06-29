package com.tweetster.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tweetster.dto.TweetDto;
import com.tweetster.entity.Tweet;
import com.tweetster.exception.ReferencedEntityNotFoundException;
import com.tweetster.mapper.TweetMapper;
import com.tweetster.repository.TweetRepository;

@Service
public class TweetService {

	private TweetRepository repo;
	private TweetMapper mapper;
	
	public TweetService(TweetRepository repo, TweetMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private void mustExist(Integer id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(Tweet.class, id);
	}
	
	public List<TweetDto> getAll() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	public List<TweetDto> getVisible() {
		return repo.findAll().stream()
				.map(mapper::toDto)
				.filter(t -> !t.isDeleted())
				.collect(Collectors.toList());
	}
	
	public boolean has(Integer id) {
		return repo.exists(id);
	}
	
	public TweetDto get(Integer id) {
		mustExist(id);
		return mapper.toDto(repo.findOne(id));
	}
	
	public Integer post(TweetDto tweetDto) {
		tweetDto.setId(null);
		tweetDto.setPosted(new Timestamp((new Date()).getTime()));
		return repo.save(mapper.toEntity(tweetDto)).getId();
	}
	
	public void put(Integer id, TweetDto tweetDto) {
		mustExist(id);
		tweetDto.setId(id);
		repo.save(mapper.toEntity(tweetDto));
	}
	
	public void markAsDeleted(Integer id) {
		mustExist(id);
		TweetDto tdto = get(id);
		tdto.setDeleted(true);
		repo.save(mapper.toEntity(tdto));
	}
	
	public void delete(Integer id) {
		mustExist(id);
		repo.delete(id);
	}
	
}
