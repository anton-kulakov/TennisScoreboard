package model;

import lombok.Getter;
import model.points.CurrentPoints;
import model.points.DeuceRulePoints;
import model.points.RegularRulePoints;
import model.points.TiebreakRulePoints;

@Getter
public class MatchScore {
    private final SetScore setScore;
    private final GameScore gameScore;
    private final CurrentPoints currentPoints;

    public MatchScore() {
        RegularRulePoints regularRulePoints = new RegularRulePoints();
        TiebreakRulePoints tiebreakRulePoints = new TiebreakRulePoints();
        DeuceRulePoints deuceRulePoints = new DeuceRulePoints();
        this.currentPoints = new CurrentPoints(regularRulePoints, tiebreakRulePoints, deuceRulePoints);
        this.gameScore = new GameScore(currentPoints);
        this.setScore = new SetScore(gameScore);
    }

    public void update(EnumPlayer pointWinner) {
        setScore.update(pointWinner);
    }
}
