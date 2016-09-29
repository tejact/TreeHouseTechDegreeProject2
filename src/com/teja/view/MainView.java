package com.teja.view;

import com.teja.controller.MainController;
import com.teja.exceptions.*;
import com.teja.models.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MainView {

    //Controller to perfomr all operations on model
    MainController controller = new MainController();
    Prompter prompter = new Prompter(new Scanner(System.in));


    //Provide options to select Organiser or Coach
    public void rolesView() {
        int role = Integer.parseInt(prompter.getDataFromUser("Please enter you role : 1.Organiser 2.Coach 3.Exit"));
        
        if(role == 1) {
            organiserView();
        }
        else if(role == 2){
            coachView();
        } else {
            return;
        }
    }

    private void organiserView()  {
        final String organiserWelcomePrompt = "Please enter you action : 1.Ceate Team 2.Add Player to Team 3.Remove Player form Team 4.View Report by height " +
                "5.View League Balance Report   6.Exit ";
        //Prompt the provided string and get input form user
        int action = Integer.parseInt(prompter.getDataFromUser(organiserWelcomePrompt));

        do{
            //Create Team
            if(action == 1) {
                String teamName =prompter.getDataFromUser("Enter the name for new team");
                String coachName =prompter.getDataFromUser("Enter the coach for new team");
                //Use controller to create team.
                boolean status = controller.createTeam(teamName,coachName);
                statusView(status);
            }
            //Add Player to team
            else if (action == 2) {
                //Add Player to Team
                String teamName = getTeamFromAllTeams();
                //selectPlayerInAvailablePlayers calls controller
                String selectedPlayer = selectPlayerInAvailablePlayers();
                controller.addPlayer(teamName,selectedPlayer);
            }
            //remove player form the team
            else if (action == 3) {
                String teamName = getTeamFromAllTeams();
                String selectedPlayerToRemove = selectPLayerFromExistingPlayer(teamName);
                //Using controller to remove players
                controller.removePlayer(teamName,selectedPlayerToRemove);
            }
            //View Remport by height
            else if(action == 4){
                //Get teamName for the height report
                String teamName = getTeamFromAllTeams();
                //Key stores the range like
                Map<String,ArrayList<String>> rangeMap  = null;
                try {
                    //Get the report in the strucuture  Map<String, ArrayList<String>>
                    //Key is range like '35-40' and value contaitns lisf of Player names
                    rangeMap = controller.viewReportByHeight(teamName);
                    //Iterate and print
                    for(Map.Entry<String,ArrayList<String>> entry : rangeMap.entrySet()) {
                        String range = entry.getKey();
                        ArrayList<String> list = (ArrayList)entry.getValue();
                        System.out.println();
                        System.out.println(range);
                        for(String playerName : list) {
                            System.out.println(playerName);
                        }
                    }
                } catch (TeamNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //View League balance report.
            else if(action == 5) {
                //Map<String, ArrayList<Integer>>
                // Key is the team name and Value is the List containing experienced and inexperienced players
                Map<String,ArrayList<Integer>> leagueBalance = controller.viewLeagueBalanceReport();
                //Iterate and print
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


    //Coach has only one option to view roaster
    private void coachView() {
        String teamName =prompter.getDataFromUser("Enter the name of your team: ");
        Map<String,Player> playersInTeam = controller.getPlayersInTeam(teamName);
        for(String playerName : playersInTeam.keySet()) {
            System.out.println(playerName);
        }

    }

    //helper method for organiserView()
    private String getTeamFromAllTeams() {
        String inputTeam =  prompter.displayAllTeams(controller.getTeams().values());
        return inputTeam;
    }

    //helper method for organiserView()
    //Prompt user with all the available players.
    private String selectPlayerInAvailablePlayers() {
        //Available players data is retrieved through the controller.
        String selectedPlayer =  prompter.selectOneFromList(controller.getAvailablePlayers().keySet());
        return selectedPlayer;
    }

    //helper method for organiserView()
    private String selectPLayerFromExistingPlayer(String teamName) {
        return  prompter.selectOneFromList(controller.getTeams().get(teamName).getTeamPlayers().keySet());
    }

    //General helper method
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
