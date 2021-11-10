package game.scores;


import java.io.Serializable;

/**
 * this class responsible of score information.
 * @author Dvir Asaf 313531113.
 */
public class ScoreInfo implements Serializable, Comparable<ScoreInfo> {
    private String name;
    private int score;
    /**
     * constructor - for this class.
     *
     * @param name  a given name.
     * @param score a given score.
     */
    public ScoreInfo(final String name, final int score) {
        this.name = name;
        this.score = score;
    }
    @Override
    public String toString() {
        return "game.data.scores.ScoreInfo{"
                + "name='" + name + '\''
                + ", score=" + score
                + '}';
    }
    /**
     * @return the class parameter name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Gets score.
     *
     * @return the class parameter score.
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(final ScoreInfo score) {
        return Integer.compare(score.getScore(), this.getScore());
    }
}

