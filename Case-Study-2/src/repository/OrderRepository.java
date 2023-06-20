package repository;

import model.Order;

public class OrderRepository extends FileContext<Order>{
    public OrderRepository(){
        filePath = "D:\\case module 2\\Case-Study-2\\src\\data\\order.csv";
        tClass = Order.class;
    }
}
