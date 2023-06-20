package utils;

import model.Drink;

import java.util.Comparator;

public class SortDrinkByPriceIncrease implements Comparator<Drink> {

    @Override
    public int compare(Drink o1, Drink o2) {
        if (o1.getPriceDrink() - o2.getPriceDrink() > 0) {
            return 1;
        } else if (o2.getPriceDrink() - o1.getPriceDrink() == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
