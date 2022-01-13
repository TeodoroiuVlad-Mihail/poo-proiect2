package strategy;

import reading.Children;
import reading.Gifts;


public class GiveGiftsNiceScore implements StrategyGiveGifts {

    /**
     * Sorts children by nice score, and then finally distributes the gifts
     * then it doesn't forget to sort the children back to id based sorting
     */
    @Override
    public void giveGifts(final Children children, final Gifts gifts) {

        children.getChildren().sort(new NiceScoreSorter());

        GiveGiftsId.distributeGifts(children, gifts);

        children.getChildren().sort(new IdSorter());

    }
}
