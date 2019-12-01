package com.rbms.entities;

import java.util.List;

public class Bill {
	private int id;
	private Client client;
	private Treasurer treasurer;
	private List<Command> listCommand;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Treasurer getTreasurer() {
		return treasurer;
	}
	public void setTreasurer(Treasurer treasurer) {
		this.treasurer = treasurer;
	}
	public List<Command> getListCommand() {
		return listCommand;
	}
	public void setListCommand(List<Command> listCommand) {
		this.listCommand = listCommand;
	}
	public Bill(Client client, Treasurer treasurer, List<Command> listCommand) {
		super();
		this.client = client;
		this.treasurer = treasurer;
		this.listCommand = listCommand;
	}
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
