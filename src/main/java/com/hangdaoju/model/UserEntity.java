package com.hangdaoju.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    @Id
    private String id;
    private String userName;
    private String passWord;
    private List<Friends> friends; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public List<Friends> getFriends() {
		return friends;
	}
	public void setFriends(List<Friends> friends) {
		this.friends = friends;
	}
	

    
}