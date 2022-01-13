package reading;

import fileio.GiftsInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of available gifts
 */

public class Gifts {
    private ArrayList<Gift> gifts = new ArrayList<>();

    public Gifts(final List<GiftsInputData> list) {
        for (GiftsInputData i : list) {
            Gift gift = new Gift(i.getProductName(), i.getPrice(), i.getCategory(),
                    i.getQuantity());
            gifts.add(gift);
        }
    }

    /**
     * get the private gift list
     */
    public ArrayList<Gift> getGifts() {
        return gifts;
    }
}
