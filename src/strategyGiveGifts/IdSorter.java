package strategyGiveGifts;

import builder.Child;

import java.util.Comparator;

public class IdSorter implements Comparator<Child>
{
    @Override
    public int compare(Child o1, Child o2) {
        return o1.getId() - o2.getId();
    }
}
