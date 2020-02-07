package pcapp.models;


//used as a Model in Rest API
public class Kid {
	
	private Long id;
	
	private String firstName;
	
	public Kid() {
	}

	public Kid(String firstName) {
		this.firstName = firstName;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	
}
