package strategyGiveGifts;

import builder.Child;

import java.util.Comparator;

public class NiceScoreCitySorter implements Comparator<Child>
{
    @Override
    public int compare(Child o1, Child o2) {
        //see if they have a different average score
        if ( o2.getCityScore() - o1.getCityScore() != 0) {
            return (int) (o2.getCityScore() - o1.getCityScore()) * 100;
        }
        else return o1.getId() - o2.getId();
        //if not, sort by ascending id
    }
}
