package repository;

import model.Drink;

public class DrinkRepository extends FileContext<Drink>{
    public DrinkRepository(){
        filePath = "D:\\case module 2\\Case-Study-2\\src\\data\\drink.csv";
        tClass = Drink.class;
    }
}
