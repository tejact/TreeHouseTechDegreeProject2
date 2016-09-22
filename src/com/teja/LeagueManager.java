package com.teja;

import com.teja.models.Player;
import com.teja.models.Players;
import com.teja.view.MainView;

/**
 * Created by Teja on 9/18/2016.
 */
public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.println(players.length);

        MainView view = new MainView();
        view.rolesView();
    }

}