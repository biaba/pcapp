package pcapp.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pcapp.validators.FieldMatch;
import pcapp.validators.ValidEmail;


@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields are not matching")
})
public class CrmUser {
	
	@NotNull(message = "required")
	@Size(min = 1, message = "required")
	private String username;

	@NotNull(message = "required")
	@Size(min = 1, message = "required")
	private String password;
	
	@NotNull(message = "required")
	@Size(min = 1, message = "required")
	private String matchingPassword;

	@ValidEmail
	@NotNull(message = "required")
	@Size(min = 1, message = "required")
	private String email;

	public CrmUser() {

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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
