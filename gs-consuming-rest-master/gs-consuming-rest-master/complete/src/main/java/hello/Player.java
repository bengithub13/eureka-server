package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

	private String firstname;
	private String lastname;
	
	public Player(){
	}
	
	public Player(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
    public String toString() {
        return "Player{" +
                "firstname=" + firstname +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
