package com.tweetster.mapper;

import org.mapstruct.Mapper;

import com.tweetster.dto.TUserDto;
import com.tweetster.entity.TUser;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class, TweetMapper.class })
public interface TUserMapper {
	
	TUserDto toDto(TUser entity);
	
	TUser toEntity(TUserDto dto);
	
}
