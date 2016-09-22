package com.teja.models;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Teja on 9/18/2016.
 */
public class Team {
    private String teamName;
    private String coachName;
    Set<Player> teamPlayers;




    public Team(String teamName, String coachName) {
        teamPlayers = new TreeSet<Player>();
        this.teamName = teamName;
        this.coachName = coachName;
    }

    public void addPlayerToTeam(Player player) {
        teamPlayers.add(player);
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
