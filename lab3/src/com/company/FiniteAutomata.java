package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class FiniteAutomata {
    private HashMap<Integer,String> states;
    private HashSet<String> alphabet;
    private HashSet<Transition> transitions;
    private  String initialState;
    private HashSet<String> finalStates;

    /**
     * Constructor FiniteAutomaton class
     * @param fileName - the filename from each we read the elements
     *                 On the first line of the file will be the list of states followed by its state type:
     *                               form                :=state type,state type,.....
     *                               states              :=state {state}, where
     *                               stateValue          :=p1|p2
     *                               stateType           :=0|1|2
     *                               state               :=(stateValue,stateType)
     *                 On the next lines we have the transitions:one transition per line:
     *                               transitionLine          :=sourceState destinationState value
     *                               transitionSet          :=transitionLine{transitionLine}, where
     *                               sourceState, destinationState          :=p1|p2
     *                               value               :=lowercase-char | uppercase-char | digit
     *                               lowercase-char          := "a" | "b" | "..." | "z"
     *                               uppercase-char          := "A" | "B" | "..." | "Z"
     *                               digit                   := "0" | "1" | "..." | "9"
     *
     */
    public FiniteAutomata(String fileName) throws IOException {
        this.states=new HashMap<>();
        this.alphabet=new HashSet<>();
        this.transitions=new HashSet<>();
        this.finalStates=new HashSet<>();
        this.readFromFile(fileName);
    }

    private void readFromFile(String fileName) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            setStates(bufferedReader);
            setTransitionsAlphabet(bufferedReader);
        }
    }


    private void setStates(BufferedReader bufferedReader) throws IOException {
        String [] states=bufferedReader.readLine().split(",");
        for(String state:states){
            String[] st=state.split(" ");
            this.states.put(Integer.parseInt(st[1]),st[0]);
            if(st[1].equals("1"))
                this.initialState=st[0];
            else if (st[1].equals("2"))
                this.finalStates.add(st[0]);
        }
    }

    private void setTransitionsAlphabet(BufferedReader bufferedReader) throws IOException {
        String line;
        String [] transitionBuffer;
        while((line=bufferedReader.readLine())!=null){
            transitionBuffer=line.split(" ");
            Transition transition=new Transition(transitionBuffer[0],transitionBuffer[1],transitionBuffer[2]);
            this.transitions.add(transition);
            this.alphabet.add(transitionBuffer[2]);
            transitionBuffer=line.split(" ");
        }
    }

    private boolean acceptNullToken(){
        for(String state:this.finalStates) {
            if (this.initialState.equals(state))
                return true;
        }
        return false;

    }

    private boolean isCharInAlphabet(char ch){
        String str=String.valueOf(ch);
        for(String ch1:this.alphabet) {
            if (ch1.equals(str))
                return true;
        }
        return false;
    }

    public boolean isTokenValid(String token){
        if(token==null && acceptNullToken()==true)
            return true;
        else if(token==null && acceptNullToken()==false)
            return false;
        String state=this.initialState;
        int index=1;
        for(char ch:token.toCharArray()) {
            if (!isCharInAlphabet(ch))
                return false;
            else {
                boolean ok=false;
                for (Transition transition : this.transitions) {
                    if (transition.getSourceState().equals(state) && transition.getValue().equals(String.valueOf(ch))) {
                        ok = true;
                        state=transition.getDestinationState();
                    }
                }
                if(ok==false)
                    return false;
                if(index==token.length()){
                    boolean ok1=false;
                    for(String stateAux:this.finalStates){
                        if(stateAux.equals(state))
                            ok1=true;
                    }
                    if(ok1==true)
                        return true;
                    else
                        return false;
                }


            }
            index++;
        }
        return false;
    }

    public HashMap<Integer, String> getStates() {
        return states;
    }

    public HashSet<String> getAlphabet() {
        return alphabet;
    }

    public HashSet<Transition> getTransitions() {
        return transitions;
    }

    public String getInitialState() {
        return initialState;
    }

    public HashSet<String> getFinalStates() {
        return finalStates;
    }
}
