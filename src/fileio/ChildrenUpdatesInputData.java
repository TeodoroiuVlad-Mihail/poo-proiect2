package fileio;

import java.util.List;

public class ChildrenUpdatesInputData {
    private int id;
    private double niceScore;
    private List<String> giftsPreferences;
    private String elf;

    public ChildrenUpdatesInputData(final int id, final double niceScore,
                                    final List<String> giftsPreferences, final String elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elf = elf;
    }

    /**
     * getter for the id of the child to be updated
     */
    public int getId() {
        return id;
    }

    /**
     * setter for the id of the child to be updated
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * getter for the nice score to be added to the list
     */
    public double getNiceScore() {
        return niceScore;
    }

    /**
     * setter for the nice score to be added to the list
     */
    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * getter for gift preferences
     */
    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * setter for gift preferences
     */
    public void setGiftsPreferences(final List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * setter for elf
     */
    public String getElf() {
        return elf;
    }

    /**
     * getter for elf
     */
    public void setElf(final String elf) {
        this.elf = elf;
    }

}
