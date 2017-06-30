package com.tweetster.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class UserCredential {
	
	private String uname;
	private String pword;
	
	public String getUsername() {
		return uname;
	}
	public void setUsername(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return pword;
	}
	public void setPassword(String pword) {
		this.pword = pword;
	}
	
}
