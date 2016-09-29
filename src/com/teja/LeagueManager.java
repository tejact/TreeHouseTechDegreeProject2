package com.teja;

import com.teja.models.Player;
import com.teja.models.Players;
import com.teja.view.MainView;


public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.println(players.length);

        //Initialize View
        MainView view = new MainView();
        //Call Main view
        view.rolesView();
    }

}