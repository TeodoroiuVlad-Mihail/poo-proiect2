package commands;

import reading.Gift;
import reading.Gifts;

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
