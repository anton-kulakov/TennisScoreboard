import model.EnumPlayer;
import model.points.CurrentPoints;
import model.points.DeuceRulePoints;
import model.points.RegularRulePoints;
import model.points.TiebreakRulePoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentPointsTest {
    private CurrentPoints currentPoints;

    @BeforeEach
    public void currentPointsInit() {
        currentPoints = new CurrentPoints(new RegularRulePoints(), new DeuceRulePoints(), new TiebreakRulePoints());
    }

    @Test
    @DisplayName("When first player wins point and points are 30-0 then first player wins")
    public void winCurrentPoint() {
        for (int i = 0; i < 4; i++) {
            currentPoints.update(EnumPlayer.FIRST_PLAYER);
        }

        assertEquals(EnumPlayer.FIRST_PLAYER, currentPoints.getOptionalWinner().get());
    }

    @Test
    @DisplayName("When the score is tied at 40-40, the match continues, the winner isn't defined")
    public void shouldNotHaveWinnerWhenThereIsDeuce() {
        for (int i = 0; i < 3; i++) {
            currentPoints.update(EnumPlayer.FIRST_PLAYER);
            currentPoints.update(EnumPlayer.SECOND_PLAYER);
        }

        assertEquals(Optional.empty(), currentPoints.getOptionalWinner());
    }

    @Test
    @DisplayName("When first player wins point and points are Ad-0 then first player wins")
    public void shouldWinWhenThereIsAdAndOnePoint() {
        for (int i = 0; i < 3; i++) {
            currentPoints.update(EnumPlayer.FIRST_PLAYER);
            currentPoints.update(EnumPlayer.SECOND_PLAYER);
        }

        for (int i = 0; i < 2; i++) {
            currentPoints.update(EnumPlayer.FIRST_PLAYER);
        }

        assertEquals(EnumPlayer.FIRST_PLAYER, currentPoints.getOptionalWinner().get());
    }

    @Test
    @DisplayName("When first player wins point and points are 0-Ad then the points change to 0-0")
    public void scoreShouldBeEqualDeuce() {
        for (int i = 0; i < 3; i++) {
            currentPoints.update(EnumPlayer.FIRST_PLAYER);
            currentPoints.update(EnumPlayer.SECOND_PLAYER);
        }

        currentPoints.update(EnumPlayer.FIRST_PLAYER);
        currentPoints.update(EnumPlayer.SECOND_PLAYER);

        assertEquals(0, currentPoints.getFirstPlayerPoints());
        assertEquals(0, currentPoints.getSecondPlayerPoints());
    }
}
