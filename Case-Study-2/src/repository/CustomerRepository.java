package repository;

import model.User;

public class CustomerRepository extends FileContext<User> {
    public CustomerRepository(){
        filePath = "D:\\case module 2\\Case-Study-2\\src\\data\\customer.csv";
        tClass = User.class;
    }
}
