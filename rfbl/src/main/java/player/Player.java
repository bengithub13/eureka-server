package player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

	private String firstname;
	private String lastname;
	private String fullname;
	private String position;
	private String pro_team;
	private String fantasy_team_name;
	private boolean free_agent;
	private String[] eligible_positions;
	
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPro_team() {
		return pro_team;
	}

	public void setPro_team(String pro_team) {
		this.pro_team = pro_team;
	}

	public String getFantasy_team_name() {
		return fantasy_team_name;
	}

	public void setFantasy_team_name(String fantasy_team_name) {
		this.fantasy_team_name = fantasy_team_name;
	}

	public boolean isFree_agent() {
		return free_agent;
	}

	public void setFree_agent(boolean free_agent) {
		this.free_agent = free_agent;
	}

	public String[] getEligible_positions() {
		return eligible_positions;
	}

	public void setEligible_positions(String[] eligible_positions) {
		this.eligible_positions = eligible_positions;
	}

	@Override
    public String toString() {
        return "Player{" +
                "firstname=" + firstname +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
