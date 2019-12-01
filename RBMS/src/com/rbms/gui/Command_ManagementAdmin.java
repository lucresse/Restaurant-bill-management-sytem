package com.rbms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.rbms.entities.Admin;
import com.rbms.entities.Bill;
import com.rbms.entities.Client;
import com.rbms.entities.Command;
import com.rbms.entities.Product;
import com.rbms.entities.Treasurer;
import com.rbms.entities.User;
import com.rbms.metier.TreasurerMetier;
import com.rbms.metier.TreasurerMetierImpl;
import com.rbms.metier.UserMetier;
import com.rbms.metier.UserMetierImpAdmin;
import com.rbms.metier.UserMetierImplTreasurer;

public class Command_ManagementAdmin extends JFrame {

	private JPanel contentPane;
	private static User user = new Admin();
	private JTextField textField_4;
	private UserMetier um = new UserMetierImpAdmin();
	private TreasurerMetier tm = new TreasurerMetierImpl();
	private Command commandSelected = new Command();
	private JTable table;
	private JComboBox<String> textField_1;
	private JComboBox<String> textField_2;
	private JComboBox<String> textField_3;
	private JTable table_1;
	private JLabel label;
	JComboBox<String> comboBox;
	JRadioButton rdbtnCustomerName;
	JRadioButton rdbtnCommandId;
	private static boolean rdSelected = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Command_ManagementAdmin frame = new Command_ManagementAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Command_ManagementAdmin.user = user;
	}
	/**
	 * Create the frame.
	 */
	public Command_ManagementAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JLabel lblCommandManagement = new JLabel("COMMAND MANAGEMENT");
		lblCommandManagement.setForeground(Color.ORANGE);
		lblCommandManagement.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String treasurer = textField_1.getSelectedItem().toString();
				String customer = textField_2.getSelectedItem().toString();
				int id_facture = 0;
				try {
					id_facture = Integer.parseInt(textField_3.getSelectedItem().toString());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				Command cm = new Command();
				//New client
				Client c = new Client();
				c.setName(customer);
				//adding client to command
				cm.setClient(c);
				//New Treasurer
				Treasurer t = new Treasurer();
				t.setName(treasurer);
				//adding treasurer to command
				cm.setTreasurer(t);
				//New Bill
				Bill b =new Bill();
				b.setId(id_facture);
				//adding bill to command
				cm.setBill(b);
				try {
					tm.addCommand(cm);
					table.setModel(um.showCommand());
					JOptionPane.showMessageDialog(null,"sucess", "add succeed", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
		btnAdd.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\03.png"));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String customer = textField_2.getSelectedItem().toString();
				int id_facture = Integer.parseInt(textField_3.getSelectedItem().toString())	;
				Command cm =new Command();
					
				Client c = new Client();
				c.setName(customer);
				
				Bill b = new Bill();
				b.setId(id_facture);
				
				cm.setId(commandSelected.getId());
				cm.setClient(c);
				cm.setBill(b);
				
				try {
					tm.updateCommand(cm);
					table.setModel(um.showCommand());
					table_1.setModel(um.showCommand());

					JOptionPane.showMessageDialog(null, "product updated", "success", JOptionPane.INFORMATION_MESSAGE);
				
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "success", JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		btnUpdate.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\18.png"));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = (Integer) table.getValueAt(table.getSelectedRow(),0);
					int boxDeleteUser = JOptionPane.showConfirmDialog(null, "are you sure to delete "+id+" ?", "Warning", JOptionPane.YES_NO_OPTION);
					if(boxDeleteUser==0){
						System.out.println(id);
						tm.deleteCommand(commandSelected.getId());
						table.setModel(um.showCommand());
						JOptionPane.showMessageDialog(null, "deleted succeed", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NullPointerException e) {
					// TODO  Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					
					
				}catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "no selected product", "error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnDelete.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\01.png"));
		
		JButton btnRetour = new JButton("Back");
		btnRetour.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\45.png"));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBorder(new LineBorder(SystemColor.inactiveCaption));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblDetaims = new JLabel("Details of command :");
		
		label = new JLabel("0");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.activeCaption));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product p = new Product();
				p.setName(comboBox.getSelectedItem().toString());
				System.out.println(p.getName());
				try {
					tm.addProductToCommand(p, commandSelected);
					table_1.setModel(tm.showProductByCommand(commandSelected));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd_1.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\03.png"));
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=table_1.getSelectedRow();
				String idProductSelected;
				try {
					idProductSelected = table_1.getValueAt(i, 0).toString();
					System.out.println(idProductSelected);
					int idCP = Integer.parseInt(idProductSelected);
					tm.removeProductToCommand(idCP);
					JOptionPane.showMessageDialog(null, "Product successfully removed", "Success", JOptionPane.INFORMATION_MESSAGE);
					table_1.setModel(tm.showProductByCommand(commandSelected));
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "No selected product", "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemove.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\01.png"));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAdd_1)
										.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRetour, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDetaims)
											.addGap(2)
											.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRemove)))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCommandManagement, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(372))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCommandManagement)
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDetaims)
										.addComponent(label))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnRemove)
										.addComponent(btnRetour))
									.addGap(10))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAdd_1)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(23)))
					.addContainerGap())
		);
		
		JLabel lblChoise = new JLabel("Choice");
		
		comboBox = new JComboBox<String>();
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6"}));
		try {
			List<Product> listProducts = um.getAllProduct();
			for(Product p:listProducts){
				comboBox.addItem(p.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox.setEnabled(false);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChoise)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChoise)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblSearch = new JLabel("Search by");
		
		rdbtnCustomerName = new JRadioButton("Customer name");
		rdbtnCustomerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdSelected =!rdSelected;
				rdbtnCustomerName.setSelected(rdSelected);
				rdbtnCommandId.setSelected(!rdSelected);
			}
		});
		rdbtnCustomerName.setSelected(rdSelected);
		
		rdbtnCommandId = new JRadioButton("Command Id");
		rdbtnCommandId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdSelected =!rdSelected;
				rdbtnCustomerName.setSelected(rdSelected);
				rdbtnCommandId.setSelected(!rdSelected);
			}
		});
		rdbtnCommandId.setSelected(!rdSelected);
		
	
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*int key =0;
				try {
					key = Integer.parseInt(textField_4.getText().toString());
				} catch (NumberFormatException e1) {
					
				}*/

				String key=textField_4.getText().toString();
					try {
						if(rdSelected){
							table.setModel(um.searchCommand("name_client", key));

							System.out.println(key);
						} 
					
						else{
						//int key2 = Integer.parseInt(textField_4.getText().toString());
						table.setModel(um.searchCommand("id", key));
						System.out.println(key);
						}
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				
				}
			
		});
		btnSearch.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearch)
						.addComponent(lblSearch))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(rdbtnCustomerName, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(rdbtnCommandId))
						.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
					.addGap(37))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearch)
						.addComponent(rdbtnCustomerName)
						.addComponent(rdbtnCommandId))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblTreasurer = new JLabel("User");
		
		textField_1 = new JComboBox<String>();
		try {
			List<Treasurer> lt = um.getAllTreasurer();
			for(Treasurer t:lt){
				textField_1.addItem(t.getName());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
				JLabel lblCustomer = new JLabel("Customer");
		
		JLabel lblFacturesId = new JLabel("ID Facture");
		
		textField_2 = new JComboBox<String>();
		try {
			List<Client> lc = um.getAllCustomer();
			for(Client c:lc){
				textField_2.addItem(c.getName());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		textField_3 = new JComboBox<String>();
		try {
			List<Bill> lb = um.getAllBill();
			for(Bill b:lb){
				textField_3.addItem(""+b.getId());
			}
		} catch (Exception e) { 
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTreasurer)
						.addComponent(lblCustomer)
						.addComponent(lblFacturesId))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
					.addGap(38))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTreasurer)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomer)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFacturesId)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String idd = table.getValueAt(table.getSelectedRow(),0).toString();
				commandSelected.setId(Integer.parseInt(idd));
				// New user
				//User ur = new User();
				//ur.setName(table.getValueAt(table.getSelectedRow(), 1).toString());
				//new treasurer
				Treasurer t = new Treasurer();
				t.setName(table.getValueAt(table.getSelectedRow(),1).toString());
				//new customer
				Client c = new Client();
				Bill b = new Bill();
				b.setId((Integer) table.getValueAt(table.getSelectedRow(), 3));
				c.setName(table.getValueAt(table.getSelectedRow(),4).toString());
				System.out.println("Command Selected "+idd+", "+t.getName()+", "+c.getName());
				label.setText(""+commandSelected.getId());
				comboBox.setEnabled(true);
				table_1.setModel(tm.showProductByCommand(commandSelected));
				for(int i=0; i<textField_1.getItemCount(); i++) {
					if(textField_1.getItemAt(i).equals(t.getName())){
						textField_1.setSelectedIndex(i);
						System.out.println("hi");
					}
				}
				for(int i=0; i<textField_2.getItemCount(); i++) {
					if(textField_2.getItemAt(i).equals(c.getName())){
						textField_2.setSelectedIndex(i);
						System.out.println("hi");
					}
				}
				System.out.println("id bill " +b.getId());
				for(int i=0; i<textField_3.getItemCount(); i++) {
					int s = Integer.parseInt(textField_3.getItemAt(i));
					if(s==b.getId()){
						textField_3.setSelectedIndex(i);
						System.out.println("hi");
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(um.showCommand());
		contentPane.setLayout(gl_contentPane);	
	}

}
