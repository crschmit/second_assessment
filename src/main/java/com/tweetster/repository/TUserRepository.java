package com.tweetster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetster.entity.TUser;

public interface TUserRepository extends JpaRepository<TUser, Integer>{

}
