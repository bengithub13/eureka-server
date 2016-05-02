package player.domain;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import player.exceptions.MLBTeamNotFoundException;
import player.exceptions.PositionNotFoundException;
import player.logger.PlayerLogger;
import player.utils.ResourceRankPair;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

	@Resource(name="elias_id")
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String fullname;
	private String position;
	private String pro_team;
	private String fantasy_team_name;
	private boolean free_agent;
	private String[] eligible_positions;
	private List<ResourceRankPair> mentions; 
	transient private MLBTeam mlbTeam;
	transient private Position pos;
	
	public Player(){
	}
	
	public Player(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		try{
			pos = Position.getPosition(position);
		}catch(PositionNotFoundException e){
			PlayerLogger.log(e.getMessage());
		}
	}

	public String getPro_team() {
		return pro_team;
	}

	public void setPro_team(String pro_team) {
		this.pro_team = pro_team;
		try{
			this.mlbTeam = MLBTeam.getMLBTeam(pro_team);
		}catch(MLBTeamNotFoundException e){
			PlayerLogger.log(e.getMessage());
		}
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

	public MLBTeam getMlbTeam() {
		return mlbTeam;
	}

	public void setMlbTeam(MLBTeam mlbTeam) {
		this.mlbTeam = mlbTeam;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public List<ResourceRankPair> getMentions() {
		return mentions;
	}

	public void setMentions(List<ResourceRankPair> mentions) {
		this.mentions = mentions;
	}

	@Override
	public String toString() {
		return "Player [fullname=" + fullname + ", fantasy_team_name=" + fantasy_team_name 
				+ ", mlb_team=" + pro_team
				+ ", free_agent="
				+ free_agent + ", eligible_positions=" + Arrays.toString(eligible_positions) + "]";
	}

	
}
