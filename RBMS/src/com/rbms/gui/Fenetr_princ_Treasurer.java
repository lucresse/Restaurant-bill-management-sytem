package com.rbms.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.BoxLayout;

public class Fenetr_princ_Treasurer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetr_princ_Treasurer frame = new Fenetr_princ_Treasurer();
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
	public Fenetr_princ_Treasurer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fenetr_princ_Treasurer.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mnFile.add(mntmPrint);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmAnnuler = new JMenuItem("Cancel");
		mnEdit.add(mntmAnnuler);
		
		JMenuItem mntmCopier = new JMenuItem("Copy");
		mnEdit.add(mntmCopier);
		
		JMenuItem mntmColler = new JMenuItem("Paste");
		mnEdit.add(mntmColler);
		
		JMenuItem mntmCouper = new JMenuItem("Cut");
		mnEdit.add(mntmCouper);
		
		JMenuItem mntmSupprimer = new JMenuItem("Delete");
		mnEdit.add(mntmSupprimer);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JMenuItem mntmCommand = new JMenuItem("Command");
		mnSearch.add(mntmCommand);
		
		JMenuItem mntmFacture = new JMenuItem("Bill");
		mnSearch.add(mntmFacture);
		
		JMenuItem mntmClient = new JMenuItem("Custumer");
		mnSearch.add(mntmClient);
		
		JMenuItem mntmProduct = new JMenuItem("Product");
		mnSearch.add(mntmProduct);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmWelcome = new JMenuItem("Welcome");
		mnHelp.add(mntmWelcome);
		
		JMenuItem mntmAboutRbms = new JMenuItem("About RBMS");
		mnHelp.add(mntmAboutRbms);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
	}
}
