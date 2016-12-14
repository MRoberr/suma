package edu.msg.suma.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import edu.msg.suma.desktop.control.MainController;
import edu.msg.suma.desktop.control.listener.LanguageListener;
import util.LabelProvider;

public class MainFrame extends JFrame implements LanguageListener{
	
	private JPanel mainPanel;
	private JPanel eastPanel;
	
//	private JButton loadButton;
	private JButton insertButton;
	private JButton updateButton;
	private JButton deleteButton;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu language;
	private JMenuItem itemHU;
	private JMenuItem itemEN;
	
	private JScrollPane scrollPane;
	private JTable table;
	
	public MainFrame(TableModel tableModel, MainController mainController) {
		
		mainPanel = new JPanel();
		eastPanel = new JPanel(new GridLayout(3, 1));
		
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane(table);
		mainController.addListener(this);
		
		createButtons();
		
		setupEastPanel();
		
		setupMainPanel();
		
		createMenuBar();
		
		configureContentPane();
		
	}
	
	private void updateTable() {

		TableColumnModel column = table.getTableHeader().getColumnModel();
		column.getColumn(0).setHeaderValue(LabelProvider.INSTANCE.getProperty("firstName"));
		column.getColumn(1).setHeaderValue(LabelProvider.INSTANCE.getProperty("lastName"));
		column.getColumn(2).setHeaderValue(LabelProvider.INSTANCE.getProperty("email"));
		
		table.getTableHeader().repaint();
	}
	
	private void configureContentPane() {
		
		setJMenuBar(menuBar);
		setContentPane(mainPanel);
		setVisible(true);
		setResizable(false);
		setBounds(50, 50, 650, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createButtons() {
		
//		loadButton = new JButton();
		insertButton = new JButton();
		updateButton = new JButton();
		deleteButton = new JButton();
		
		updateButtonLabels();
	}
	
	private void updateButtonLabels() {
		
//		loadButton.setText(LabelProvider.INSTANCE.getProperty("load"));
		insertButton.setText(LabelProvider.INSTANCE.getProperty("insert"));
		updateButton.setText(LabelProvider.INSTANCE.getProperty("update"));
		deleteButton.setText(LabelProvider.INSTANCE.getProperty("delete"));
	}
	
	private void setupEastPanel() {

//		eastPanel.add(loadButton);
		eastPanel.add(insertButton);
		eastPanel.add(updateButton);
		eastPanel.add(deleteButton);
	}
	
	private void setupMainPanel() {
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(eastPanel, BorderLayout.EAST);
	}
	
	private void createMenuBar() {

		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		language = new JMenu("Language");
		itemHU = new JMenuItem("HU");
		itemEN = new JMenuItem("EN");
		language.add(itemHU);
		language.add(itemEN);
		menu.add(language);
		menuBar.add(menu);
		
		updateMenuText();
	}
	
	private void updateMenuText() {
		
		menu.setText(LabelProvider.INSTANCE.getProperty("menu"));
		language.setText(LabelProvider.INSTANCE.getProperty("menuLanguage"));
	}

	public JMenu getLanguageMenu() {
		
		return language;
	}
	
//	public JButton getLoadButton() {
//		
//		return loadButton;
//	}
	
	public JButton getInsertButton() {
		
		return insertButton;
	}
	
	public JButton getUpdateButton() {
		
		return updateButton;
	}
	
	public JButton getDeleteButton() {
		
		return deleteButton;
	}
	
	public JTable getTable() {
		
		return table;
	}
	
	@Override
	public void onLanguageChang() {
		
		updateButtonLabels();
		updateMenuText();
		updateTable();
	}
}
