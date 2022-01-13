package strategy;

import builder.Child;
import common.Constants;

import java.util.Comparator;

public class NiceScoreCitySorter implements Comparator<Child> {

    /**
     * compares based off of city nice score, descending, then lexicographically then by id
     */
    @Override
    public int compare(final Child o1, final Child o2) {
        //see if they have a different average score
        if (o2.getCityScore() - o1.getCityScore() != 0) {
            return (int) ((o2.getCityScore() - o1.getCityScore()) * Constants.TEN);
        }
        if (o2.getCity().compareTo(o1.getCity()) != 0) {
            return o1.getCity().compareTo(o2.getCity());
        } else {
            return o1.getId() - o2.getId();
        }
    }
}
