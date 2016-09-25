package com.teja.controller;

import com.teja.models.Player;
import com.teja.models.Players;
import com.teja.models.Team;
import com.teja.models.Teams;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Teja on 9/18/2016.
 */
public class MainController {

    Teams teams;
    Players players;
    Set<Player> availablePlayers;

    public MainController() {
        this.teams = new Teams();
        this.players = new Players();
        availablePlayers = new TreeSet<Player>();
        for(Player player : Players.load()) {
            availablePlayers.add(player);
        }
    }


    public boolean createTeam(String teamName, String coachName) {
        Team team = new Team(teamName,coachName);
        teams.addTeam(team);
        return true;
    }

    public void addPlayer(String teamName , String playerName) {
        Team team = teams.getTeam(teamName);
        Player player = players.getPlayer(playerName);
        team.addPlayerToTeam(playerName,player);
        players.removePlayerFromAvailablePlayers(playerName);
    }


    public Map<String,Team> getTeams() {
        return teams.getAllTeams();
    }

    public Map<String,Player> getAvailablePlayers() {
        return players.getAvailablePlayers();
    }

    public void removePlayer(String teamName, String playerName) {
        Team team = teams.getTeam(teamName);
        Player player = players.getPlayer(playerName);
        team.removePlayer(playerName);
        players.addPlayerToAvailablePlayers(playerName,player);



    }
}
