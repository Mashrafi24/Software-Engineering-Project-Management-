package com.mathwhiz.model;
import java.io.Serializable;
public class GameQuestion implements Serializable {
    private final String type, prompt, operation;
    private final int marks, number, first, second;
    private final double degree;
    public GameQuestion(String type,String prompt,int marks,int number,int first,int second,String operation,double degree) {
        this.type=type; this.prompt=prompt; this.marks=marks; this.number=number; this.first=first; this.second=second; this.operation=operation; this.degree=degree;
    }
    public String getType(){return type;} public String getPrompt(){return prompt;} public int getMarks(){return marks;}
    public int getNumber(){return number;} public int getFirst(){return first;} public int getSecond(){return second;}
    public String getOperation(){return operation;} public double getDegree(){return degree;}
}
