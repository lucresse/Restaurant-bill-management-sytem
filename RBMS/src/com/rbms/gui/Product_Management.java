package com.rbms.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
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

import com.rbms.entities.Product;
import com.rbms.entities.Treasurer;
import com.rbms.entities.User;
import com.rbms.metier.TreasurerMetier;
import com.rbms.metier.TreasurerMetierImpl;
import com.rbms.metier.UserMetier;
import com.rbms.metier.UserMetierImplTreasurer;

public class Product_Management extends JFrame {

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Product_Management.user = user;
	}

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	

	private static Product productselected = new Product();
	private static boolean rdselected = true;
	
	private UserMetier um = new UserMetierImplTreasurer();
	private static User user= new Treasurer();
	private TreasurerMetier tm = new TreasurerMetierImpl();
	JRadioButton rdbtnName;
	JRadioButton rdbtnPrice;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user.setName("ornella");
					Product_Management frame = new Product_Management();
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
	public Product_Management() {
		setResizable(false);
		setTitle("Product Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 346);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblProductManagement = new JLabel("PRODUCT MANAGEMENT");
		lblProductManagement.setForeground(Color.GREEN);
		lblProductManagement.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\03.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String name = textField.getText().toString();
					String description = textField_1.getText().toString();
					int price =Integer.parseInt(textField_2.getText().toString());
					String image = textField_3.getText().toString();
					
					Product p =new Product();
					p.setName(name);
					p.setDescription(description);
					p.setPrice(price);
					p.setImage(image);
					try {
						tm.addProduct(p);
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						table_1.setModel(um.showProduct());
						JOptionPane.showMessageDialog(null, "One product added", "success", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Error ","Error pointer",JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\18.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textField.getText();
				String description = textField_1.getText();
				int price =Integer.parseInt(textField_2.getText().toString());
				String image = textField_3.getText();
				Product pd =new Product();
				pd.setName(name);
				pd.setDescription(description);
				pd.setPrice(price);
				pd.setImage(image);
				tm.updateProduct(pd,productselected.getName());
				table_1.setModel(um.showProduct());
				JOptionPane.showMessageDialog(null, "product updated", "success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int boxDeleteUser = JOptionPane.showConfirmDialog(null, "are you sure to delete "+productselected.getName()+" ?", "Warning", JOptionPane.YES_NO_OPTION);
					if(boxDeleteUser==0){
						System.out.println(productselected.getName());
						try {
							tm.deleteProduct(productselected);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
						}
						table_1.setModel(um.showProduct());
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						JOptionPane.showMessageDialog(null, "deleted succeed", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NullPointerException e) {
					// TODO  Auto-generated catch block
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					
					
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
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBorder(new LineBorder(SystemColor.textHighlight));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBorder(new LineBorder(SystemColor.textHighlight));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAdd)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnUpdate)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDelete))
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRetour))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProductManagement)
							.addGap(200))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProductManagement)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(105)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnAdd)
										.addComponent(btnDelete)
										.addComponent(btnUpdate))
									.addGap(18)
									.addComponent(btnRetour))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblDescription = new JLabel("Description");
		
		JLabel lblPrice = new JLabel("Price");
		
		JLabel lblImage = new JLabel("Image");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblDescription)
						.addComponent(lblImage)
						.addComponent(lblPrice))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField)
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescription))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrice))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblImage))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		rdbtnPrice = new JRadioButton("Price");
		rdbtnPrice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				rdselected =!rdselected;
				rdbtnPrice.setSelected(rdselected);
				rdbtnName.setSelected(!rdselected);
			}
		});
		rdbtnName = new JRadioButton("Name");
		rdbtnName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				rdselected =!rdselected;
				rdbtnPrice.setSelected(rdselected);
				rdbtnName.setSelected(!rdselected);
			}
		});
		JLabel lblSearch = new JLabel("Search by");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String key = textField_4.getText().toString();

				//List<String> columns = new ArrayList<String>();
				//columns.add("name");
				//columns.add("description");
				//columns.add("price");
				//columns.add("image");
				try {

				if(rdselected){
						table_1.setModel(um.searchProduct("price",key));
								
							}else{
						
									table_1.setModel(um.searchProduct("name",key));
							}
			}
						catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
			}
			
		});
		btnSearch.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSearch)
							.addGap(6)
							.addComponent(rdbtnName)
							.addComponent(rdbtnPrice))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnSearch)
							.addGap(18)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblSearch))
						.addComponent(rdbtnName)
						.addComponent(rdbtnPrice))
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearch)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i=table_1.getSelectedRow();
				System.out.println(i);
			
				try {
					if(table_1.getValueAt(i,0)!=null ){
						productselected.setName(table_1.getValueAt(i, 0).toString());
					}
					if(table_1.getValueAt(i,1)!=null ){
						productselected.setDescription(""+table_1.getValueAt(i, 1).toString());
					}
					if(table_1.getValueAt(i,2)!=null ){
						productselected.setPrice(Integer.parseInt(table_1.getValueAt(i, 2).toString()));
					}
					if(table_1.getValueAt(i,3)!=null ){
						productselected.setImage(table_1.getValueAt(i, 3).toString());
					}
					// VISIBILITE LORS DE LA SELECTION D'UNE LIGNE
					textField.setText(productselected.getName());
					textField_1.setText(productselected.getDescription());
					textField_2.setText(""+(int)productselected.getPrice());
					textField_3.setText(productselected.getImage());
				} catch (NumberFormatException e) {

				}
				catch (NullPointerException e) {

				}
				
				System.out.println(productselected.getName()+", "+productselected.getPrice()+", "+productselected.getDescription()+", "+productselected.getImage());
			}
		});
		scrollPane.setViewportView(table_1);
		table_1.setModel(um.showProduct());
		contentPane.setLayout(gl_contentPane);	
		}
}
