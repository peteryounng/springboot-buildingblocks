package com.stacksimplify.dto;

public class UserMsDto {
	private Long id;
	private String username;
	private String emailadress;
	
	
	public UserMsDto() {
	}
	public UserMsDto(Long id, String username, String emailadress) {
		super();
		this.id = id;
		this.username = username;
		this.emailadress = emailadress;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailadress() {
		return emailadress;
	}
	public void setEmailadress(String emailadress) {
		this.emailadress = emailadress;
	}
	
	
}
