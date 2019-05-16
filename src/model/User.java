package model;

public class User {
	String id;
	double danceability;
	double energy;
	double instrumentalness;
	int mode;

	public User(String id, double danceability, double energy, double instrumentalness, int mode) {
		this.id = id;
		this.danceability = danceability;
		this.energy = energy;
		this.instrumentalness = instrumentalness;
		this.mode = mode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDanceability() {
		return danceability;
	}

	public void setDanceability(double danceability) {
		this.danceability = danceability;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getInstrumentalness() {
		return instrumentalness;
	}

	public void setInstrumentalness(double instrumentalness) {
		this.instrumentalness = instrumentalness;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
