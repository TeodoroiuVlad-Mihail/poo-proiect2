package strategyGiveGifts;

import common.Constants;
import reading.Children;
import reading.Gift;
import reading.Gifts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiveGiftsNiceScoreCity implements StrategyGiveGifts{
    @Override
    public void giveGifts(Children children, Gifts gifts) {

        Map<String, Double> citiesScore = new HashMap<>();
        Map<String, Double> citiesTimes =  new HashMap<>();

        //Calculate the scores of the cities
        for (int i = 0; i < children.getChildren().size(); i++) {
            String city = children.getChildren().get(i).getCity();
            double score = children.getChildren().get(i).getAverageScore();
            if (!citiesScore.containsKey(city)) {
                citiesScore.put(city, score);
                citiesTimes.put(city, 1.0);
            }
            else{
                citiesScore.put(city, citiesScore.get(city) + score);
                citiesTimes.put(city, citiesTimes.get(city) + 1);
            }
        }
        for(Map.Entry<String,Double> entry : citiesTimes.entrySet()) {
            String city = entry.getKey();
            Double score = citiesScore.get(city);
            double times = entry.getValue();
            citiesScore.put(city, score / times);
        }

        for (int i = 0; i < children.getChildren().size(); i++) {
            String city = children.getChildren().get(i).getCity();
            double cityScore = citiesScore.get(city);
            children.getChildren().get(i).setCityScore(cityScore);
        }

        children.getChildren().sort(new NiceScoreCitySorter());


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
            children.getChildren().get(i).setReceivedGifts(giftsList);
        }

        children.getChildren().sort(new IdSorter());
    }
}
