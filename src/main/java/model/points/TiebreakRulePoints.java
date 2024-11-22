package model.points;

public class TiebreakRulePoints extends AbstractPoints {
    @Override
    protected int getWinningPoint() {
        return 7;
    }

    @Override
    protected int getWinningPointDifference() {
        return 2;
    }
}
