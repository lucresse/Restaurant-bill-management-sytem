package com.rbms.metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import com.rbms.connection.ConnectionDB;
import com.rbms.entities.Bill;
import com.rbms.entities.Command;
import com.rbms.entities.Product;
import com.rbms.entities.Treasurer;

public class TreasurerMetierImpl implements TreasurerMetier {

	ConnectionDB con = new ConnectionDB();
	public void addCommand(Command c) throws Exception {
		PreparedStatement stx=con.getCon().prepareStatement("INSERT INTO command (name_user,id_facture,name_client) VALUE (?,?,?)");
		
		stx.setString(1, c.getTreasurer().getName());
		stx.setDouble(2, c.getBill().getId());
		stx.setString(3, c.getClient().getName());
		stx.executeUpdate();
		
		//for(Product p:c.getListProduit()){
			//tm.addProduct(p);
		//}
			
	}

	public void updateCommand(Command c) throws Exception {
		
			PreparedStatement stx=con.getCon().prepareStatement("UPDATE command SET name_client = ?, id_facture = ? WHERE id like ? and activated like '1' ");
			stx.setString(1, c.getClient().getName());
			stx.setInt(2, c.getBill().getId());
			
			stx.setInt(3, c.getId());
			stx.executeUpdate();
			
	}

	public void deleteCommand(int id) {
		try {
			PreparedStatement stx = con.getCon().prepareStatement("UPDATE command SET activated = '0' WHERE id like ?");
			stx.setInt(1, id);
			stx.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addProduct(Product p) throws Exception {
			PreparedStatement stx=con.getCon().prepareStatement("INSERT INTO product (name,description,price,image) VALUES (?,?,?,?) ");
			stx.setString(1, p.getName());
			stx.setString(2, p.getDescription());
			stx.setDouble(3, p.getPrice());
			stx.setString(4, p.getImage());
			stx.executeUpdate();
	}

	public void updateProduct(Product p,String key) {
		try {
			PreparedStatement stx=con.getCon().prepareStatement("UPDATE product SET name = ?, description= ?, price = ?, image = ? WHERE name like ? and activated like '1' ");
			stx.setString(1, p.getName());
			stx.setString(2, p.getDescription());
			stx.setDouble(3, p.getPrice());
			stx.setString(4, p.getImage());
			stx.setString(5, key);
			stx.executeUpdate();
		} catch (Exception e) {
			new Exception(e.getMessage());
		}
	}

	public void deleteProduct(Product p) throws Exception  {
			PreparedStatement stx = con.getCon().prepareStatement("UPDATE product SET activated = '0' WHERE name like ? ");
			stx.setString(1, p.getName());
			stx.executeUpdate();
	}

	public void updateInformation(Treasurer t) {
		// TODO Auto-generated method stub
		
	}

	public DefaultTableModel showProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	public  DefaultTableModel showProductByCommand(Command c) {
		DefaultTableModel tb = new DefaultTableModel();
		tb.addColumn("ID CP");
		tb.addColumn("Product name");
		tb.addColumn("Price");
		try {
			PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM command_product WHERE id_command = ?");
			ps.setDouble(1, c.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Object[] t = {
						rs.getInt("id"),
						rs.getString("name_product"),
						"--"
				};
				tb.addRow(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tb;
	}

	public void addProductToCommand(Product p, Command c) throws Exception {
		PreparedStatement stx = con.getCon().prepareStatement("INSERT INTO command_product (id_command,name_product) VALUES (?,?)");
		stx.setDouble(1, c.getId());
		stx.setString(2, p.getName());
		stx.executeUpdate();
	}
	public void removeProductToCommand(double id) throws Exception { 
		PreparedStatement stx = con.getCon().prepareStatement("DELETE FROM command_product WHERE id = ?");
		stx.setDouble(1, id);
		stx.executeUpdate();
		System.out.println(id);
	}

	public void addBill(Bill b) throws Exception {
		PreparedStatement stx = con.getCon().prepareStatement("INSERT INTO facture (name_user,name_client) VALUES (?,?)");
		stx.setString(1, b.getTreasurer().getName());
		stx.setString(2, b.getClient().getName());
		stx.executeUpdate();
	}

	public void deleteBill(int id) {
		try {
			PreparedStatement stx = con.getCon().prepareStatement("UPDATE facture SET activated = '0' WHERE id like ?");
			stx.setInt(1, id);
			stx.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}