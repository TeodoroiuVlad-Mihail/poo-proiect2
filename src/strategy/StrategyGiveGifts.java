package strategy;

import reading.Children;
import reading.Gifts;

public interface StrategyGiveGifts {

    /**
     * gives gifts based on the strategy chosen
     */
    void giveGifts(Children children, Gifts gifts);

}
