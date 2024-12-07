import model.score.EnumPlayer;
import model.score.MatchScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchScoreTest {
    private MatchScore matchScore;

    @BeforeEach
    public void currentPointsInit() {
        matchScore = new MatchScore();
    }

    @Test
    @DisplayName("When first player wins point and games are 5-0 then first player wins set")
    public void winSet() {
        for (int i = 0; i < 6; i++) {
            addOneGame(EnumPlayer.FIRST_PLAYER);
        }

        assertEquals(1, matchScore.getSetScore().getFirstPlayerPoints());
        assertEquals(0, matchScore.getSetScore().getSecondPlayerPoints());
    }

    @Test
    @DisplayName("When first player wins point and games are 5-4 then first player wins set")
    public void winSetWithTwoPointsAbove() {
        for (int i = 0; i < 4; i++) {
            addOneGame(EnumPlayer.FIRST_PLAYER);
        }

        for (int i = 0; i < 4; i++) {
            addOneGame(EnumPlayer.SECOND_PLAYER);
        }
        // games: 4-4

        for (int i = 0; i < 2; i++) {
            addOneGame(EnumPlayer.FIRST_PLAYER);
        }
        // games: 6-4

        assertEquals(1, matchScore.getSetScore().getFirstPlayerPoints());
        assertEquals(0, matchScore.getSetScore().getSecondPlayerPoints());
    }

    @Test
    @DisplayName("When first player wins point and games are 5-5 then the game continues")
    public void shouldNotWinSetWhenPointDifferenceIsOne() {
        for (int i = 0; i < 4; i++) {
            addOneGame(EnumPlayer.FIRST_PLAYER);
        }

        for (int i = 0; i < 5; i++) {
            addOneGame(EnumPlayer.SECOND_PLAYER);
        }
        // games: 4-5

        for (int i = 0; i < 2; i++) {
            matchScore.update(EnumPlayer.FIRST_PLAYER);
        }
        // games: 6-5

        assertEquals(0, matchScore.getSetScore().getFirstPlayerPoints());
        assertEquals(0, matchScore.getSetScore().getSecondPlayerPoints());
    }
    @Test
    @DisplayName("When first player wins point and games are 6-5 then first player wins set")
    public void shouldWinWhenThereIsPointDifference() {
        for (int i = 0; i < 4; i++) {
            addOneGame(EnumPlayer.FIRST_PLAYER);
        }

        for (int i = 0; i < 5; i++) {
            addOneGame(EnumPlayer.SECOND_PLAYER);
        }
        // games: 4-5

        for (int i = 0; i < 3; i++) {
            addOneGame(EnumPlayer.FIRST_PLAYER);
        }
        // games: 7-5

        assertEquals(1, matchScore.getSetScore().getFirstPlayerPoints());
        assertEquals(0, matchScore.getSetScore().getSecondPlayerPoints());
    }

    @Test
    @DisplayName("There is tiebreak enables when games are 6-6")
    public void tiebreakShouldBeEnabledWhenGamesEqualSix() {
        for (int i = 0; i < 5; i++) {
            addOneGame(EnumPlayer.FIRST_PLAYER);
        }

        for (int i = 0; i < 5; i++) {
            addOneGame(EnumPlayer.SECOND_PLAYER);
        }

        addOneGame(EnumPlayer.FIRST_PLAYER);
        addOneGame(EnumPlayer.SECOND_PLAYER);
        // games: 6-6

        for (int i = 0; i < 6; i++) {
            matchScore.update(EnumPlayer.FIRST_PLAYER);
        }
        // tiebreak: 6-0

        assertEquals(6, matchScore.getCurrentPoints().getFirstPlayerPoints());
        assertEquals(0, matchScore.getCurrentPoints().getSecondPlayerPoints());
    }

    @Test
    @DisplayName("When tiebreak and first player win point and points are 6-5 then first player wins")
    public void winTiebreak() {
        enableTiebreak();

        for (int i = 0; i < 5; i++) {
            matchScore.update(EnumPlayer.SECOND_PLAYER);
        }

        for (int i = 0; i < 7; i++) {
            matchScore.update(EnumPlayer.FIRST_PLAYER);
        }

        assertEquals(1, matchScore.getSetScore().getFirstPlayerPoints());
        assertEquals(0, matchScore.getSetScore().getSecondPlayerPoints());
    }

    @Test
    @DisplayName("When tiebreak and first player win point and points are 7-7 then the game continues")
    public void gameShouldContinuesWhenTiebreakIsSevenSeven() {
        enableTiebreak();

        for (int i = 0; i < 6; i++) {
            matchScore.update(EnumPlayer.SECOND_PLAYER);
        }

        for (int i = 0; i < 7; i++) {
            matchScore.update(EnumPlayer.FIRST_PLAYER);
        }

        matchScore.update(EnumPlayer.SECOND_PLAYER);
        matchScore.update(EnumPlayer.FIRST_PLAYER);

        assertEquals(0, matchScore.getSetScore().getFirstPlayerPoints());
        assertEquals(0, matchScore.getSetScore().getSecondPlayerPoints());
    }

    @Test
    @DisplayName("When tiebreak and first player win point and points are 8-7 then then first player wins")
    public void winTiebreakWhenThereIsPointDifference() {
        enableTiebreak();

        for (int i = 0; i < 6; i++) {
            matchScore.update(EnumPlayer.SECOND_PLAYER);
        }

        for (int i = 0; i < 7; i++) {
            matchScore.update(EnumPlayer.FIRST_PLAYER);
        }

        matchScore.update(EnumPlayer.SECOND_PLAYER);
        for (int i = 0; i < 2; i++) {
            matchScore.update(EnumPlayer.FIRST_PLAYER);
        }

        assertEquals(1, matchScore.getSetScore().getFirstPlayerPoints());
        assertEquals(0, matchScore.getSetScore().getSecondPlayerPoints());
    }

    @Test
    @DisplayName("When the first player wins two sets then he wins the match")
    public void winMatch() {
        addOneSet(EnumPlayer.FIRST_PLAYER);
        addOneSet(EnumPlayer.FIRST_PLAYER);

        assertEquals(EnumPlayer.FIRST_PLAYER, matchScore.getSetScore().getOptionalWinner().get());
    }

    public void enableTiebreak() {
        for (int i = 0; i < 20; i++) {
            matchScore.update(EnumPlayer.FIRST_PLAYER);
        }

        for (int i = 0; i < 20; i++) {
            matchScore.update(EnumPlayer.SECOND_PLAYER);
        }

        addOneGame(EnumPlayer.FIRST_PLAYER);
        addOneGame(EnumPlayer.SECOND_PLAYER);
    }

    private void addOneGame(EnumPlayer pointWinner) {
        for (int i = 0; i < 4; i++) {
            matchScore.update(pointWinner);
        }
    }

    private void addOneSet(EnumPlayer pointWinner) {
        for (int i = 0; i < 6; i++) {
            addOneGame(pointWinner);
        }
    }
}
