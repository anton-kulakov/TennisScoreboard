package model;

import lombok.Getter;
import model.point.CurrentPoint;
import model.point.DeucePoint;
import model.point.RegularPoint;
import model.point.TiebreakPoint;

@Getter
public class MatchScore {
    private final SetScore setScore;
    private final GameScore gameScore;
    private final CurrentPoint currentPoint;

    public MatchScore() {
        RegularPoint regularPoint = new RegularPoint();
        TiebreakPoint tiebreakPoint = new TiebreakPoint();
        DeucePoint deucePoint = new DeucePoint();
        this.currentPoint = new CurrentPoint(regularPoint, tiebreakPoint, deucePoint);
        this.gameScore = new GameScore(currentPoint);
        this.setScore = new SetScore(gameScore);
    }

    public void update(EnumPlayer pointWinner) {
        setScore.update(pointWinner);
    }
}
