package edu.msg.suma.desktop.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.msg.suma.backend.model.User;
import edu.msg.suma.backend.service.ServiceException;
import edu.msg.suma.backend.service.ServiceFactory;
import edu.msg.suma.backend.service.UserService;
import edu.msg.suma.desktop.view.InsertFrame;

public class InsertController {

	InsertFrame frame;

	public InsertController(MainController mainController, User user) {

		frame = new InsertFrame(mainController, user);
		mainController.addListener(frame);
		JButton okButton = frame.getOkButton();
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (user == null) {
					
					insert();	
				} else {
					
					update(user);
				}
				try {

					Method m = MainController.class.getDeclaredMethod("loadData");
					m.setAccessible(true);
					m.invoke(mainController);
					
					
				} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
					
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Failed to insert user.");
				}
				frame.dispose();
				mainController.removeListener(frame);
			}
		});
	}

	private void insert() {

		if (!frame.getFirstName().isEmpty() && !frame.getLastName().isEmpty() && !frame.getEmail().isEmpty()
				&& !frame.getPassword().isEmpty()) {

			User user = new User();
			user.setFirstName(frame.getFirstName());
			user.setLastName(frame.getLastName());
			user.setEmail(frame.getEmail());
			user.setPassword(frame.getPassword());

			try {
				UserService userService = ServiceFactory.getUserService();
				userService.insertUser(user);
				
			} catch (ServiceException e) {

				JOptionPane.showMessageDialog(null, "Can't insert user");
			}
		}
	}

	private void update(User user) {

		if (!frame.getFirstName().isEmpty() && !frame.getLastName().isEmpty() && !frame.getEmail().isEmpty()
				&& !frame.getPassword().isEmpty()) {
System.out.println("adsa");
			user.setFirstName(frame.getFirstName());
			user.setLastName(frame.getLastName());
			user.setEmail(frame.getEmail());
			user.setPassword(frame.getPassword());
			
			try {
				UserService userService = ServiceFactory.getUserService();
				userService.updateUser(user);
			} catch (ServiceException e) {

				JOptionPane.showMessageDialog(null, "Can't update user");
			}
		}
	}
}
