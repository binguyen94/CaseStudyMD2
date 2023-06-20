package repository;

public interface IModel <T>{
    long getID();
    String getName();
    void  update(T obj);
    T parseData(String line);

}
