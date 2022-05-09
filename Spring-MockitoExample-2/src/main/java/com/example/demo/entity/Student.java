package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Student {
	@Id
	private int id;
	
	@NonNull
	private String name;

	@NonNull
	private String clgName;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, String clgName) {
		super();
		this.id = id;
		this.name = name;
		this.clgName = clgName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClgName() {
		return clgName;
	}
	public void setClgName(String clgName) {
		this.clgName = clgName;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", clgName=" + clgName + "]";
	}
	
}