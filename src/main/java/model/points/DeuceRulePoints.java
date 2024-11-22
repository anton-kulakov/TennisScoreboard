package model.points;

public class DeuceRulePoints extends AbstractPoints {
    @Override
    protected int getWinningPoint() {
        return 2;
    }

    @Override
    protected int getWinningPointDifference() {
        return 2;
    }
}
