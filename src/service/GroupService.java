package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.Group;
import model.Song;
import model.Vote;

public class GroupService {
	private static final String NUMERIC_STRING = "0123456789";
	List<Group> groupList;

	public GroupService() {
		groupList = new ArrayList<Group>();
		initVoteReset();
		initPlaylistOrdering();
	}

	public void addVoteToGroup(String id, Vote v) {
		for (Group g : groupList) {
			if (g.getId().equals(id)) {
				g.getEnergyVotes().add(v.getEnergyVote());
				g.getDanceVotes().add(v.getDanceVote());
				System.out.println(g.getEnergyVotes());
			}
		}
	}

	public void addSongToGroup(String id, Song s) {
		for (Group g : groupList) {
			if (g.getId().equals(id)) {
				System.out.println(s.getSpotifyID());
				g.getSongList().add(s);
			}
		}
	}

	public void removeFirstSongFromPlaylist(String id) {
		for (Group g : groupList) {
			if (g.getId().equals(id)) {
				g.getSongList().remove(0);
			}
		}
	}

	public Group getGroup(String id) {
		for (Group g : groupList) {
			if (g.getId().equals(id)) {
				System.out.println(g.getId());
				return g;
			}
		}
		return null;
	}

	public void addGroup(Group g) {
		groupList.add(g);
		g.setId(generateGroupId());
	}

	public List<Song> getGroupSongList(String id) {
		for (Group g : groupList) {
			if (g.getId().equals(id)) {
				return g.getSongList();
			}
		}
		return null;
	}

	private String generateGroupId() {
		int idSize = 6;
		StringBuilder builder = new StringBuilder();
		while (idSize-- != 0) {
			int character = (int) (Math.random() * NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(character));
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

	private Song getNearestDanceValue(double value, List<Song> songs) {
		double userNum = value; // This is coming from user input
		double smallestDiff = Math.abs(userNum - songs.get(0).getDanceability());
		int closest = 0; // index of the current closest number

		for (int i = 1; i < songs.size(); i++) {
			double currentDiff = Math.abs(userNum - songs.get(i).getDanceability());
			if (currentDiff < smallestDiff) {
				smallestDiff = currentDiff;
				closest = i;
			}
		}
		return songs.get(closest);
	}

	private Song getNearestEnergyValue(double value, List<Song> songs) {
		double userNum = value; // This is coming from user input
		double smallestDiff = Math.abs(userNum - songs.get(0).getEnergy());
		int closest = 0; // index of the current closest number

		for (int i = 1; i < songs.size(); i++) {
			double currentDiff = Math.abs(userNum - songs.get(i).getEnergy());
			if (currentDiff < smallestDiff) {
				smallestDiff = currentDiff;
				closest = i;
			}
		}
		return songs.get(closest);
	}

	private void orderPlaylist(Group g) {
		List<Song> listToOrder = g.getSongList();
		List<Song> newList = new ArrayList<Song>();
		double averageDance = calculateAverage(g.getDanceVotes());
		double averageEnergy = calculateAverage(g.getEnergyVotes());
		System.out.println(averageDance + " " + averageEnergy);
		if (averageDance >= averageEnergy) {
			System.out.println("Sorting on dance");
			while (!listToOrder.isEmpty()) {
				Song nearestSong = getNearestDanceValue(averageDance, listToOrder);
				newList.add(nearestSong);
				listToOrder.remove(nearestSong);
			}
		} else {
			System.out.println("Sorting on energy");
			while (!listToOrder.isEmpty()) {
				Song nearestSong = getNearestEnergyValue(averageEnergy, listToOrder);
				newList.add(nearestSong);
				listToOrder.remove(nearestSong);
			}
		}
		g.getSongList().clear();
		g.getSongList().addAll(newList);
	}

	private double calculateAverage(List<Double> values) {
		int counter = 0;
		double total = 0;
		for (double d : values) {
			total += d;
			counter++;
		}

		if (counter != 0) {
			return total / counter;
		}

		return 0.5;
	}

	private void initPlaylistOrdering() {
		Runnable runnable = new Runnable() {
			public void run() {
				for (Group g : groupList) {
					orderPlaylist(g);
				}
				System.out.println("Ordered all playlists");
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
	}

	private void initVoteReset() {
		Runnable runnable = new Runnable() {
			public void run() {
				for (Group g : groupList) {
					g.getDanceVotes().clear();
					g.getEnergyVotes().clear();
				}
				System.out.println("Resetted votes for all groups");
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 300, TimeUnit.SECONDS);
	}

}
