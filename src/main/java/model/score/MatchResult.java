package model.score;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MatchResult {
    private Map<String, Integer> firstSet;
    private Map<String, Integer> secondSet;
    private Map<String, Integer> thirdSet;
    private String firstPlayerName;
    private String secondPlayerName;
    private String winner;

    public MatchResult() {
        this.firstSet = new HashMap<>();
        this.secondSet = new HashMap<>();
        this.thirdSet = new HashMap<>();
    }
}
