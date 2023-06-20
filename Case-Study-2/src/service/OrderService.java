package service;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.Order;
import repository.OrderAllRepository;
import repository.OrderRepository;

import java.io.IOException;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;
    private OrderAllRepository orderAllRepository;

    public OrderService() {
        orderRepository = new OrderRepository();
        orderAllRepository = new OrderAllRepository();
    }

    public List<Order> getAllOrder() throws IOException {
        return orderRepository.getAll();
    }

    public List<Order> getAllOrderAll() throws IOException {
        return orderAllRepository.getAll();
    }

    public long checkIdOrderAll(long id) throws IOException {
        return orderAllRepository.checkID(id);
    }

    public long checkIdOrder(int id) throws IOException {
        return orderRepository.checkID(id);
    }

    public void deleteDrinkFromOrderAllById(long id) throws IOException {
        orderAllRepository.deleteById(id);
    }

    public void deleteDrinkFromOrderById(long id) throws IOException {
        orderRepository.deleteById(id);
    }
}
