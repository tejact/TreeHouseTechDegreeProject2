package com.teja.view;

import com.teja.models.Team;

import java.util.Collection;
import java.util.Scanner;




public class  Prompter {
    final Scanner scanner;

    public Prompter(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getDataFromUser(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }


    public String  selectOneFromList(Collection<String> items) {
        System.out.println("Select one of an item");
        for(String  item: items) {
            System.out.println(item);
        }
        return scanner.nextLine();
    }

    public String displayAllTeams(Collection<Team> teams) {
        System.out.println("Please enter the name of the team");
        for(Team team: teams) {
            System.out.println("Team::: "+team.getTeamName()+" Coach::: "+team.getCoachName());
        }
        return scanner.nextLine();
    }
}