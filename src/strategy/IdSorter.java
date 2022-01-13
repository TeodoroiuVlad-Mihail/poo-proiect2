package strategy;

import builder.Child;

import java.util.Comparator;

public class IdSorter implements Comparator<Child> {

    /**
     * compares based off of id, ascending
     */
    @Override
    public int compare(final Child o1, final Child o2) {
        return o1.getId() - o2.getId();
    }
}
