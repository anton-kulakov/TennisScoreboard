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
    private final RegularPoint regularPoint;
    private final TiebreakPoint tiebreakPoint;
    private final DeucePoint deucePoint;
    private EnumPlayer winner;
    private final int WINNING_POINT = 2;

    @Getter
    private boolean isFinished;
    public MatchScore() {
        this.regularPoint = new RegularPoint();
        this.tiebreakPoint = new TiebreakPoint();
        this.deucePoint = new DeucePoint();
        this.currentPoint = new CurrentPoint(regularPoint, tiebreakPoint, deucePoint);
        this.gameScore = new GameScore(currentPoint);
        this.setScore = new SetScore(gameScore);
    }

    public void update(EnumPlayer pointWinner) {
        setScore.update(pointWinner);

        if (setScore.isFinished()) {
            if (setScore.getOptionalWinner().isPresent()) {
                winner = setScore.getOptionalWinner().get();
            }
            isFinished = true;
        }
    }

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }
}
