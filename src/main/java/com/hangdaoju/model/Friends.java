package com.hangdaoju.model;

import org.springframework.data.annotation.Id;

public class Friends {
	@Id
	private Object id;
	private String name;
	private Integer age;
	
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
