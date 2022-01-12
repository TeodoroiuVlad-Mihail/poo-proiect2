package strategyGiveGifts;

import builder.Child;

import java.util.Comparator;

public class NiceScoreSorter implements Comparator<Child>
{
    @Override
    public int compare(Child o1, Child o2) {
        //see if they have a different average score
        if ( o2.getAverageScore() - o1.getAverageScore() != 0) {
            return (int) (o2.getAverageScore() - o1.getAverageScore()) * 100;
        }
        else return o1.getId() - o2.getId();
        //if not, sort by ascending id
    }
}
