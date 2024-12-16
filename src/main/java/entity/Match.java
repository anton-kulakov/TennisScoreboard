package entity;

import lombok.*;
import model.score.MatchScore;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player1")
    private Player player1;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player2")
    private Player player2;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "winner")
    private Player winner;

    @Transient
    private MatchScore matchScore;

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
