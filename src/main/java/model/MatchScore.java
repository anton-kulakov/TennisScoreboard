package model;

import lombok.Getter;
import model.point.CurrentPoint;
import model.point.DeucePoint;
import model.point.RegularPoint;
import model.point.TiebreakPoint;

import java.util.Optional;

public class MatchScore {
    @Getter
    private final SetScore setScore;
    @Getter
    private final GameScore gameScore;
    @Getter
    private final CurrentPoint currentPoint;
    private EnumPlayer winner;

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

        if (setScore.getOptionalWinner().isPresent()) {
            winner = setScore.getOptionalWinner().get();
        }
    }

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }
}
