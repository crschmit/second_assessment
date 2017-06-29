package com.tweetster.mapper;

import org.mapstruct.Mapper;

import com.tweetster.dto.TweetDto;
import com.tweetster.entity.Tweet;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class, TUserMapper.class })
public interface TweetMapper {
	
	TweetDto toDto(Tweet entity);
	
	Tweet toEntity(TweetDto dto);
	
}
