package com.mathwhiz.model;
import java.io.Serializable;
import java.util.List;
public class GameResult implements Serializable {
    private final String playerName; private final int score; private final List<String> feedback;
    public GameResult(String playerName,int score,List<String> feedback){this.playerName=playerName;this.score=score;this.feedback=List.copyOf(feedback);}
    public String getPlayerName(){return playerName;} public int getScore(){return score;} public List<String> getFeedback(){return feedback;}
}
