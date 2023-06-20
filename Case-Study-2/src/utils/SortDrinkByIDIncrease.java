package utils;

import model.Drink;

import java.util.Comparator;

public class SortDrinkByIDIncrease implements Comparator<Drink> {

    @Override
    public int compare(Drink o1, Drink o2) {
        return (int) (o1.getIdDrink() - o2.getIdDrink());
    }
}
