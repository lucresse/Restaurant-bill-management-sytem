package com.rbms.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rbms.entities.Admin;
import com.rbms.entities.User;

public class MenuAdmin extends JFrame {

	private JPanel contentPane;
	private static User user = new Admin();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin frame = new MenuAdmin();
					frame.setVisible(true);
					//user.setName("ornella");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuAdmin() {
		setTitle("MenuAdmin");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 500);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\new.png"));
		mnFile.add(mntmNew);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\printer.png"));
		mnFile.add(mntmPrint);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\01.png"));
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCancel = new JMenuItem("Cancel");
		mntmCancel.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\cancel.png"));
		mnEdit.add(mntmCancel);
		
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
		mntmDelete.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\01.png"));
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
		
		JButton btnCustomerManagement = new JButton("Customer Management");
		btnCustomerManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customer_Management.setUser(user);
				Customer_Management cm = new Customer_Management();
				cm.setVisible(true);
			}
		});
		
		JButton btnProductManagement = new JButton("Product Management");
		btnProductManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product_Management.setUser(user);
				Product_Management pm = new Product_Management();
				pm.setVisible(true);
			}
		});
		
		JButton btnCommandManagement = new JButton("Command Management");
		btnCommandManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Command_ManagementAdmin.setUser(user);
				Command_ManagementAdmin com = new Command_ManagementAdmin();
				com.setVisible(true);
			}
		});
		
		JButton btnBillManagement = new JButton("Bill Management");
		btnBillManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Bill_ManagementAdmin.setUser(user);
				Bill_ManagementAdmin bm = new Bill_ManagementAdmin();
				bm.setVisible(true);
			}
		});
		
		JButton btnTreasurerManagement = new JButton("Treasurer Management");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(675, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBillManagement)
						.addComponent(btnTreasurerManagement))
					.addGap(74))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(204)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCustomerManagement)
						.addComponent(btnProductManagement)
						.addComponent(btnCommandManagement))
					.addContainerGap(545, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addComponent(btnBillManagement))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addComponent(btnCustomerManagement)))
					.addGap(72)
					.addComponent(btnProductManagement)
					.addGap(32)
					.addComponent(btnTreasurerManagement)
					.addGap(63)
					.addComponent(btnCommandManagement)
					.addContainerGap(105, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
