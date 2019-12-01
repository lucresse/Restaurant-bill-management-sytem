package com.rbms.metier;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.rbms.entities.Bill;
import com.rbms.entities.Command;
import com.rbms.entities.Product;
import com.rbms.entities.Treasurer;

public interface TreasurerMetier {
	public void addCommand(Command c) throws Exception ;
	public void addBill(Bill b) throws Exception ;
	public void updateCommand(Command c) throws Exception;
	public void deleteCommand(int id);
	public void deleteBill(int id);
	public void addProduct(Product p) throws Exception ;
	public void addProductToCommand(Product p,Command c) throws Exception ;
	public void removeProductToCommand(double id) throws Exception ;
	public void updateProduct(Product p,String key);
	public void deleteProduct(Product p) throws Exception ;
	public void updateInformation(Treasurer t);
	public TableModel showProduct();
	public DefaultTableModel showProductByCommand(Command c);
}
