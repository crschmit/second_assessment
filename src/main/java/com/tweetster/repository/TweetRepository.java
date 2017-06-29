package com.tweetster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetster.entity.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {

}
