package model;

import model.point.CurrentPoint;

public class GameScore extends BaseScore {
    private final CurrentPoint currentPoint;
    private boolean isTiebreak;
    private final int WINNING_POINT = 6;
    private int winningPointDifference = 2;
    public GameScore(CurrentPoint currentPoint) {
        this.currentPoint = currentPoint;
    }
    void update(EnumPlayer pointWinner) {
        if (isPointsEqualWinningPoint()) {
            isTiebreak = true;
            currentPoint.setTiebreak(isTiebreak);
            winningPointDifference = 1;
        }

        currentPoint.update(pointWinner);

        if (currentPoint.getOptionalWinner().isPresent()) {
            addPoint(currentPoint.getOptionalWinner().get());
            currentPoint.reset();
        }

        if (isPointsLessThanWinningPoint()) {
            return;
        }

        if (!isThereWinningPointDifference()) {
            return;
        }

        setWinner();
    }

    private boolean isPointsEqualWinningPoint() {
        return WINNING_POINT == firstPlayerPoints && WINNING_POINT == secondPlayerPoints;
    }

    private boolean isThereWinningPointDifference() {
        return Math.abs(firstPlayerPoints - secondPlayerPoints) >= winningPointDifference;
    }

    private boolean isPointsLessThanWinningPoint() {
        return firstPlayerPoints < WINNING_POINT && secondPlayerPoints < WINNING_POINT;
    }
    public void resetWinningPointDifference() {
        winningPointDifference = 2;
    }

    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
    }
}
