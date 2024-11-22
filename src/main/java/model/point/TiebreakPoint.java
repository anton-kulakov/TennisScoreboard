package model.point;

public class TiebreakPoint extends BaseGamePoint {
    @Override
    protected int getWinningPoint() {
        return 7;
    }

    @Override
    protected int getWinningPointDifference() {
        return 2;
    }
}
