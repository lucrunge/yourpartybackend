package model;

public class Vote {

	double danceVote;
	double energyVote;

	public Vote(double danceVote, double energyVote) {
		this.danceVote = danceVote;
		this.energyVote = energyVote;
	}

	public double getDanceVote() {
		return danceVote;
	}

	public void setDanceVote(double danceVote) {
		this.danceVote = danceVote;
	}

	public double getEnergyVote() {
		return energyVote;
	}

	public void setEnergyVote(double energyVote) {
		this.energyVote = energyVote;
	}

}
