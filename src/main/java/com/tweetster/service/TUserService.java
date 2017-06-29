package com.tweetster.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tweetster.dto.TUserDto;
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

}
