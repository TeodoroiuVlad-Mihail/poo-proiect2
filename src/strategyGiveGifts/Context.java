package strategyGiveGifts;

import reading.Children;
import reading.Gifts;

public class Context {
    private StrategyGiveGifts strategy;

    public Context(StrategyGiveGifts strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(Children children, Gifts gifts){
        strategy.giveGifts(children, gifts);
    }
}