package com.rbms.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.rbms.entities.Treasurer;
import com.rbms.entities.User;

public class Menu extends JFrame {

	private JPanel contentPane;
	private static User user = new Treasurer();
	

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Menu.user = user;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
					user.setName("ornella");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setResizable(false);
		setForeground(Color.BLACK);
		setFont(new Font("Dialog", Font.ITALIC, 15));
		setTitle("Menu principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 481);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("file");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\new.png"));
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\printer.png"));
		mnFile.add(mntmPrint);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\01.png"));
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cancel");
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\cancel.png"));
		mnEdit.add(mntmNewMenuItem_1);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\page_copy.png"));
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\page_paste.png"));
		mnEdit.add(mntmPaste);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\cut.png"));
		mnEdit.add(mntmCut);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\delete.png"));
		mnEdit.add(mntmDelete);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JMenuItem mntmCustomer = new JMenuItem("Customer");
		mntmCustomer.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		mnSearch.add(mntmCustomer);
		
		JMenuItem mntmProduct = new JMenuItem("Product");
		mntmProduct.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		mnSearch.add(mntmProduct);
		
		JMenuItem mntmCommand = new JMenuItem("Command");
		mntmCommand.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		mnSearch.add(mntmCommand);
		
		JMenuItem mntmBill = new JMenuItem("Bill");
		mntmBill.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		mnSearch.add(mntmBill);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmWelcome = new JMenuItem("Welcome");
		mntmWelcome.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\control_wheel.png"));
		mnHelp.add(mntmWelcome);
		
		JMenuItem mntmAboutRbms = new JMenuItem("About RBMS");
		mnHelp.add(mntmAboutRbms);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Product Management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product_Management.setUser(user);
				Product_Management pm=new  Product_Management();
				pm.setVisible(true);
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Customer Management");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customer_Management.setUser(user);
				
				Customer_Management cm=new  Customer_Management();
				cm.setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Command Management");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Command_Management.setUser(user);
				Command_Management com=new  Command_Management();
				com.setVisible(true);
			}
		});
		
		JButton btnNewButton_3 = new JButton("Bill Management");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Bill_Management.setUser(user);
				Bill_Management bm=new  Bill_Management();
				bm.setVisible(true);
			}
		});
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\500_F_36364022_7hcHmkwHmWOsC17rM5Sbi4OKS8iAHrbF.jpg"));
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\DSC00229.jpg"));
		
		JLabel lblCommand = new JLabel("Command");
		lblCommand.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\3114585_thumbnail.jpg"));
		
		JLabel lblBill = new JLabel("Bill");
		lblBill.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\facture.jpg"));
		
		JLabel lblUser = new JLabel("USER: null");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1)
								.addComponent(lblCustomer))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(lblProduct))
							.addPreferredGap(ComponentPlacement.RELATED, 354, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2)
								.addComponent(lblCommand))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBill)
								.addComponent(btnNewButton_3)))
						.addComponent(lblUser))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUser)
					.addPreferredGap(ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomer)
						.addComponent(lblProduct)
						.addComponent(lblCommand)
						.addComponent(lblBill))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2))
					.addGap(41))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
