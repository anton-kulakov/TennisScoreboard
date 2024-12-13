package model.score;

import lombok.Getter;

import java.util.Optional;

public class BaseScore {
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;

    private EnumPlayer winner;

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }

    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
    }

    protected void addPoint(EnumPlayer pointWinner) {
        if (pointWinner.equals(EnumPlayer.FIRST_PLAYER)) {
            firstPlayerPoints++;
        } else {
            secondPlayerPoints++;
        }
    }

    protected boolean arePointsLessThanWinningPoint(int winningPoint) {
        return firstPlayerPoints < winningPoint && secondPlayerPoints < winningPoint;
    }

    protected boolean isThereWinningPointDifference(int winningPointDifference) {
        return Math.abs(firstPlayerPoints - secondPlayerPoints) >= winningPointDifference;
    }

    protected boolean arePointsEqualConstantPoint(int constantPoint) {
        return constantPoint == firstPlayerPoints && constantPoint == secondPlayerPoints;
    }

    protected void setWinner() {
        winner = (firstPlayerPoints > secondPlayerPoints) ? EnumPlayer.FIRST_PLAYER : EnumPlayer.SECOND_PLAYER;
    }
}
