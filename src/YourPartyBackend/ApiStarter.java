package YourPartyBackend;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Scanner;

import com.google.gson.Gson;

import model.Group;
import model.Song;
import model.Vote;
import service.GroupService;
import util.CorsHandler;
import util.DefaultResponse;

public class ApiStarter {

	public static void main(String[] args) {
		GroupService groupService = new GroupService();
		CorsHandler corsHandler = new CorsHandler();

		get("/api/group/:id", (request, response) -> {
			response.type("application/json");
			Gson gson = new Gson();
			return gson.toJson(groupService.getGroup(request.params("id")));
		});

		get("/api/group/playlist/:id", (request, response) -> {
			response.type("application/json");
			Gson gson = new Gson();
			return gson.toJson(groupService.getGroupSongList(request.params("id")));
		});

		put(("/api/group/removefirst/:id"), (request, response) -> {
			groupService.removeFirstSongFromPlaylist(request.params(":id"));
			return DefaultResponse.SUCCES;
		});

		post("/api/group/create", (request, response) -> {
			response.type("application/json");
			Group g = new Gson().fromJson(request.body(), Group.class);
			groupService.addGroup(g);
			return g.getId();
		});

		post("/api/group/add/:id", (request, response) -> {
			response.type("application/json");
			Song s = new Gson().fromJson(request.body(), Song.class);
			System.out.println(
					s.getName() + " from " + s.getArtistName() + " " + s.getEnergy() + " " + s.getDanceability());
			groupService.addSongToGroup(request.params(":id"), s);
			return DefaultResponse.SUCCES;
		});
		
		post("/api/group/vote/:id", (request, response) -> {
			response.type("application/json");
			Vote v = new Gson().fromJson(request.body(), Vote.class);
			groupService.addVoteToGroup(request.params(":id"), v);
			return DefaultResponse.SUCCES;
		});

	}

}
