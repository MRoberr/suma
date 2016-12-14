package edu.msg.suma.backend.model;

public class User extends BaseEntity{

	
	private static final long serialVersionUID = -2207573242083743591L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	public User() {
		
		this(null, null, null, null, null, null);
	}
	
	public User(String uuid, 
			Long id, 
			String firstName, 
			String lastName,
			String email,
			String password) {
		
		super(uuid, id);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
