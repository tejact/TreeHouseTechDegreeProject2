package com.teja.models;

import java.util.Map;
import java.util.TreeMap;



public class Teams {


    private TreeMap<String,Team> allTeams;

    public Teams() {

        allTeams = new TreeMap<String,Team> ();
    }

    public void addTeam(Team team) {
 
        allTeams.put(team.getTeamName(),team);
    }

    public Map<String,Team> getAllTeams() {
        return allTeams;
    }

    public Team getTeam(String teamName) {
        return allTeams.get(teamName);
    }
}
