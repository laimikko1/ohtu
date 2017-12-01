package ohtu;

import java.util.ArrayList;
import java.util.List;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    private List<String> scorePrints;
    private int zeroToFortyMappings;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        setUpscorePrints();
        this.zeroToFortyMappings = 3;
    }


    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (evenScore()) {
            if (m_score1 > zeroToFortyMappings) {
                return "Deuce";
            }
            return scorePrints.get(m_score1) + "-All";
        }

        if (fortyPoints()) {
            return calculateAdvantageOrWin();
        }

        return calculateNormalScore();

    }

    private boolean fortyPoints() {
        return m_score1 >= 4 || m_score2 >= 4;
    }

    private boolean evenScore() {
        return m_score1 == m_score2;
    }


    private void setUpscorePrints() {
        this.scorePrints = new ArrayList<>();
        this.scorePrints.add("Love");
        this.scorePrints.add("Fifteen");
        this.scorePrints.add("Thirty");
        this.scorePrints.add("Forty");
    }


    private String calculateAdvantageOrWin() {
        if (m_score1 - m_score2 == -1 || m_score1 - m_score2 == 1) {
            return "Advantage player" + getPlayerNumber(Integer.max(m_score1, m_score2));
        }
        return "Win for player" + getPlayerNumber(Integer.max(m_score1, m_score2));
    }

    private String calculateNormalScore() {
        return scorePrints.get(m_score1) + "-" + scorePrints.get(m_score2);
    }

    private String getPlayerNumber(int max) {
        if (max == m_score1) {
            return "1";
        }
        return "2";
    }
}