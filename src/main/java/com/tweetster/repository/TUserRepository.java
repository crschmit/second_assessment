package com.tweetster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetster.embeddable.UserCredential;
import com.tweetster.entity.TUser;

public interface TUserRepository extends JpaRepository<TUser, Integer>{
	List<TUser> findByUsername(String username);
	List<TUser> findByCredential(UserCredential credential);
}
