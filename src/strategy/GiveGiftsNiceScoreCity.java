package strategy;

import reading.Children;
import reading.Gifts;

import java.util.HashMap;
import java.util.Map;

public class GiveGiftsNiceScoreCity implements StrategyGiveGifts {

    /**
     * first it calculates the average score of each city, then attaches it to the hidden
     * city score of each child, then sorts them by city, and then finally distributes the gifts
     * then it doesn't forget to sort the children back to id based sorting
     */
    @Override
    public void giveGifts(final Children children, final Gifts gifts) {

        Map<String, Double> citiesScore = new HashMap<>();
        Map<String, Double> citiesTimes = new HashMap<>();

        //Calculate the scores of the cities
        for (int i = 0; i < children.getChildren().size(); i++) {
            String city = children.getChildren().get(i).getCity();
            double score = children.getChildren().get(i).getAverageScore();
            if (!citiesScore.containsKey(city)) {
                citiesScore.put(city, score);
                citiesTimes.put(city, 1.0);
            } else {
                citiesScore.put(city, citiesScore.get(city) + score);
                citiesTimes.put(city, citiesTimes.get(city) + 1);
            }
        }
        for (Map.Entry<String, Double> entry : citiesTimes.entrySet()) {
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

        GiveGiftsId.distributeGifts(children, gifts);

        children.getChildren().sort(new IdSorter());

    }
}
