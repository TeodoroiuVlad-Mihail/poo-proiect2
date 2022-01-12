package commands;

import fileio.ChildrenInputData;
import builder.Child;
import reading.Children;
import reading.Gift;
import reading.Gifts;

import java.util.ArrayList;

public class AddGifts implements AnnualUpdateCommand {
    private Gifts gifts;
    private Gifts newGifts;

    public AddGifts(final Gifts gifts, final Gifts newGifts) {
        this.gifts = gifts;
        this.newGifts = newGifts;
    }

    /**
     * adds children to the children list
     */
    public void execute() {
        for (int i = 0; i < newGifts.getGifts().size(); i++) {
            Gift newGift = newGifts.getGifts().get(i);
            gifts.getGifts().add(newGift);
        }
    }
}
