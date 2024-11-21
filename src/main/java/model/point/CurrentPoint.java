package model.point;

import lombok.Getter;
import lombok.Setter;
import model.EnumPlayer;

import java.util.Optional;

public class CurrentPoint {
    private final RegularPoint regularPoint;
    private final TiebreakPoint tiebreakPoint;
    private final DeucePoint deucePoint;
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    @Setter
    private boolean isTiebreak;
    @Getter
    private boolean isFinished;
    private boolean isDeuce;
    private EnumPlayer winner;
    private final int DEUCE_SCORE_POINT = 3;
    public CurrentPoint(RegularPoint regularPoint, TiebreakPoint tiebreakPoint, DeucePoint deucePoint) {
        this.regularPoint = regularPoint;
        this.tiebreakPoint = tiebreakPoint;
        this.deucePoint = deucePoint;
    }

    public void update(EnumPlayer pointWinner) {
        if (isTiebreak) {
            tiebreakPoint.update(pointWinner);
            isFinished = tiebreakPoint.isFinished();

            if (isFinished) {
                if (tiebreakPoint.getOptionalWinner().isPresent()) {
                    winner = tiebreakPoint.getOptionalWinner().get();
                }
                tiebreakPoint.reset();
                isTiebreak = false;
            }

            firstPlayerPoints = tiebreakPoint.getFirstPlayerPoints();
            secondPlayerPoints = tiebreakPoint.getSecondPlayerPoints();
        } else if (isDeuce) {
            deucePoint.update(pointWinner);
            isFinished = deucePoint.isFinished();

            if (isFinished) {
                if (deucePoint.getOptionalWinner().isPresent()) {
                    winner = deucePoint.getOptionalWinner().get();
                }
                deucePoint.reset();
                isDeuce = false;
            }

            firstPlayerPoints = deucePoint.getFirstPlayerPoints();
            secondPlayerPoints = deucePoint.getSecondPlayerPoints();
        } else {
            regularPoint.update(pointWinner);
            isFinished = regularPoint.isFinished();

            if (isFinished) {
                if (regularPoint.getOptionalWinner().isPresent()) {
                    winner = regularPoint.getOptionalWinner().get();
                }
            }

            firstPlayerPoints = regularPoint.getFirstPlayerPoints();
            secondPlayerPoints = regularPoint.getSecondPlayerPoints();

            if (isPointsEqualDeucePoint()) {
                isDeuce = true;
            }
        }
    }

    private boolean isPointsEqualDeucePoint() {
        return DEUCE_SCORE_POINT == firstPlayerPoints && DEUCE_SCORE_POINT == secondPlayerPoints;
    }

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }
    public void reset() {
        if (isTiebreak) {
            tiebreakPoint.reset();
            isTiebreak = false;
        } else {
            regularPoint.reset();
        }

        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
    }
}
