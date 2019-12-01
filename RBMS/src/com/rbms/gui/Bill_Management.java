package com.rbms.gui;

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

import com.rbms.entities.Bill;
import com.rbms.entities.Client;
import com.rbms.entities.Treasurer;
import com.rbms.entities.User;
import com.rbms.metier.TreasurerMetier;
import com.rbms.metier.TreasurerMetierImpl;
import com.rbms.metier.UserMetier;
import com.rbms.metier.UserMetierImplTreasurer;

public class Bill_Management extends JFrame {

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Bill_Management.user = user;
	}

	private JPanel contentPane;
	private JComboBox<String> textField_1;
	private JComboBox<String> textField_2;
	private JTextField textField_4;
	private UserMetier um = new UserMetierImplTreasurer();
	private TreasurerMetier tm = new TreasurerMetierImpl();
	private static User user = new Treasurer();
	private Bill billSelected = new Bill();
	private static int id;
	private JTable table;
	JRadioButton rdbtnId;
	JRadioButton rdbtnCustomersName;
	private static boolean rdSelected = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user.setName("ornella");
					Bill_Management frame = new Bill_Management();
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
	public Bill_Management() {
		setResizable(false);
		setTitle("Bill Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 382);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBillManagement = new JLabel("BILL MANAGEMENT");
		lblBillManagement.setForeground(Color.MAGENTA);
		lblBillManagement.setFont(new Font("Stencil", Font.PLAIN, 18));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name_user = textField_1.getSelectedItem().toString();
				String name_client = textField_2.getSelectedItem().toString();
				
				Bill b = new Bill();
				
				Treasurer u = new Treasurer();
				u.setName(name_user);
				
				Client c = new Client();
				c.setName(name_client);
				
				b.setClient(c);
				b.setTreasurer(u);
				System.out.println(name_user);
				System.out.println(name_client);
				try {
					tm.addBill(b);
					table.setModel(um.showFacture(user.getName()));
					JOptionPane.showMessageDialog(null, "one bill added", "success", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\03.png"));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idd = table.getValueAt(table.getSelectedRow(),0).toString();
					int boxDeleteUser = JOptionPane.showConfirmDialog(null, "are you sure to delete bill "+idd+" ?", "Warning", JOptionPane.YES_NO_OPTION);
					if(boxDeleteUser==0){
						System.out.println(id);
						tm.deleteBill(billSelected.getId());
						table.setModel(um.showFacture(user.getName()));
						JOptionPane.showMessageDialog(null, "deleted succeed", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
	
					JOptionPane.showMessageDialog(null, "no selected bill", "error", JOptionPane.ERROR_MESSAGE);
					
			}catch (Exception e){
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnPri = new JButton("Print");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(241)
							.addComponent(lblBillManagement))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnAdd)
											.addGap(97)
											.addComponent(btnDelete))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(72)
									.addComponent(btnRetour, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
							.addGap(110)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
									.addGap(83))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(546, Short.MAX_VALUE)
					.addComponent(btnPri)
					.addGap(71))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblBillManagement)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(70)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnDelete))
							.addGap(27)
							.addComponent(btnRetour)
							.addGap(17))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPri)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel lblSearchBy = new JLabel("Search by");
		
		rdbtnId = new JRadioButton("Id");
		rdbtnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdSelected =!rdSelected;
				rdbtnId.setSelected(rdSelected);
				rdbtnCustomersName.setSelected(!rdSelected);
			}			
		});
		rdbtnId.setSelected(rdSelected);

		rdbtnCustomersName = new JRadioButton("Customer's name");
		rdbtnCustomersName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdSelected =!rdSelected;
				rdbtnId.setSelected(rdSelected);
				rdbtnCustomersName.setSelected(!rdSelected);
			}
		});
		rdbtnCustomersName.setSelected(!rdSelected);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String key=textField_4.getText().toString();
				try {
					if(rdSelected){
						table.setModel(um.searchBill("id", key,user.getName()));
						System.out.println(key);
					} 
				
					else{
					//int key2 = Integer.parseInt(textField_4.getText().toString());
					table.setModel(um.searchBill("name_client", key,user.getName()));
					System.out.println(key);
					}
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSearch.setIcon(new ImageIcon("C:\\Developpement Web App Engine\\WorkSpace\\RBMS\\images\\49.png"));
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblSearchBy)
							.addGap(18)
							.addComponent(rdbtnId)
							.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
							.addComponent(rdbtnCustomersName))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnSearch)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnCustomersName)
						.addComponent(rdbtnId)
						.addComponent(lblSearchBy))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel = new JLabel("Treasurer");
		
		JLabel lblCustomersName = new JLabel("Customer");
		
		textField_1 = new JComboBox<String>();
		textField_1.addItem(user.getName());
		
		textField_2 = new JComboBox<String>();
	try {
			List<Client> lc = um.getAllCustomer();
			for(Client c:lc){
				textField_2.addItem(c.getName());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblCustomersName))
					.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCustomersName)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41))
		);
		panel_1.setLayout(gl_panel_1);
		scrollPane.setViewportView(table);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String idd = table.getValueAt(table.getSelectedRow(),0).toString();
				billSelected.setId(Integer.parseInt(idd));
				//new treasurer
				Treasurer t = new Treasurer();
				t.setName(table.getValueAt(table.getSelectedRow(),2).toString());
				//new customer
				Client c = new Client();
				c.setName(table.getValueAt(table.getSelectedRow(),3).toString());
				System.out.println("Bill Selected "+idd+", "+t.getName()+", "+c.getName());
				//table.setModel(tm.show(billSelected));
				for(int i=0; i<textField_2.getItemCount(); i++) {
					if(textField_2.getItemAt(i).equals(c.getName())){
						textField_2.setSelectedIndex(i);
						System.out.println("hi");
					}
				}
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(um.showFacture(user.getName()));
		contentPane.setLayout(gl_contentPane);

	}
}
