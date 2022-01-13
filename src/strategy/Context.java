package strategy;

import reading.Children;
import reading.Gifts;

public class Context {
    private StrategyGiveGifts strategy;

    public Context(final StrategyGiveGifts strategy) {
        this.strategy = strategy;
    }

    /**
     * executor of the strategy
     */
    public void executeStrategy(final Children children, final Gifts gifts) {
        strategy.giveGifts(children, gifts);
    }
}
