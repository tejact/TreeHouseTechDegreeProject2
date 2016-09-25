package com.teja.view;

import com.teja.controller.MainController;
import com.teja.models.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MainView {

    MainController controller = new MainController();
    Prompter prompter = new Prompter(new Scanner(System.in));



    public void rolesView() {
        int role = Integer.parseInt(prompter.getDataFromUser("Please enter you role : 1.Organiser 2.Coach 3.Admin"));
        
        if(role == 1) {
            organiserView();
        }
        else if(role == 2){
            coachView();
        }
    }

    private void coachView() {
        String teamName =prompter.getDataFromUser("Enter the name of your team: ");
        Map<String,Player> playersInTeam = controller.getPlayersInTeam(teamName);
        for(String playerName : playersInTeam.keySet()) {
            System.out.println(playerName);
        }

    }

    private void organiserView() {
        final String organiserWelcomePrompt = "Please enter you action : 1.Ceate Team 2.Add Player to Team 3.Remove Player form Team 4.View Report by height " +
                "5.View League Balance Report   6.Exit ";
        int action = Integer.parseInt(prompter.getDataFromUser(organiserWelcomePrompt));

        do{
            if(action == 1) {
                String teamName =prompter.getDataFromUser("Enter the name for new team");
                String coachName =prompter.getDataFromUser("Enter the coach for new team");
                boolean status = controller.createTeam(teamName,coachName);
                statusView(status);
            }
            else if (action == 2) {
                //Add Player to Team
                String teamName = getTeamFromAllTeams();
                String selectedPlayer = selectPlayerInAvailablePlayers();
                controller.addPlayer(teamName,selectedPlayer);
            }
            else if (action == 3) {
                //remove player form the team
                String teamName = getTeamFromAllTeams();
                String selectedPlayerToRemove = selectPLayerFromExistingPlayer(teamName);
                controller.removePlayer(teamName,selectedPlayerToRemove);
            }
            else if(action == 4){
                //Get teamName for the height report
                String teamName = getTeamFromAllTeams();
                //Key stores the range like
                Map<String,ArrayList<String>> rangeMap  = controller.viewReportByHeight(teamName);

                for(Map.Entry<String,ArrayList<String>> entry : rangeMap.entrySet()) {
                    String range = entry.getKey();
                    ArrayList<String> list = (ArrayList)entry.getValue();
                    System.out.println();
                    System.out.println(range);
                    for(String playerName : list) {
                        System.out.println(playerName);
                    }
                }
            }
            else if(action == 5) {
                Map<String,ArrayList<Integer>> leagueBalance = controller.viewLeagueBalanceReport();
                for (Map.Entry<String,ArrayList<Integer>> entry : leagueBalance.entrySet()) {
                    String teamName = entry.getKey();
                    ArrayList list = entry.getValue();
                    System.out.println(teamName+" "+list.get(0)+" "+list.get(1));
                }
            }
            else if(action == 6) {
                rolesView();
            }
            action = Integer.parseInt(prompter.getDataFromUser(organiserWelcomePrompt));
        } while(action > 0 && action < 7);
    }


    private String getTeamFromAllTeams() {
        String inputTeam =  prompter.selectOneFromList(controller.getTeams().keySet());
        return inputTeam;
    }


    private String selectPlayerInAvailablePlayers() {
        String selectedPlayer =  prompter.selectOneFromList(controller.getAvailablePlayers().keySet());
        return selectedPlayer;
    }

    private String selectPLayerFromExistingPlayer(String teamName) {
        return  prompter.selectOneFromList(controller.getTeams().get(teamName).getTeamPlayers().keySet());
    }


    private void statusView(boolean status) {
        String message = "";
        if(status) {
           message =  "Operation Success";
        }
        else {
            message = "Sorry Operation got failed!!!";
        }

        System.out.println(message);
    }

}
