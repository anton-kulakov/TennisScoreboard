package model;

import lombok.Getter;
import model.point.CurrentPoint;

import java.util.Optional;

public class GameScore {
    private final CurrentPoint currentPoint;
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    @Getter
    private boolean isFinished;
    private boolean isTiebreak;
    EnumPlayer winner;
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

        if (currentPoint.isFinished() && currentPoint.getOptionalWinner().isPresent()) {
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
        isFinished = true;
    }

    private void addPoint(EnumPlayer pointWinner) {
        if (pointWinner.equals(EnumPlayer.FIRST_PLAYER)) {
            firstPlayerPoints++;
        } else {
            secondPlayerPoints++;
        }
    }
    private void setWinner() {
        winner = (firstPlayerPoints > secondPlayerPoints) ? EnumPlayer.FIRST_PLAYER : EnumPlayer.SECOND_PLAYER;
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
    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }
    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
        isFinished = false;
        winningPointDifference = 2;
    }
}
