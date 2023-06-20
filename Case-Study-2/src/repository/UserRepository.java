package repository;

import model.User;

public class UserRepository extends FileContext<User>{
    public UserRepository(){
        filePath = "D:\\case module 2\\Case-Study-2\\src\\data\\admin.csv";
        tClass = User.class;
    }
}
