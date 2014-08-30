package br.ufscar.events.diadejava.model;

/**
 * Application user.
 * 
 * @author Pedro Brigatto
 */
public class User extends DomainObject {

	private static final long serialVersionUID = 6577655615142227959L;

	private String username;
	private String password;
	
	public User() {}
	
	public User(String name) {
		super(name);
	}

	public User(String name, String description) {
		super(name, description);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return (null != ((User)obj).getUsername()) && this.username != null &&
					((User)obj).getUsername().equals(this.username) &&
					null != ((User)obj).getPassword() && this.password != null &&
					((User)obj).getUsername().equals(this.password);
		}
		return false;
	}
}
