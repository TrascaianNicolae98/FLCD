package com.company;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    private FiniteAutomata finiteAutomata;
    Scanner scan = new Scanner(System.in);
    public Ui(String fileName) throws IOException {
            this.finiteAutomata = new FiniteAutomata(fileName);
    }

    public void menu(){
        System.out.println("1-Get states.");
        System.out.println("2-Get alphabet.");
        System.out.println("3-Get transitions.");
        System.out.println("4-Get initial state.");
        System.out.println("5-Get final states.");
        System.out.println("6-Is token valid.");
    }

    public void printStates(){
        for(Map.Entry state:this.finiteAutomata.getStates().entrySet())
            System.out.println(state.getValue());
    }

    public void printAlphabet(){
        for(String elemnt:this.finiteAutomata.getAlphabet())
            System.out.println(elemnt);
    }

    public void printTransitions(){
        for(Transition transition:this.finiteAutomata.getTransitions())
            System.out.println(transition);
    }

    public void printInitialState(){
        System.out.println(this.finiteAutomata.getInitialState());
    }

    public void printFinalStates(){
        for(String state:this.finiteAutomata.getFinalStates())
            System.out.println(state);
    }

    public void printValidToken(){
        System.out.println("Input the token:");
        String token=scan.nextLine();
        if(this.finiteAutomata.isTokenValid(token))
            System.out.println("Token is valid.");
        else
            System.out.println("Token is not valid.");
    }

    String option;
    public void run(){
        while(true){
            this.menu();
            System.out.println("Choose an option!");
            option=scan.nextLine();
            if(option.equals("1"))
                this.printStates();
            else if(option.equals("2"))
                this.printAlphabet();
            else if(option.equals("3"))
                this.printTransitions();
            else if(option.equals("4"))
                this.printInitialState();
            else if(option.equals("5"))
                this.printFinalStates();
            else if(option.equals("6"))
                this.printValidToken();
            else
                break;
        }
    }
}
