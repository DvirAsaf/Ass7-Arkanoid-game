
package game.scores;


import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this class is responsible of the high scores table. read from files and write to files.
 * @author Dvir Asaf 313531113.
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> scores;
    private int maxSize;
    /**
     * constructor - for this class.
     *
     * @param size the size
     */
    public HighScoresTable(final int size) {
        this.maxSize = size;
        this.scores = new ArrayList<>();
    }
    /**
     * this method load table info from the file. if can not find or open the file return empty table.
     *
     * @param file the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(final File file) {
        ObjectInputStream objectInput = null;
        HighScoresTable highScores = new HighScoresTable(0);
        try {
            objectInput = new ObjectInputStream(new FileInputStream(file.getName()));
            highScores = (HighScoresTable) (objectInput.readObject());
            highScores.save(file);
            return highScores;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + file.getName());
            return highScores;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: " + file.getName());
            return highScores;
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return highScores;
        } finally {
            try {
                if (objectInput != null) {
                    objectInput.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + file.getName());
            }
        }
    }
    /**
     * this method load table info from the file.
     *
     * @param file a give file.
     * @throws IOException the io exception
     */
    public void load(final File file) throws IOException {
        try {
            this.clear();
            this.scores = loadFromFile(file).getHighScores();
            Collections.sort(this.scores);
            while (this.scores.size() > this.maxSize) {
                this.scores.remove(this.scores.size() - 1);
            }
            this.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * this method add a given score info to list a check if is greater than the the score inside the list.
     *
     * @param score a given score info.
     */
    public void add(final ScoreInfo score) {
        this.scores.add(score);
        Collections.sort(this.scores);
        //if after adding the array is greater than the max getSize, so last entry should be removed.
        if (this.scores.size() > this.maxSize) {
            //remove last entry
            this.scores.remove(this.scores.size() - 1);
        }
    }

    /**
     * @return the size of the high scores.
     */
    public int getSize() {
        return this.getHighScores().size();
    }
    /**
     * @return the high scores from the list.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }
    /**
     * this method save the info from the file.
     *
     * @param file a given file.
     * @throws IOException the io exception
     */
    public void save(final File file) throws IOException {
        ObjectOutputStream objectOutput = null;
        try {
            objectOutput = new ObjectOutputStream(new FileOutputStream(file.getName()));
            objectOutput.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutput != null) {
                objectOutput.close();
            }
        }
    }
    /**
     * @return the max size.
     */
    public int getMaxSize() {
        return maxSize;
    }
    /**
     * this method clear the table.
     */
    public void clear() {
        this.scores.clear();
    }
}

