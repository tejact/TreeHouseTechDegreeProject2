package com.teja.models;

import com.teja.exceptions.*;

import java.util.Map;
import java.util.TreeMap;



public class Teams {


    //Key is team name
    //Value is the team object
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

    public Team getTeam(String teamName) throws TeamNotFoundException {
        if(!allTeams.containsKey(teamName)) {
            throw new TeamNotFoundException("Sorry , Team not found");
        }
        return allTeams.get(teamName);
    }
}
