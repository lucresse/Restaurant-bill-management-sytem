package com.rbms.entities;

import java.util.List;

public class Command {
	private int id;
	private Client client;
	private List<Product> listProduit;
	private Treasurer treasurer;
	private Bill bill;
	
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Treasurer getTreasurer() {
		return treasurer;
	}
	public void setTreasurer(Treasurer treasurer) {
		this.treasurer = treasurer;
	}
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
	public List<Product> getListProduit() {
		return listProduit;
	}
	public void setListProduit(List<Product> listProduit) {
		this.listProduit = listProduit;
	}
	public Command(Client client, List<Product> listProduit) {
		super();
		this.client = client;
		this.listProduit = listProduit;
	}
	public Command() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

