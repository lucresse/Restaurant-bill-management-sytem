package com.rbms.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.rbms.entities.Admin;
import com.rbms.entities.Treasurer;
import com.rbms.entities.User;
import com.rbms.metier.UserMetier;
import com.rbms.metier.UserMetierImpAdmin;
import com.rbms.metier.UserMetierImplTreasurer;

import javax.swing.ImageIcon;

public class Login extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField textField;
	private JRadioButton rdbtnAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	  /**
	 * Create the dialog.
	 */
	public Login() {	
		setFont(new Font("DFKai-SB", Font.BOLD | Font.ITALIC, 15));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		//to set a title of this windows (login)
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		//to centralize
		setLocationRelativeTo(null);
		JButton btnConnection = new JButton("Connection");
		btnConnection.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\41.png"));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\01.png"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.err.println("Windows Login exited");
				System.exit(0);
			}
		});
		
		passwordField = new JPasswordField();
		
		JLabel lblPassword = new JLabel("password");
		
		JLabel lblPseudo = new JLabel("pseudo");
		
		textField = new JTextField();
		textField.setColumns(10);
		rdbtnAdmin = new JRadioButton("Admin");
		
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get name
				String name = textField.getText();
				//get  password
				char[] pass = passwordField.getPassword();
				String password = "";
				for(int i=0; i<pass.length; i++){
					password += pass[i];
				}
				System.out.println("Login Windows");
				System.out.println("Connection with name: "+name+" and password: "+password);
				//Test if Admin
				UserMetier um = null;
				//UserMetier umt = null;
				User user = new User();
				user.setName(name);
				user.setPassword(password);
				//User user = new Admin();
				//User ur = new Treasurer();
				if(rdbtnAdmin.isSelected()){
					System.out.println("Is admin ?");
					um = new UserMetierImpAdmin();
					user = new Admin();
					user.setName(name);
					user.setPassword(password);
					User u = null;
					try {
						try {
							u = um.login(user);
							//JOptionPane.showMessageDialog(null, "empty or incorrect name", "Error Connection", JOptionPane.ERROR_MESSAGE);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//JOptionPane.showMessageDialog(null, "empty or incorrect name", "Error Connection", JOptionPane.ERROR_MESSAGE);

						if(u.getName().isEmpty()){
							System.err.println("User not found 1");
							//JOptionPane.showMessageDialog(null, "emptyame", "Error Connection", JOptionPane.ERROR_MESSAGE);

						}else{
							System.out.println("Connection successfull");
							//Open Menu Windows 
							dispose();
							JOptionPane.showMessageDialog(null, "we wish welcome to "+u.getName(), "Connection succesfull", JOptionPane.INFORMATION_MESSAGE);
						
						MenuAdmin ma = new MenuAdmin();
						ma.setVisible(true);
						}
						//JOptionPane.showMessageDialog(null, "emptyame", "Error Connection", JOptionPane.ERROR_MESSAGE);

					}catch(NullPointerException e2){
						e2.printStackTrace();
					}
					
				}else{
					System.out.println("Is Treasurer ? ");
					um = new UserMetierImplTreasurer();
					user = new Treasurer();
					user.setName(name);
					user.setPassword(password);
					User u = null;
					try {
						try {
							u = um.login(user);
							//JOptionPane.showMessageDialog(null, "empty or incorrect name", "Error Connection", JOptionPane.ERROR_MESSAGE);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//JOptionPane.showMessageDialog(null, "empty or incorrect name", "Error Connection", JOptionPane.ERROR_MESSAGE);

						if(u.getName().isEmpty()){
							System.err.println("empty ame");
							//JOptionPane.showMessageDialog(null, "empty name", "Error Connection", JOptionPane.ERROR_MESSAGE);
						}else{
							System.out.println("Connection successfull");
							//Open Menu Windows 
							dispose();
							JOptionPane.showMessageDialog(null, "we wish welcome to "+u.getName(), "Connection succesfull", JOptionPane.INFORMATION_MESSAGE);
						
						Menu m = new Menu();
						m.setVisible(true);
					}
					}catch(NullPointerException e1){
				
						e1.printStackTrace();					}
					
				}
				//user.setName(name);
				//user.setPassword(password);
				/*try {
					//umt = new UserMetierImplTreasurer();
					//User ut = um.login(user);
					//um = new UserMetierImpAdmin();
					User u = um.login(user);
					if((u.getName().isEmpty()||ut.getName().isEmpty())){
						
						System.err.println("User not found 1");
					}
					else{ 
						if(rdbtnAdmin.isSelected()){
							System.out.println("Is admin ?");
							um = new UserMetierImpAdmin();
							user = new Admin();
							MenuAdmin ma = new MenuAdmin();
							ma.setVisible(true);
							setLocationRelativeTo(null);
							System.out.println("Connection successfull");
							//Open Menu Windows 
							dispose();
							JOptionPane.showMessageDialog(null, "we wish welcome to "+u.getName(), "Connection succesfull", JOptionPane.INFORMATION_MESSAGE);
						
						}else{
							System.out.println("Is Treasurer ? ");
							umt = new UserMetierImplTreasurer();
							user = new Treasurer();
						    //Menu.setUser(u);
						    Menu m = new Menu();
						    m.setVisible(true);
						    System.out.println("Connection successfull");
							//Open Menu Windows 
							dispose();
							JOptionPane.showMessageDialog(null, "we wish welcome to "+u.getName(), "Connection succesfull", JOptionPane.INFORMATION_MESSAGE);
						
						}
					
				
					}
				} catch (Exception e) {
					System.err.println("User not found");
					//show error on dialog
					JOptionPane.showMessageDialog(null, "Name/Password invalid", "Error Connection", JOptionPane.ERROR_MESSAGE);
				}*/
			}
		});
		
		JLabel lblIcon = new JLabel("icon");
		lblIcon.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\logo.jpg"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(lblPseudo))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnAdmin)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnConnection)
									.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
									.addComponent(btnCancel))
								.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
								.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
							.addGap(85))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(197)
					.addComponent(lblIcon)
					.addContainerGap(228, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIcon)
					.addGap(89)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPseudo)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addComponent(rdbtnAdmin)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConnection)
						.addComponent(btnCancel))
					.addGap(21))
		);
		getContentPane().setLayout(groupLayout);
	}
}
