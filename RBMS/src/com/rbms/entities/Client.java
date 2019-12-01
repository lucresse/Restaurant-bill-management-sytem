package com.rbms.entities;

public class Client {
	private int id;
	private String name;
	private double tel;
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
	public double getTel() {
		return tel;
	}
	public void setTel(double tel) {
		this.tel = tel;
	}
	public Client(String name, double tel) {
		super();
		this.name = name;
		this.tel = tel;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
