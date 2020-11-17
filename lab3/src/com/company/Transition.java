package com.company;

public class Transition {
    private String sourceState;
    private String destinationState;
    private String value;


    public Transition(String sourceState,String destinationState,String value){
        this.sourceState=sourceState;
        this.destinationState=destinationState;
        this.value=value;
    }

    public String getSourceState() {
        return sourceState;
    }

    public void setSourceState(String sourceState) {
        this.sourceState = sourceState;
    }

    public String getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "sourceState='" + sourceState + '\'' +
                ", destinationState='" + destinationState + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
