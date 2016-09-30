package com.teja.models;

import com.teja.exceptions.PlayerNotFoundException;

import java.util.Map;
import java.util.TreeMap;


public class Team {
    private String teamName;
    private String coachName;
    //all players in a team are represented with a map
    //Key is player firstName + player lastName
    //Value is Player object
    private TreeMap<String,Player> teamPlayers;
    public Team(String teamName, String coachName) {
        teamPlayers = new TreeMap<String,Player>();
        this.teamName = teamName;
        this.coachName = coachName;
    }

    public void addPlayerToTeam(String playerName, Player player) {
        teamPlayers.put(playerName,player);
    }

    public Player getPlayerFromTeam(String playerName) throws PlayerNotFoundException {
        if(!teamPlayers.containsKey(playerName)) {
            throw new PlayerNotFoundException("Player not found");
        }
        return teamPlayers.get(playerName);
    }

    public void removePlayer(String playerName) {
        teamPlayers.remove(playerName);
    }

    public Map<String,Player> getTeamPlayers() {
        return teamPlayers;
    }



    public String getTeamName() {
        return teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return teamName.equals(team.teamName);
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

}
