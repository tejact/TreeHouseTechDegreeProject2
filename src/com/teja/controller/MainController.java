package com.teja.controller;

import com.teja.exceptions.*;
import com.teja.models.*;

import java.util.*;


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


        try {
            Team team = teams.getTeam(teamName);
        Player player = players.getPlayer(playerName);
        team.addPlayerToTeam(playerName,player);
        players.removePlayerFromAvailablePlayers(playerName);
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
        } catch (TeamNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Map<String,Player> getPlayersInTeam(String teamName) {
        Team team = null;
        try {
            team = teams.getTeam(teamName);
        } catch (TeamNotFoundException e) {
            e.printStackTrace();
        }
        return team.getTeamPlayers();
    }


    public Map<String,Team> getTeams() {
        return teams.getAllTeams();
    }

    public Map<String,Player> getAvailablePlayers() {
        return players.getAvailablePlayers();
    }

    public void removePlayer(String teamName, String playerName) {

        try {
            Team team = teams.getTeam(teamName);
            Player player = null;
            player = players.getPlayer(playerName);
            team.removePlayer(playerName);
            players.addPlayerToAvailablePlayers(playerName,player);
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
        } catch (TeamNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Map<String, ArrayList<String>> viewReportByHeight(String teamName) throws TeamNotFoundException {

        //Get teamName for the height report
        Team team = null;

        team = teams.getTeam(teamName);
        //Get all the players of the above provided team.
        Map<String,Player> teamPlayers = team.getTeamPlayers();

        //Key stores the range like 35-40,41-46
        //Values is the ArrayList of player names that fall in this range
        Map<String,ArrayList<String>> rangeMap = new HashMap<String,ArrayList<String>>();

        //Initialize the rangeMap with the defined values and empty ArrayList as values
        rangeMap.put("35-40",new ArrayList<String>());
        rangeMap.put("41-46",new ArrayList<String>());
        rangeMap.put("47-50",new ArrayList<String>());


        //Iterage over all the players and added to suitable ArrayList based on the height.
        for(Player player : teamPlayers.values()){
            int height = player.getHeightInInches();
            String playerName = player.getFirstName()+" "+player.getLastName();
            if(height >= 35 && height <= 40 ){
                rangeMap.get("35-40").add(playerName);
            }
            else if(height >= 41 && height <= 46) {
                rangeMap.get("41-46").add(playerName);
            }
            else
            {
                rangeMap.get("47-50").add(playerName);
            }
        }

        //The range map is returned to View. Viwe iterate over it and prints in the required format
        return rangeMap;
    }

    //Map<String, ArrayList<Integer>>
    // Key is the team name and Value is the List containing experienced and inexperienced players
    public Map<String, ArrayList<Integer>> viewLeagueBalanceReport() {
        Map<String,Team> allTeams = teams.getAllTeams();
        Map<String,ArrayList<Integer>>  leagueBalance =
                new TreeMap<String,ArrayList<Integer>>();
        for(Team team : allTeams.values()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int experienced = 0;
            int inExperienced = 0;
            Map<String,Player> teamPlayers = team.getTeamPlayers();
            for(Player player : teamPlayers.values()){
               if(player.isPreviousExperience()) {
                   experienced++;
               } else {
                   inExperienced++;
               }
            }
            list.add(experienced);
            list.add(inExperienced);
            leagueBalance.put(team.getTeamName(),list);
        }
        return leagueBalance;
    }
}
