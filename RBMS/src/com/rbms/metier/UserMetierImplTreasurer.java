package com.rbms.metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.rbms.connection.ConnectionDB;
import com.rbms.entities.Bill;
import com.rbms.entities.Client;
import com.rbms.entities.Command;
import com.rbms.entities.Product;
import com.rbms.entities.Treasurer;
import com.rbms.entities.User;

public class UserMetierImplTreasurer implements UserMetier {
	ConnectionDB con = new ConnectionDB();
	
	public User login(User u) throws Exception {
		User nu = new User();
		try {
			PreparedStatement stx  = con.getCon().prepareStatement("SELECT * FROM user WHERE name like ? and password like ? and typeUser like ?");
			stx.setString(1, u.getName());
			stx.setString(2, u.getPassword());
			stx.setString(3, "TREASURER");
			ResultSet rs = stx.executeQuery();
			while(rs.next()){
				nu.setName(rs.getString("name"));
			}
			if(nu.getName()!=u.getName()){
				new Exception("mot de passe / pseudo incorrect");
			}
		} catch (SQLException e) {
			// TODO Auto- catch block
			e.printStackTrace();
		}
		return nu;
	}

	public DefaultTableModel showCommand(String nameTreasurer) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("id");
		dtm.addColumn("name_user");
		dtm.addColumn("created_at");
		dtm.addColumn("id_facture");
		dtm.addColumn("name_client");
		try {
		    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM command WHERE name_user like ? and activated like 1");
		    stx.setString(1, nameTreasurer);
			ResultSet rs = stx.executeQuery();
			
			while (rs.next()){
				Object[] tab ={
						(int)rs.getDouble("id"),
						rs.getString("name_user"),
						rs.getDate("created_at"),
						(int)rs.getDouble("id_facture"),
						rs.getString("name_client")
				};
				dtm.addRow(tab);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtm;
		
	}

	public DefaultTableModel showFacture(String nameTreasurer) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("id");
		dtm.addColumn("Date");
		dtm.addColumn("treasurer");
		dtm.addColumn("customer");
		try {
		    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM facture WHERE name_user like ? and activated like 1");
		    stx.setString(1, nameTreasurer);
			ResultSet rs = stx.executeQuery();
			
			while (rs.next()){
				Object[] tab ={
						(int)rs.getDouble("id"),
						rs.getDate("created_at"),
						rs.getString("name_user"),
						rs.getString("name_client"),
				};
				dtm.addRow(tab);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtm;
	}

	public void printFacture(Command c) {
		// TODO Auto-generated method stub

	}

	public DefaultTableModel showProduct() {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Name");
		dtm.addColumn("Description");
		dtm.addColumn("Price");
		dtm.addColumn("Image");
		try {
			Statement stx = con.getCon().createStatement();
			ResultSet rs = stx.executeQuery("SELECT * FROM product WHERE activated like '1'");
			
			while (rs.next()){
				Object[] tab ={
						rs.getString("name"),
						rs.getString("description"),
						(int)rs.getDouble("price"),
						rs.getString("image")
				};
				dtm.addRow(tab);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtm;
	}

	public void updateInformation(User u) {
		// TODO Auto-generated method stub

	}

	public int addCustomer(Client c) throws Exception {
		int rs = -1;
		/*try {*/
			PreparedStatement stx=con.getCon().prepareStatement("INSERT INTO client (name,tel) VALUES (?,?) ");
			stx.setString(1, c.getName());
			stx.setDouble(2, c.getTel());
			rs = stx.executeUpdate();
		/*} catch (Exception e) {
			new Exception(e.getMessage());
			System.err.println("Error "+e.getMessage());
		}*/
		return rs;
	}

	public int updateCustormer(Client c, String key) {
		int rs=-1;
		try {
			PreparedStatement stx=con.getCon().prepareStatement("UPDATE client SET name = ?, tel= ? WHERE name like ? ");
			stx.setString(1, c.getName());
			stx.setDouble(2, c.getTel());
			stx.setString(3, key);
			rs = stx.executeUpdate();
		} catch (Exception e) {
			new Exception(e.getMessage());
		}
		return rs;
	}

	public DefaultTableModel showCustomers() {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Customer name");
		dtm.addColumn("Phone Number");
		
		try {
			Statement stx = con.getCon().createStatement();
			ResultSet rs = stx.executeQuery("SELECT * FROM client WHERE actived like true");
			
			while (rs.next()){
				Object[] tab ={
						rs.getString("name"),
						(int)rs.getDouble("tel")
				};
				dtm.addRow(tab);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtm;
	}

	public void deleteCustomer(String name) {
		try {
			PreparedStatement stx = con.getCon().prepareStatement("UPDATE client SET actived = '0' WHERE name like ? ");
			stx.setString(1, name);
			stx.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Bill showFacture(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> getAllProduct() throws Exception {
		List<Product> lp = new ArrayList<Product>();
		
		Statement stx = con.getCon().createStatement();
		ResultSet rs = stx.executeQuery("SELECT * FROM product WHERE activated like '1'");
			
			while (rs.next()){
				Product p = new Product();
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setPrice(rs.getDouble("price"));
				p.setImage(rs.getString("image"));
				
				lp.add(p);
			}
		return lp;
	}

	public List<Client> getAllCustomer() throws Exception {
		List<Client> lc = new ArrayList<Client>();
		try {
			Statement stx = con.getCon().createStatement();
			ResultSet rs = stx.executeQuery("SELECT * FROM client WHERE actived like true");
			while (rs.next()){
				Client c = new Client();
				c.setName(rs.getString("name"));
				c.setTel(rs.getDouble("tel"));
				lc.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lc;
	}

	public List<Bill> getAllBill() throws Exception {
		List<Bill> lc = new ArrayList<Bill>();

		Statement stx = con.getCon().createStatement();
		ResultSet rs = stx.executeQuery("SELECT * FROM facture WHERE activated like 1");
		while (rs.next()){
			Bill b = new Bill();
			Treasurer t = new Treasurer();
			t.setName(rs.getString("name_user"));
			b.setId((int)rs.getDouble("id"));
			b.setTreasurer(t);
			lc.add(b);
		}
		return lc;
	}

	public List<Treasurer> getAllTreasurer() throws Exception {
List<Treasurer> lt = new ArrayList<Treasurer>();
		
		Statement stx = con.getCon().createStatement();
		ResultSet rs = stx.executeQuery("SELECT * FROM user WHERE active like '1'");
			
			while (rs.next()){
				Treasurer us = new Treasurer();
				us.setName(rs.getString("name"));
				us.setAddress(rs.getString("addresss"));				
				lt.add(us);
			}
		return lt;
	}

	public DefaultTableModel searchByCustomerName(String field, String value,String field2,String value2,String tabb,List<String> columns) throws Exception {
		DefaultTableModel dtm = new DefaultTableModel();
		/**dtm.addColumn("id");
		dtm.addColumn("name_user");
		dtm.addColumn("created_at");
		dtm.addColumn("id_facture");
		dtm.addColumn("name_client");**/
		for(String item:columns){
			dtm.addColumn(item);
		}
	    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM "+tabb+" WHERE "+field+" like ? and activated like 1 and "+field2+" like ?");
	    	stx.setString(1, "%"+value+"%");
	    	stx.setString(2, value2);
	    	System.out.println("Search by customer name "+value);
	    
	    ResultSet rs = stx.executeQuery();
		while (rs.next()){
			/*Object[] tab ={
					(int)rs.getDouble("id"),
					rs.getString("name_user"),
					rs.getDate("created_at"),
					(int)rs.getDouble("id_facture"),
					rs.getString("name_client")
			};*/
			Object[] tab ={};
			int i =0;
			for(String item:columns){
				try {
					tab[i] = rs.getInt(item);
				} catch (Exception e) {
					tab[i] = rs.getString(item);
				}
				i++;
			}
			dtm.addRow(tab);
		}
		return dtm;
		
	}

	public DefaultTableModel searchById(String field, int value,String field2,String value2,String tabb,List<String> columns)throws Exception {
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("id");
		dtm.addColumn("name_user");
		dtm.addColumn("created_at");
		dtm.addColumn("id_facture");
		dtm.addColumn("name_client");
		
	    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM "+tabb+" WHERE "+field+" = ? and activated like 1 and "+field2+" like ?");
	    	stx.setDouble(1, value);
	    	stx.setString(2, value2);
	    	System.out.println("Search command by id "+value);
	    
	    ResultSet rs = stx.executeQuery();
		while (rs.next()){
			Object[] tab ={
					(int)rs.getDouble("id"),
					rs.getString("name_user"),
					rs.getDate("created_at"),
					(int)rs.getDouble("id_facture"),
					rs.getString("name_client")
			};
			dtm.addRow(tab);
		}
		return dtm;
		
	}

	public DefaultTableModel searchCustomer(String field, String value) throws Exception {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Customer name");
		dtm.addColumn("Phone Number");
		
	    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM client WHERE "+field+" like ? and actived like 1");
	    	stx.setString(1, "%"+value+"%");
	    	System.out.println("Search customer by "+field);
	    
	    ResultSet rs = stx.executeQuery();
		while (rs.next()){
			Object[] tab ={
					rs.getString("name"),
					rs.getString("tel")
			};
			dtm.addRow(tab);
		}
		return dtm;
	}

	public DefaultTableModel searchProduct(String field, String value)throws Exception {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("name");
		dtm.addColumn("description");
		dtm.addColumn("price");
		dtm.addColumn("image");
		
	    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM product WHERE "+field+" like ? and activated like '1'");
	    	stx.setString(1, "%"+value+"%");
	    	System.out.println("Search product by "+field);
	    
	    ResultSet rs = stx.executeQuery();
		while (rs.next()){
			Object[] tab ={
					rs.getString("name"),
					rs.getString("description"),
					rs.getInt("price"),
					rs.getString("image")
			};
			dtm.addRow(tab);
		}
		return dtm;
	}

	public DefaultTableModel searchCommand(String field,String value,String nameTreasurer)throws Exception {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("id");
		dtm.addColumn("name_user");
		dtm.addColumn("created_at");
		dtm.addColumn("id_facture");
		dtm.addColumn("name_client");
		
	    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM command WHERE "+field+" like ? and activated like 1 and name_user like ?");
	    	stx.setString(1, "%"+value+"%");
	    	stx.setString(2, nameTreasurer);

	    	System.out.println("Search command by "+field);
	    
	    ResultSet rs = stx.executeQuery();
		while (rs.next()){
			Object[] tab ={
					rs.getInt("id"),
					rs.getString("name_user"),
					rs.getDate("created_at"),
					rs.getInt("id_facture"),
					rs.getString("name_client")
			};
			dtm.addRow(tab);
		}
		return dtm;
	}

	public DefaultTableModel searchBill(String field, String value,String nameTreasurer) throws Exception {
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("id");
		dtm.addColumn("Date");
		dtm.addColumn("treasurer");
		dtm.addColumn("customer");
		
	    PreparedStatement stx = con.getCon().prepareStatement("SELECT * FROM facture WHERE "+field+" like ? and activated like 1 and name_user like ?");
	    	stx.setString(1, "%"+value+"%");
	    	stx.setString(2, nameTreasurer);

	    	System.out.println("Search bill by "+field);
	    
	    ResultSet rs = stx.executeQuery();
		while (rs.next()){
			Object[] tab ={
					rs.getInt("id"),
					rs.getString("created_at"),
					rs.getString("name_user"),
					rs.getString("name_client")
			};
			dtm.addRow(tab);
		}
		return dtm;
	}

	public DefaultTableModel showCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	public DefaultTableModel searchBill(String field, String value)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public DefaultTableModel searchCommand(String field, String value)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public DefaultTableModel showFacture() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
