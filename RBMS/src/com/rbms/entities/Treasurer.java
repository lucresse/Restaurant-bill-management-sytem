package com.rbms.entities;

public class Treasurer extends User {
private String name;
private String address;

public Treasurer() {
	super();
	// TODO Auto-generated constructor stub
}


public Treasurer(String pseudo, String password) {
	super(pseudo, password);
	// TODO Auto-generated constructor stub
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

}
