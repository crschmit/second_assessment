package com.tweetster.dto;

import java.util.List;

import com.tweetster.embeddable.UserCredential;

public class TUserDto {

	private Integer id;
	
	private String username;
	
	private List<TweetDto> tweets;
	
	private boolean active;
	
	private UserCredential credential;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<TweetDto> getTweets() {
		return tweets;
	}

	public void setTweets(List<TweetDto> tweets) {
		this.tweets = tweets;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean deleted) {
		this.active = deleted;
	}

	public UserCredential getCredential() {
		return credential;
	}

	public void setCredential(UserCredential credential) {
		this.credential = credential;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TUserDto other = (TUserDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
