package model.score;

import lombok.Getter;
import model.score.points.CurrentPoints;
import model.score.points.DeuceRulePoints;
import model.score.points.TiebreakRulePoints;
import model.score.points.RegularRulePoints;

@Getter
public class MatchScore {
    private final SetScore setScore;
    private final GameScore gameScore;
    private final CurrentPoints currentPoints;
    private final MatchResult matchResult;

    public MatchScore() {
        RegularRulePoints regularRulePoints = new RegularRulePoints();
        TiebreakRulePoints tiebreakRulePoints = new TiebreakRulePoints();
        DeuceRulePoints deuceRulePoints = new DeuceRulePoints();
        this.matchResult = new MatchResult();
        this.currentPoints = new CurrentPoints(regularRulePoints, deuceRulePoints, tiebreakRulePoints);
        this.gameScore = new GameScore(currentPoints);
        this.setScore = new SetScore(gameScore, matchResult);
    }

    public void update(EnumPlayer pointWinner) {
        setScore.update(pointWinner);
    }
}
