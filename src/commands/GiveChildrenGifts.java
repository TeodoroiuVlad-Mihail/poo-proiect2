package commands;

import reading.Children;
import reading.Gifts;
import strategy.Context;
import strategy.GiveGiftsId;
import strategy.GiveGiftsNiceScore;
import strategy.GiveGiftsNiceScoreCity;

/**
 * gives gifts to the children
 */

public class GiveChildrenGifts implements AnnualUpdateCommand {
    private Children children;
    private Gifts gifts;
    private String strategy;

    public GiveChildrenGifts(final Children children, final Gifts gifts, final String strategy) {
        this.children = children;
        this.gifts = gifts;
        this.strategy = strategy;
    }

    /**
     * calculates the budget each kid gets
     */
    public void execute() {
        if (strategy == null || strategy.compareTo("id") == 0) {
            Context context = new Context(new GiveGiftsId());
            context.executeStrategy(children, gifts);
        }
        if (strategy != null && strategy.compareTo("niceScore") == 0) {
            Context context = new Context(new GiveGiftsNiceScore());
            context.executeStrategy(children, gifts);
        }
        if (strategy != null && strategy.compareTo("niceScoreCity") == 0) {
            Context context = new Context(new GiveGiftsNiceScoreCity());
            context.executeStrategy(children, gifts);
        }

    }
}
