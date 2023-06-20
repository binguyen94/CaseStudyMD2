package model;

import repository.IModel;
import utils.CurrencyFormat;
import utils.DateFormat;

import java.util.Date;

public class Order implements IModel <Order>{
    private  long idOrder = System.currentTimeMillis();
    private long idCustomer;
    private String nameCustomer;
    private String nameDrink;
    private int quantityDrink;
    private double priceDrink;
    private double totalPrice;
    private Date createDateOrder;
    private EStatus status;

    public Order() {
    }

    public Order(long idOrder, long idCustomer, String nameCustomer, String nameDrink, int quantityDrink, double priceDrink, double totalPrice, Date createDateOrder, EStatus status) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.nameDrink = nameDrink;
        this.quantityDrink = quantityDrink;
        this.priceDrink = priceDrink;
        this.totalPrice = totalPrice;
        this.createDateOrder = createDateOrder;
        this.status = status;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNameDrink() {
        return nameDrink;
    }

    public void setNameDrink(String nameDrink) {
        this.nameDrink = nameDrink;
    }

    public int getQuantityDrink() {
        return quantityDrink;
    }

    public void setQuantityDrink(int quantityDrink) {
        this.quantityDrink = quantityDrink;
    }

    public double getPriceDrink() {
        return priceDrink;
    }

    public void setPriceDrink(double priceDrink) {
        this.priceDrink = priceDrink;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateDateOrder() {
        return createDateOrder;
    }

    public void setCreateDateOrder(Date createDateOrder) {
        this.createDateOrder = createDateOrder;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    @Override
    public long getID() {
        return idOrder;
    }

    @Override
    public String getName() {
        return nameDrink;
    }

    @Override
    public void update(Order obj) {
        this.idOrder = obj.idOrder;
        this.idCustomer = obj.idCustomer;
        this.nameCustomer = obj.nameCustomer;
        this.nameDrink = obj.nameDrink;
        this.quantityDrink = obj.quantityDrink;
        this.priceDrink = obj.priceDrink;
        this.totalPrice = obj.totalPrice;
        this.createDateOrder= obj.createDateOrder;
        this.status = obj.status;
    }

    @Override
    public Order parseData(String line) {

        //
        Order order = new Order();
        String[] strings = line.split(",");
        int idOrder = Integer.parseInt(strings[0]);
        int idCustomer = Integer.parseInt(strings[1]);
        String nameCustomer = strings[2];
        String nameDrink = strings[3];
        int quantityDrink = Integer.parseInt(strings[4]);
        double priceDrink = Double.parseDouble(strings[5]);
        double totalPrice = Double.parseDouble(strings[6]);
        Date createDateOrder = DateFormat.parseDate2(strings[7]);
        EStatus eStatus = EStatus.getStatusByName(strings[8]);
        order.setIdOrder(idOrder);
        order.setIdCustomer(idCustomer);
        order.setNameCustomer(nameCustomer);
        order.setNameDrink(nameDrink);
        order.setQuantityDrink(quantityDrink);
        order.setPriceDrink(priceDrink);
        order.setTotalPrice(totalPrice);
        order.setCreateDateOrder(createDateOrder);
        order.setStatus(eStatus);
        return order;
    }

    public String orderView() {
        return String.format("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", this.idOrder, this.idCustomer, this.nameCustomer, this.nameDrink, this.quantityDrink, CurrencyFormat.convertPriceToString(this.priceDrink), CurrencyFormat.convertPriceToString(this.totalPrice), DateFormat.converDateToString2(this.createDateOrder), this.status.getName());
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", this.idOrder, this.idCustomer, this.nameCustomer, this.nameDrink, this.quantityDrink, CurrencyFormat.parseIntegerPrice(this.priceDrink), CurrencyFormat.parseIntegerPrice(this.totalPrice), DateFormat.converDateToString2(this.createDateOrder), this.status.getName());
    }
}
