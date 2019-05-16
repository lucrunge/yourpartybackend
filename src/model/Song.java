package model;

public class Song {
	String name;
	String artistName;
	String imageUrl;
	String spotifyUri;
	String spotifyID;
	
	double danceability;
	double energy;

	public Song(String name, String artistName, String imageUrl, String spotifyUri, String spotifyID, double danceability, double energy) {
		this.name = name;
		this.artistName = artistName;
		this.imageUrl = imageUrl;
		this.spotifyUri = spotifyUri;
		this.spotifyID = spotifyID;
		
		this.danceability = danceability;
		this.energy = energy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSpotifyUri() {
		return spotifyUri;
	}

	public void setSpotifyUri(String spotifyUri) {
		this.spotifyUri = spotifyUri;
	}

	public String getSpotifyID() {
		return spotifyID;
	}

	public void setSpotifyID(String spotifyID) {
		this.spotifyID = spotifyID;
	}

	public double getDanceability() {
		return danceability;
	}

	public double getEnergy() {
		return energy;
	}
	
	

}
