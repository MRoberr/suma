package edu.msg.suma.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.msg.suma.backend.model.User;
import edu.msg.suma.desktop.control.MainController;
import edu.msg.suma.desktop.control.listener.LanguageListener;
import util.LabelProvider;

public class InsertFrame extends JFrame implements LanguageListener{

	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JPasswordField password;
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	
	private JButton okButton;
	
	private JPanel mainPanel;
	private JPanel formPanel;
	
	public InsertFrame(MainController mainController, User user) {
		
		mainController.addListener(this);
		
		createLabels();
		createTextFields();

		
		okButton = new JButton("OK");
		
		createPanels();
		
		fillFormPanel();
		
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(okButton, BorderLayout.SOUTH);
		
		updateLabels();
		
		if(user != null) {
			
			fillFormWithUserData(user);
		}
		
		setupFrame();
	}
	
	private void createLabels() {
		
		firstNameLabel = new JLabel();
		lastNameLabel = new JLabel();
		emailLabel = new JLabel();
		passwordLabel = new JLabel();
	}
	
	private void createTextFields() {
		
		firstName = new JTextField();
		lastName = new JTextField();
		email = new JTextField();
		password = new JPasswordField();
	}
	
	public void createPanels() {
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2));
	}
	
	public void fillFormPanel() {
		
		formPanel.add(firstNameLabel);
		formPanel.add(firstName);
		formPanel.add(lastNameLabel);
		formPanel.add(lastName);
		formPanel.add(emailLabel);
		formPanel.add(email);
		formPanel.add(passwordLabel);
		formPanel.add(password); 
	}
	
	public void setupFrame() {
		
		setContentPane(mainPanel);
		setVisible(true);
		setResizable(false);
		setBounds(50, 50, 300, 300);
		
	}
	
	public void fillFormWithUserData(User user) {
		
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		email.setText(user.getEmail());
		
	}
	
	public void updateLabels() {
		
		firstNameLabel.setText(LabelProvider.INSTANCE.getProperty("firstName"));
		lastNameLabel.setText(LabelProvider.INSTANCE.getProperty("lastName"));
		emailLabel.setText(LabelProvider.INSTANCE.getProperty("email"));
		passwordLabel.setText(LabelProvider.INSTANCE.getProperty("password"));
	}
	
	public JButton getOkButton() {
		
		return okButton;
	}
	
	public String getFirstName() {
		
		return firstName.getText();
	}
	
	public String getLastName() {
		
		return lastName.getText();
	}
	
	public String getEmail() {
		
		return email.getText();
	}
	
	public String getPassword() {
		
		return new String(password.getPassword());
	}

	@Override
	public void onLanguageChang() {

		updateLabels();
	}
}