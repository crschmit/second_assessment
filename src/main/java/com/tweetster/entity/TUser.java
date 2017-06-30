package com.tweetster.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.tweetster.dto.datatype.BaseEntity;
import com.tweetster.embeddable.UserCredential;
import com.tweetster.embeddable.UserProfile;

@Entity
public class TUser implements BaseEntity<Integer>{

	@Id
	@GeneratedValue
	private Integer id;
	
	private String username;
	
	private boolean active;
	
	@OneToMany(mappedBy = "author")
	private List<Tweet> tweets = new ArrayList<Tweet>();

	@ManyToMany
	private List<TUser> follows = new ArrayList<TUser>();
	
	@ManyToMany
	private List<TUser> followedBy = new ArrayList<TUser>();

	@Embedded
	private UserCredential credential;
	
	@Embedded
	private UserProfile profile;
	
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<TUser> getFollows() {
		return follows;
	}

	public void setFollows(List<TUser> follows) {
		this.follows = follows;
	}

	public List<TUser> getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(List<TUser> followedBy) {
		this.followedBy = followedBy;
	}

	public UserCredential getCredential() {
		return credential;
	}

	public void setCredential(UserCredential credential) {
		this.credential = credential;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
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
		TUser other = (TUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
