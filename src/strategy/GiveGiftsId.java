package strategy;

import common.Constants;
import reading.Children;
import reading.Gift;
import reading.Gifts;

import java.util.ArrayList;
import java.util.List;

public class GiveGiftsId implements StrategyGiveGifts {

    /**
     * Distributes the gifts in the order of the id
     */
    @Override
    public void giveGifts(final Children children, final Gifts gifts) {
        distributeGifts(children, gifts);
    }

    //Because we are going to use this code in other strategies too, we will be making it a method
    static void distributeGifts(final Children children, final Gifts gifts) {
        for (int i = 0; i < children.getChildren().size(); i++) {
            double remainingBudget = children.getChildren().get(i).getAssignedBudget();
            List<Gift> giftsList = new ArrayList<>();
            List<String> giftsPreferences = children.getChildren().get(i).getGiftsPreferences();
            for (int j = 0; j < giftsPreferences.size(); j++) {
                String giftPreference = giftsPreferences.get(j);
                double mostExpensive = Constants.BIGFOFFNUMBER;
                Gift cheapestGift = null; //placeholder, needs to be initialized

                //search for the cheapest gift
                for (int k = 0; k < gifts.getGifts().size(); k++) {
                    double giftPrice = gifts.getGifts().get(k).getPrice();
                    String giftCategory = gifts.getGifts().get(k).getCategory();
                    int giftQuantity = gifts.getGifts().get(k).getQuantity();
                    if (remainingBudget >= giftPrice && giftPreference.compareTo(giftCategory) == 0
                            && giftPrice < mostExpensive && giftQuantity > 0) {
                        mostExpensive = giftPrice;
                        cheapestGift = gifts.getGifts().get(k);
                    }

                }
                if (cheapestGift != null) {
                    giftsList.add(cheapestGift);
                    remainingBudget = remainingBudget - cheapestGift.getPrice();
                    cheapestGift.setQuantity(cheapestGift.getQuantity() - 1);
                }

            }

            if (giftsList.isEmpty()
                    && children.getChildren().get(i).getElf().compareTo("yellow") == 0) {
                String giftPreference = giftsPreferences.get(0);
                double mostExpensive = Constants.BIGFOFFNUMBER;
                Gift cheapestGift = null; //placeholder, needs to be initialized

                //search for the cheapest gift
                for (int k = 0; k < gifts.getGifts().size(); k++) {
                    double giftPrice = gifts.getGifts().get(k).getPrice();
                    String giftCategory = gifts.getGifts().get(k).getCategory();
                    if (giftPreference.compareTo(giftCategory) == 0
                            && giftPrice < mostExpensive) {
                        mostExpensive = giftPrice;
                        cheapestGift = gifts.getGifts().get(k);
                    }
                }
                if (cheapestGift != null && cheapestGift.getQuantity() > 0) {
                    giftsList.add(cheapestGift);
                    cheapestGift.setQuantity(cheapestGift.getQuantity() - 1);
                }

            }
            children.getChildren().get(i).setReceivedGifts(giftsList);
        }
    }
}
