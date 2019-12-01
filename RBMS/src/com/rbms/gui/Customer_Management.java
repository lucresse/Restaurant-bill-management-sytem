package com.rbms.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.rbms.entities.Client;
import com.rbms.entities.Treasurer;
import com.rbms.entities.User;
import com.rbms.metier.UserMetier;
import com.rbms.metier.UserMetierImplTreasurer;

public class Customer_Management extends JFrame {

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Customer_Management.user = user;
	}

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private UserMetier um = new UserMetierImplTreasurer();
	private static User user = new Treasurer();
	private JTable table;
	JRadioButton rdbtnName;
	JRadioButton rdbtnAddress;
	private static boolean rdSelected = true;
	
	private static String userSelected =new String();
	private static String phoneSelected =new String();
	/**&	a²
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Management frame = new Customer_Management();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Customer_Management() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\logo.jpg"));
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Customer Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\03.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get name
				String name = textField.getText();
				//get tel
				String stel = textField_1.getText().toString();
				//manage errors
				double tel=0;
				List<String> error = new ArrayList<String>();
				try {
					tel  = (double)Integer.parseInt(stel);
				}catch(NumberFormatException e){
					error.add("phone number must be numeric");
					System.out.println("phone number must be numeric");
				}
				
				//Verify if name is valid
				if(!name.isEmpty()){
					if(!name.matches("[a-zA-Z0-9]+")){
						error.add("Name is invalid");
						System.out.println("Name is invalid");
					}
				}else{
					error.add("Name is empty");
					System.out.println("Name is empty");
				}
				//Verify if tel is valid 
				if(tel < 100000.0){
					error.add("phone number invalid, "+"too short");
					System.out.println("Phone number too short");
				}
				//Verify if the size of error list is empty 
				if(error.size()==0){
					try {
						Client c = new Client (name,tel);
						um.addCustomer(c);
						table.setModel(um.showCustomers());
						JOptionPane.showMessageDialog(null, "One customer added", "success", JOptionPane.INFORMATION_MESSAGE);
					
						textField.setText("");
						textField_1.setText("");
						
					} catch (MySQLIntegrityConstraintViolationException e) {
						System.out.println("Customer already exists");
						String duplicateError = e.getMessage();
						String derror="";
						if(duplicateError.matches("(.*)PRIMARY(.*)")){
							System.out.println("");
							derror="customer name already exist";
						}else
							if(duplicateError.matches("(.*)tel(.*)")){
								System.out.println("");
								derror="customer tel already exist";
							}
							else{
								derror="error "+e.getMessage();
							}
						JOptionPane.showMessageDialog(null, derror, "error", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e) {
						error.add("Error "+e.getMessage());
						System.out.println("error "+e.getMessage());
						JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					System.err.println("Error of adding");
					String msgError = "";
					for(String err: error){
						msgError += "\n"+err;
					}
					System.err.println(msgError);
					JOptionPane.showMessageDialog(null, msgError, "error", JOptionPane.ERROR_MESSAGE);				
				}
				
			}

			
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\18.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get name
				String name = textField.getText();
				//get tel
				String stel = textField_1.getText().toString();
				//manage errors
				double tel=0;
				List<String> error = new ArrayList<String>();
				try {
					tel  = (double)Integer.parseInt(stel);
				}catch(NumberFormatException e){
					error.add("phone number must be numeric");
					System.out.println("phone number must be numeric");
				}
				
				//Verify if name is valid
				if(!name.isEmpty()){
					if(!name.matches("[a-zA-Z0-9]+")){
						error.add("Name is invalid");
						System.out.println("Name is invalid");
					}
				}else{
					error.add("Name is empty");
					System.out.println("Name is empty");
				}
				//Verify if tel is valid 
				if(tel < 100000.0){
					error.add("phone number invalid, "+"too short");
					System.out.println("Phone number too short");
				}
				
				if(error.size()==0){
					Client c=new Client(name,tel);
					System.out.println(c.getName()+" "+ c.getTel()+" key "+userSelected);
					um.updateCustormer(c, userSelected);
					table.setModel(um.showCustomers());
					textField.setText("");
					textField_1.setText("");
					JOptionPane.showMessageDialog(null, "One customer updated", "success", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					String msgErr = new String();
					for(String err: error){
						msgErr += "\n"+err; 
					}
					JOptionPane.showMessageDialog(null, msgErr, "warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\01.png"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int boxDeleteUser = JOptionPane.showConfirmDialog(null, "are you sure to delete "+userSelected+" ?", "Warning", JOptionPane.YES_NO_OPTION);
					if(boxDeleteUser==0){
						um.deleteCustomer(userSelected);
						table.setModel(um.showCustomers());
						textField.setText("");
						textField_1.setText("");
						JOptionPane.showMessageDialog(null, "deleted succeed", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					// TODO  Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblCustomerMa = new JLabel("CUSTOMER MANAGEMENT");
		lblCustomerMa.setForeground(Color.BLUE);
		lblCustomerMa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		JButton btnRetour = new JButton("Back");
		btnRetour.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\45.png"));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.activeCaption));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnRetour))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
							.addGap(75)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
								.addComponent(panel_2, 0, 0, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblCustomerMa, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
							.addGap(68))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCustomerMa, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(109)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnUpdate)
								.addComponent(btnDelete))
							.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
							.addComponent(btnRetour))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JLabel lblSearch = new JLabel("Search by");
		
		rdbtnName = new JRadioButton("Name");
		rdbtnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdSelected =!rdSelected;
				rdbtnName.setSelected(rdSelected);
				rdbtnAddress.setSelected(!rdSelected);
			}
		});
		rdbtnName.setSelected(rdSelected);

		
		rdbtnAddress = new JRadioButton("Address");
		rdbtnAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdSelected =!rdSelected;
				rdbtnName.setSelected(rdSelected);
				rdbtnAddress.setSelected(!rdSelected);

			}
		});
		rdbtnAddress.setSelected(!rdSelected);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String key = textField_2.getText().toString();
				try {
					if(rdSelected){
						table.setModel(um.searchCustomer("name", key));
					}else{
						table.setModel(um.searchCustomer("tel", key));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblSearch)
							.addGap(41)
							.addComponent(rdbtnName)
							.addGap(18)
							.addComponent(rdbtnAddress, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnSearch)
							.addGap(31)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSearch)
						.addComponent(rdbtnName)
						.addComponent(rdbtnAddress))
					.addGap(7)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearch)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblAddress = new JLabel("tel\r\n");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(46, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblAddress))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
					.addGap(17))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddress))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addContainerGap())
		);
		table = new JTable();
		//SELECTION D'UNE LIGNE GRAPHIQUEMENT DANS LA TABLE
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				System.out.println("the selected user is "+table.getValueAt(i,0));
				userSelected=table.getValueAt(i,0).toString();
				phoneSelected = table.getValueAt(i,1).toString();
				try{
					textField.setText(userSelected);
					textField_1.setText(""+Integer.parseInt(phoneSelected));
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		});
		table.setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setViewportView(table);
		table.setModel(um.showCustomers());
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
