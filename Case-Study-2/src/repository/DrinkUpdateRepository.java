package repository;

import model.Drink;

public class DrinkUpdateRepository extends FileContext<Drink> {
    public DrinkUpdateRepository(){
        filePath = "D:\\case module 2\\Case-Study-2\\src\\data\\drinkUpdate.csv";
        tClass = Drink.class;
    }
}
