package com.rbms.metier;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.rbms.entities.Bill;
import com.rbms.entities.Client;
import com.rbms.entities.Command;
import com.rbms.entities.Product;
import com.rbms.entities.Treasurer;
import com.rbms.entities.User;

public interface UserMetier {
	public User login(User u) throws Exception;
	public DefaultTableModel showCommand(String nameTreasurer);
	public DefaultTableModel showFacture(String nameTreasurer);
	public void printFacture(Command c);
	public DefaultTableModel showProduct();
	public void updateInformation(User u);
	public int addCustomer(Client c) throws Exception;
	public int updateCustormer(Client c, String key);
	public DefaultTableModel showCustomers();
	public void deleteCustomer(String name);
	public List<Product> getAllProduct() throws Exception;
	public List<Client> getAllCustomer() throws Exception;
	public List<Treasurer> getAllTreasurer() throws Exception;
	public List<Bill> getAllBill() throws Exception;
	public DefaultTableModel searchById(String field,int value,String field2,String value2,String tab,List<String> columns) throws Exception;
	public DefaultTableModel searchByCustomerName(String field,String value,String field2,String value2,String tab,List<String> columns) throws Exception;
	public DefaultTableModel searchCustomer(String field,String value) throws Exception;
	public DefaultTableModel searchProduct(String field,String value) throws Exception;
	public DefaultTableModel searchCommand(String field,String value,String nameTreasurer) throws Exception;
	public DefaultTableModel searchBill(String field,String value,String nameTreasurer) throws Exception;
	public DefaultTableModel searchBill(String field,String value) throws Exception;
	public DefaultTableModel searchCommand(String field,String value) throws Exception;
	public DefaultTableModel showFacture();

	public DefaultTableModel showCommand();


}