package repository;

import model.Order;

public class OrderAllRepository extends FileContext<Order> {
    public OrderAllRepository(){
        filePath = "D:\\case module 2\\Case-Study-2\\src\\data\\orderAll.csv";
        tClass = Order.class;
    }
}
