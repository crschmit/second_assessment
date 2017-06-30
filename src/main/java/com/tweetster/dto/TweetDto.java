package com.tweetster.dto;

import java.sql.Timestamp;

public class TweetDto {

	private Integer id;
	private Timestamp posted;
	private String content;
	private boolean deleted;
	private TUserDto author;
//	private TweetDto repostOf;
//	private TweetDto replyTo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getPosted() {
		return posted;
	}
	public void setPosted(Timestamp posted) {
		this.posted = posted;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public TUserDto getAuthor() {
		return author;
	}
	public void setAuthor(TUserDto author) {
		this.author = author;
	}
//	public TweetDto getRepostOf() {
//		return repostOf;
//	}
//	public void setRepostOf(TweetDto repostOf) {
//		this.repostOf = repostOf;
//	}
//	public TweetDto getReplyTo() {
//		return replyTo;
//	}
//	public void setReplyTo(TweetDto replyTo) {
//		this.replyTo = replyTo;
//	}
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
		TweetDto other = (TweetDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
