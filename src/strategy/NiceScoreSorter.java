package strategy;

import builder.Child;
import common.Constants;

import java.util.Comparator;

public class NiceScoreSorter implements Comparator<Child> {
    /**
     * compares based off of nice score, descending
     */
    @Override
    public int compare(final Child o1, final Child o2) {
        //see if they have a different average score
        if (o2.getAverageScore() - o1.getAverageScore() != 0) {
            return (int) ((o2.getAverageScore() - o1.getAverageScore()) * Constants.TEN);
        } else {
            return o1.getId() - o2.getId();
        }
        //if not, sort by ascending id
    }
}
