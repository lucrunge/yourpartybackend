package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	String id;
	List<Song> songList;
	List<User> userList;
	String hostName;
	String token;
	List<Double> energyVotes;
	List<Double> danceVotes;

	public Group(String id, String hostName, String token) {
		this.id = id;
		this.hostName = hostName;
		this.token = token;
		this.songList = new ArrayList<Song>();
		this.userList = new ArrayList<User>();
		this.energyVotes = new ArrayList<Double>();
		this.danceVotes = new ArrayList<Double>();
	}

	public List<Double> getEnergyVotes() {
		return energyVotes;
	}

	public void setEnergyVotes(List<Double> energyVotes) {
		this.energyVotes = energyVotes;
	}

	public List<Double> getDanceVotes() {
		return danceVotes;
	}

	public void setDanceVotes(List<Double> danceVotes) {
		this.danceVotes = danceVotes;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public String getHostName() {
		return hostName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
