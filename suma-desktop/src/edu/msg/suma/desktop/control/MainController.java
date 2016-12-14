package edu.msg.suma.desktop.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import edu.msg.suma.backend.model.User;
import edu.msg.suma.backend.service.ServiceException;
import edu.msg.suma.backend.service.ServiceFactory;
import edu.msg.suma.backend.service.UserService;
import edu.msg.suma.desktop.control.listener.LanguageListener;
import edu.msg.suma.desktop.view.MainFrame;
import util.LabelProvider;

public class MainController {

	private DefaultTableModel tableModel;
	private UserService userService;
	private MainFrame mainFrame;
	private List<LanguageListener> languageListener;
	private List<User> users;
	

	public MainController() {

		setupTable();

		languageListener = new ArrayList<>();

		mainFrame = new MainFrame(tableModel, this);
		

		buttonSetup(mainFrame);

		try {

			userService = ServiceFactory.getUserService();
			loadData();
		} catch (ServiceException e) {

			JOptionPane.showMessageDialog(null, "Failed to initialize");
		}

	}

	private void setupTable() {

		tableModel = new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				
				return false;
			}
		};
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Email");
		
	}

	private void loadData() {

		try {
			users = userService.getUsers();

			tableModel.setRowCount(0);
			for (User u : users) {

				tableModel.addRow(new Object[] { u.getFirstName(), u.getLastName(), u.getEmail() });
			}
		} catch (ServiceException | NullPointerException e) {

			JOptionPane.showMessageDialog(null, "Failed to get users");
		}

	}
	

	
	
	
	public void addListener(LanguageListener listener) {

		languageListener.add(listener);
	}

	public void removeListener(LanguageListener listener) {

		languageListener.remove(listener);
	}

	
	
	
	
	
	private void buttonSetup(MainFrame mainFrame) {

//		mainFrame.getLoadButton().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				loadData();
//			}
//
//		});

		mainFrame.getInsertButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mainFrame.getTable().clearSelection();
				new InsertController(MainController.this, null);
			}
		});
		
		mainFrame.getUpdateButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = mainFrame.getTable().getSelectedRow();
				if (row!= -1){
				new InsertController(MainController.this, users.get(row));
				}else {
					JOptionPane.showMessageDialog(null, "No rows selected");
				}
				
			}
		});
		
		mainFrame.getDeleteButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteUser();
				
				
			}
		});

		mainFrame.getLanguageMenu().getItem(0).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LabelProvider.INSTANCE.setLocale(new Locale("HU", "hu"));
				notifyListeners();
			}
		});

		mainFrame.getLanguageMenu().getItem(1).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LabelProvider.INSTANCE.setLocale(new Locale("US", "en"));
				notifyListeners();
			}
		});
		
	
	}
	
	
	
	
	

	private void notifyListeners() {

		for (LanguageListener l : languageListener) {
			
			l.onLanguageChang();
		}
	}
	
	private void deleteUser() {
		
	 int row=mainFrame.getTable().getSelectedRow();
	 
	 if (row == -1) {
		 
		 JOptionPane.showMessageDialog(null, "No user selected");
		 return;
	 }
	 try {
		 
		 UserService userService=ServiceFactory.getUserService();
		 userService.deleteUser(users.get(row));
		 loadData();
	 } catch(ServiceException e) {
		 
		 JOptionPane.showMessageDialog(null, "Can't delete user");
	 }
	}

}
