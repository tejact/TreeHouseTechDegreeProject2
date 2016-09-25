package com.teja.view;

import com.teja.controller.MainController;

import java.util.Scanner;

/**
 * Created by Teja on 9/18/2016.
 */
public class MainView {

    MainController controller = new MainController();
    Prompter prompter = new Prompter(new Scanner(System.in));



    public void rolesView() {
        int role = Integer.parseInt(prompter.getDataFromUser("Please enter you role : 1.Organiser 2.Coach 3.Admin"));
        
        if(role == 1) {
            organiserView();
        }
    }

    private void organiserView() {
        int action = Integer.parseInt(prompter.getDataFromUser("Please enter you action : 1.Ceate Team 2.Add Player to Team 3.Remove Player form Team"));

        do{
        if(action == 1) {
            String teamName =prompter.getDataFromUser("Enter the name for new team");
            String coachName =prompter.getDataFromUser("Enter the coach for new team");
            boolean status = controller.createTeam(teamName,coachName);
            statusView(status);
            action = Integer.parseInt(prompter.getDataFromUser("Please enter you action : 1.Ceate Team 2.Add Player to Team 3.Remove Player form Team"));

        }
        else if (action == 2) {
            //Add Player to Team
            String teamName = getTeamFromAllTeams();
            String selectedPlayer = selectPlayerInAvailablePlayers();
            controller.addPlayer(teamName,selectedPlayer);
            action = Integer.parseInt(prompter.getDataFromUser("Please enter you action : 1.Ceate Team 2.Add Player to Team 3.Remove Player form Team"));
        }
        else if (action == 3) {
            //remove player form the team
            String teamName = getTeamFromAllTeams();
            String selectedPlayerToRemove = selectPLayerFromExistingPlayer(teamName);
            controller.removePlayer(teamName,selectedPlayerToRemove);
            action = Integer.parseInt(prompter.getDataFromUser("Please enter you action : 1.Ceate Team 2.Add Player to Team 3.Remove Player form Team"));
        }

        } while(action != 99);
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
