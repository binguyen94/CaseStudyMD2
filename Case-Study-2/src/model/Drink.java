package model;

import repository.IModel;
import utils.CurrencyFormat;

public class Drink implements IModel<Drink> {
    private long idDrink = System.currentTimeMillis();
    private String nameDrink;
    private int quantity;
    private Double priceDrink;
    private ECategory eCategory;

    public Drink() {
    }

    public Drink(long idDrink, String nameDrink, int quantity, Double priceDrink, ECategory eCategory) {
        this.idDrink = idDrink;
        this.nameDrink = nameDrink;
        this.quantity = quantity;
        this.priceDrink = priceDrink;
        this.eCategory = eCategory;
    }

    public long getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(long idDrink) {
        this.idDrink = idDrink;
    }

    public String getNameDrink() {
        return nameDrink;
    }

    public void setNameDrink(String nameDrink) {
        this.nameDrink = nameDrink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPriceDrink() {
        return priceDrink;
    }

    public void setPriceDrink(Double priceDrink) {
        this.priceDrink = priceDrink;
    }

    public ECategory geteCategory() {
        return eCategory;
    }

    public void seteCategory(ECategory eCategory) {
        this.eCategory = eCategory;
    }

    @Override
    public long getID() {
        return idDrink;
    }

    @Override
    public String getName() {
        return nameDrink;
    }

    @Override
    public void update(Drink obj) {

    }

    @Override
    public Drink parseData(String line) {
        Drink drink = new Drink();
        String[] strings = line.split(",");
        int id = Integer.parseInt(strings[0]);
        String name = strings[1];
        int quantity = Integer.parseInt(strings[2]);
        Double price = CurrencyFormat.parseDoublePrice(strings[3]);
        ECategory category = ECategory.getCategoryByName(strings[4]);
        drink.setIdDrink(id);
        drink.setNameDrink(name);
        drink.setQuantity(quantity);
        drink.setPriceDrink(price);
        drink.seteCategory(category);
        return drink;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", getIdDrink(), getNameDrink(), getQuantity(), CurrencyFormat.parseIntegerPrice(this.priceDrink), this.eCategory.getName());
    }

    public String drinkView() {
        return String.format("%10s ║ %-30s ║ %-10s ║ %15s ║ %20s", getIdDrink(), getNameDrink(), getQuantity(), CurrencyFormat.convertPriceToString(this.priceDrink), this.eCategory.getName());
    }
}
