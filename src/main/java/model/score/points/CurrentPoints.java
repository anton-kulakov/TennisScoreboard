package model.score.points;

import lombok.Getter;
import lombok.Setter;
import model.score.EnumPlayer;

import java.util.Optional;

public class CurrentPoints {
    private final RegularRulePoints regularRulePoints;
    private final TiebreakRulePoints tiebreakRulePoints;
    private final DeuceRulePoints deuceRulePoints;
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    @Setter
    private boolean isTiebreak;
    private boolean isDeuce;
    private EnumPlayer winner;
    private static final int DEUCE_ONE_POINT = 1;
    private static final int DEUCE_SCORE_POINT = 3;

    public CurrentPoints(RegularRulePoints regularRulePoints, DeuceRulePoints deuceRulePoints, TiebreakRulePoints tiebreakRulePoints) {
        this.regularRulePoints = regularRulePoints;
        this.deuceRulePoints = deuceRulePoints;
        this.tiebreakRulePoints = tiebreakRulePoints;
    }

    public void update(EnumPlayer pointWinner) {
        if (isTiebreak) {
            tiebreakRulePoints.update(pointWinner);

            if (isFinished(tiebreakRulePoints)) {
                winner = getWinner(tiebreakRulePoints);
                resetOtherRulePoints(tiebreakRulePoints);
                isTiebreak = false;
            }

            refreshCurrentPoints(tiebreakRulePoints);
            return;
        }

        if (isDeuce) {
            deuceRulePoints.update(pointWinner);

            if (isFinished(deuceRulePoints)) {
                winner = getWinner(deuceRulePoints);
                resetOtherRulePoints(deuceRulePoints);
                isDeuce = false;
            }

            if (isDeuceOnePointEqualPlayersPoints(deuceRulePoints.getFirstPlayerPoints(), deuceRulePoints.getSecondPlayerPoints())) {
                deuceRulePoints.reset();
            }

            refreshCurrentPoints(deuceRulePoints);
            return;
        }

        regularRulePoints.update(pointWinner);

        if (isFinished(regularRulePoints)) {
            winner = getWinner(regularRulePoints);
            resetOtherRulePoints(regularRulePoints);
        }

        refreshCurrentPoints(regularRulePoints);

        if (isDeuceScorePointEqualPlayersPoints()) {
            isDeuce = true;
        }
    }

    public boolean getIsTiebreak() {
        return isTiebreak;
    }

    public boolean getIsDeuce() {
        return isDeuce;
    }

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }

    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
    }

    private void refreshCurrentPoints(AbstractPoints points) {
        firstPlayerPoints = points.getFirstPlayerPoints();
        secondPlayerPoints = points.getSecondPlayerPoints();
    }

    private boolean isFinished(AbstractPoints points) {
        return points.getOptionalWinner().isPresent();
    }

    private EnumPlayer getWinner(AbstractPoints points) {
        return points.getOptionalWinner().get();
    }

    private void resetOtherRulePoints(AbstractPoints updatedPoints) {
        if (updatedPoints.equals(tiebreakRulePoints)) {
            tiebreakRulePoints.reset();
        }

        if (updatedPoints.equals(deuceRulePoints)) {
            deuceRulePoints.reset();
            regularRulePoints.reset();
        }

        if (updatedPoints.equals(regularRulePoints)) {
            regularRulePoints.reset();
        }
    }

    private boolean isDeuceScorePointEqualPlayersPoints() {
        return DEUCE_SCORE_POINT == firstPlayerPoints && DEUCE_SCORE_POINT == secondPlayerPoints;
    }

    private boolean isDeuceOnePointEqualPlayersPoints(int deuceFirstPlayerPoint, int deuceSecondPlayerPoint) {
        return DEUCE_ONE_POINT == deuceFirstPlayerPoint && DEUCE_ONE_POINT == deuceSecondPlayerPoint;
    }
}
