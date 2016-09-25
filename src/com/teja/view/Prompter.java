package com.teja.view;

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


    public String selectOneFromList(Collection<String> items) {
        System.out.println("Select one of an item");
        for(String  item: items) {
            System.out.println(item);
        }
        return scanner.nextLine();
    }
}