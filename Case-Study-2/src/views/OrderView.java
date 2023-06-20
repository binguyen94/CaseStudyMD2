package views;

import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.Drink;
import model.EStatus;
import model.Order;
import model.User;
import service.DrinkService;
import service.FileService;
import service.OrderService;
import service.UserService;
import sun.security.util.PolicyUtil;
import utils.CurrencyFormat;
import utils.DateFormat;
import utils.SortOrderById;
import utils.ValidateUtils;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private static final String FILE_PATH_DRINK = "D:\\case module 2\\Case-Study-2\\src\\data\\drink.csv";
    private final String FILE_PATH_ORDER = "D:\\case module 2\\Case-Study-2\\src\\data\\order.csv";
    private final String FILE_PATH_ORDERALL = "D:\\case module 2\\Case-Study-2\\src\\data\\orderAll.csv";
    private DrinkService drinkService = new DrinkService();
    private UserService userService = new UserService();
    private OrderService orderService = new OrderService();
    private FileService fileService = new FileService();
    private Scanner scanner = new Scanner(System.in);

    public OrderView() {
    }

    public OrderView(DrinkService drinkService, UserService userService, OrderService orderService, FileService fileService, Scanner scanner) {
        this.drinkService = drinkService;
        this.userService = userService;
        this.orderService = orderService;
        this.fileService = fileService;
        this.scanner = scanner;
    }

    public void addDrinkToOrderByIDCustomer() throws IOException {
        CustomerView customerView = new CustomerView();
        DrinkView drinkView = new DrinkView();
        drinkView.displayDrinkList();
        List<Drink> drinks = drinkService.getAllDrink();
        List<User> users = userService.getAllCustomers();
        List<Order> orderAll = orderService.getAllOrderAll();
        List<Order> orders = orderService.getAllOrder();
        orderAll.sort(new SortOrderById());
        Order order = new Order();
        Order orderNew = new Order();
        int idDrink = 0;
        String nameDrink = null;
        boolean checkID = false;
        do {
            doBack();
            boolean checkAction = false;
            System.out.println("Vui lòng cung cấp ID mà thức uống mà bạn muốn order:");
            String inputID = scanner.nextLine();
            if (inputID.equals("0")) {
                checkID = true;
                customerView.launcher();
            }
            if (ValidateUtils.isId(idDrink, inputID)) {
                idDrink = Integer.parseInt(inputID);
            } else {
                continue;
            }
            int checkIDDrink = (int) drinkService.checkIdDrink(idDrink);
            switch (checkIDDrink) {
                case 1:
                    for (int i = 0; i < drinks.size(); i++) {
                        if (drinks.get(i).getIdDrink() == idDrink) {
                            nameDrink = drinks.get(i).getNameDrink();
                        }
                    }
                    if (!orders.isEmpty()) {
                        int index = 0;
                        boolean checkNameDrinkAvailable = false;
                        for (int i = 0; i < orders.size(); i++) {
                            if (orders.get(i).getNameDrink().equals(nameDrink) && users.get(0).getID() == orders.get(i).getIdCustomer()) {
                                index = i;
                                checkNameDrinkAvailable = true;
                            }
                        }
                        if (checkNameDrinkAvailable) {
                            doBack();
                            System.out.println("Thức uống đã được đặt");
                            int quantity = 0;
                            boolean checkValid = false;
                            boolean checkQuantity = false;
                            do {
                                System.out.println("Số lượng : ");
                                String inputQuantity = scanner.nextLine();
                                checkValid = ValidateUtils.isQuantity(quantity, inputQuantity);
                                if (!checkValid) {
                                    checkQuantity = false;
                                } else {
                                    quantity = Integer.parseInt(inputQuantity);
                                    for (int j = 0; j < drinks.size(); j++) {
                                        if (drinks.get(j).getNameDrink().equals(nameDrink)) {
                                            if (quantity <= drinks.get(j).getQuantity()) {
                                                checkQuantity = true;
                                            } else {
                                                System.out.println("LỖI! Vui lòng nhập số lượng không vượt quá " + drinks.get(j).getQuantity());
                                                checkQuantity = false;
                                            }
                                        }
                                    }
                                }
                            } while (!checkQuantity);
                            int quantityNew = orders.get(index).getQuantityDrink() + quantity;
                            double total = orders.get(index).getPriceDrink() * quantityNew;
                            order.setIdOrder(orders.get(index).getIdOrder());
                            order.setIdCustomer(orders.get(index).getIdCustomer());
                            order.setNameCustomer(orders.get(index).getNameCustomer());
                            order.setNameDrink(nameDrink);
                            order.setQuantityDrink(quantityNew);
                            order.setPriceDrink(orders.get(index).getPriceDrink());
                            order.setTotalPrice(total);
                            order.setCreateDateOrder(new Date());
                            order.setStatus(orders.get(index).getStatus());
                            orders.set(index, order);
                            for (int j = 0; j < drinks.size(); j++) {
                                if (drinks.get(j).getNameDrink().equals(nameDrink)) {
                                    drinks.get(j).setQuantity(drinks.get(j).getQuantity() - quantity);
                                }
                            }
                            fileService.writeData(FILE_PATH_ORDER, orders);
                            fileService.writeData(FILE_PATH_DRINK, drinks);
                            for (int j = 0; j < orderAll.size(); j++) {
                                if (orderAll.get(j).getIdCustomer() == users.get(0).getId() && orderAll.get(j).getNameDrink().equals(nameDrink) && orderAll.get(j).getStatus().equals(EStatus.UNPAID)) {
                                    orderAll.get(j).setQuantityDrink(quantityNew);
                                    fileService.writeData(FILE_PATH_ORDERALL, orderAll);
                                }
                            }
                        } else {
                            int quantity = 0;
                            boolean checkValid = false;
                            boolean checkQuantity = false;
                            do {
                                System.out.println("Số lượng: ");
                                String inputQuantity = scanner.nextLine();
                                if (inputQuantity.equals("0")) {
                                    checkID = true;
                                    customerView.launcher();
                                }
                                checkValid = ValidateUtils.isQuantity(quantity, inputQuantity);
                                if (!checkValid) {
                                    checkQuantity = false;
                                } else {
                                    quantity = Integer.parseInt(inputQuantity);
                                    for (int j = 0; j < drinks.size(); j++) {
                                        if (drinks.get(j).getIdDrink() == idDrink) {
                                            if (quantity <= drinks.get(j).getQuantity()) {
                                                checkQuantity = true;
                                            } else {
                                                System.out.println("LỖI! Vui lòng nhập số lượng không vượt quá " + drinks.get(j).getQuantity());
                                                checkQuantity = false;
                                            }
                                        }
                                    }
                                }
                            } while (!checkQuantity);
                            double price = 0;
                            for (int j = 0; j < drinks.size(); j++) {
                                if (drinks.get(j).getIdDrink() == idDrink) {
                                    price = drinks.get(j).getPriceDrink();
                                    drinks.get(j).setQuantity(drinks.get(j).getQuantity() - quantity);
                                }
                            }
                            double totalMoney = quantity * price;
                            order.setIdOrder(1);
                            order.setIdCustomer(users.get(0).getId());
                            order.setNameCustomer(users.get(0).getFullname());
                            order.setNameDrink(nameDrink);
                            order.setQuantityDrink(quantity);
                            order.setPriceDrink(price);
                            order.setTotalPrice(totalMoney);
                            order.setCreateDateOrder(new Date());
                            order.setStatus(EStatus.UNPAID);
                            orders.add(order);
                            fileService.writeData(FILE_PATH_ORDER, orders);
                            fileService.writeData(FILE_PATH_DRINK, drinks);
                        }
                    } else if (orders.isEmpty()) {
                        int quantity = 0;
                        boolean checkValid = false;
                        boolean checkQuantity = false;
                        do {
                            System.out.println("Số lượng: ");
                            String inputQuantity = scanner.nextLine();
                            if (inputQuantity.equals("exit")) {
                                checkID = true;
                                customerView.launcher();
                            }
                            checkValid = ValidateUtils.isQuantity(quantity, inputQuantity);
                            if (!checkValid) {
                                checkQuantity = false;
                            } else {
                                quantity = Integer.parseInt(inputQuantity);
                                for (int j = 0; j < drinks.size(); j++) {
                                    if (drinks.get(j).getIdDrink() == idDrink) {
                                        if (quantity <= drinks.get(j).getQuantity()) {
                                            checkQuantity = true;
                                        } else {
                                            System.out.println("LỖI! Vui lòng nhập số lượng không vượt quá " + drinks.get(j).getQuantity());
                                            checkQuantity = false;
                                        }
                                    }
                                }
                            }
                        } while (!checkQuantity);
                        double price = 0;
                        for (int j = 0; j < drinks.size(); j++) {
                            if (drinks.get(j).getIdDrink() == idDrink) {
                                price = drinks.get(j).getPriceDrink();
                                drinks.get(j).setQuantity(drinks.get(j).getQuantity() - quantity);
                            }
                        }
                        double totalMoney = quantity * price;
                        order.setIdOrder(1);
                        order.setIdCustomer(users.get(0).getId());
                        order.setNameCustomer(users.get(0).getFullname());
                        order.setNameDrink(nameDrink);
                        order.setQuantityDrink(quantity);
                        order.setPriceDrink(price);
                        order.setTotalPrice(totalMoney);
                        order.setCreateDateOrder(new Date());
                        order.setStatus(EStatus.UNPAID);
                        orders.add(order);
                        fileService.writeData(FILE_PATH_ORDER, orders);
                        fileService.writeData(FILE_PATH_DRINK, drinks);
                    }
                    checkID = true;
                    break;
                case -1:
                    System.out.println("Vui lòng nhập lại! Hệ thống không tìm thấy ID mà bạn vừa nhập");
                    checkID = false;
                    break;
            }
        } while (!checkID);
        long id = 1;
        if(orderAll.size() != 0){
            id = orderAll.get(orderAll.size() - 1).getIdOrder() + 1;
        }
        orderNew.setIdOrder(id);
        orderNew.setIdCustomer(order.getIdCustomer());
        orderNew.setNameCustomer(order.getNameCustomer());
        orderNew.setNameDrink(order.getNameDrink());
        orderNew.setQuantityDrink(order.getQuantityDrink());
        orderNew.setPriceDrink(order.getPriceDrink());
        orderNew.setTotalPrice(order.getTotalPrice());
        orderNew.setCreateDateOrder(order.getCreateDateOrder());
        orderNew.setStatus(order.getStatus());
        orderAll.add(orderNew);
        fileService.writeData(FILE_PATH_ORDERALL, orderAll);
        displayOrderNow();
        System.out.println("Món của bạn đã được thêm!");
    }

    public void displayOrderNow() throws IOException {
        List<Order> orders = orderService.getAllOrder();
        List<User> users = userService.getAllCustomers();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn Hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getIdCustomer() == users.get(0).getId()) {
                System.out.printf(orders.get(i).orderView()).println();
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayOrderAll() throws IOException {
        List<Order> orderList = orderService.getAllOrderAll();
        if (orderList.isEmpty()) {
            System.out.println("Xin lỗi, bạn chưa có đơn hàng nào cả!");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < orderList.size(); i++) {
                System.out.printf(orderList.get(i).orderView()).println();
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void displayOrderUnpaid() throws IOException {
        List<Order> orderList = orderService.getAllOrderAll();
        if (orderList.isEmpty()) {
            System.out.println("Xin lỗi, bạn chưa có đơn hàng nào chưa thanh toán cả");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getStatus().equals(EStatus.UNPAID)) {
                    System.out.printf(orderList.get(i).orderView()).println();
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void displayOrderPaid() throws IOException {
        List<Order> orderList = orderService.getAllOrderAll();
        if (orderList.isEmpty()) {
            System.out.println("Xin lỗi, bạn chưa có đơn hàng nào đã thanh toán cả");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getStatus().equals(EStatus.PAID)) {
                    System.out.printf(orderList.get(i).orderView()).println();
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void doBack() {
        System.out.println("Nhập 0 để hủy tác vụ và quay lại");
    }

    public void displayRevenueByDay() throws IOException {
        AdminView adminView = new AdminView();
        List<Order> orderList = orderService.getAllOrderAll();
        if (orderList.isEmpty()) {
            System.out.println("Xin lỗi, mục này trống");
        } else {
            String date = null;
            boolean checkDate = false;
            do {
                System.out.println("Vui lòng nhập ngày-tháng-năm để xem:");
                date = scanner.nextLine();
                checkDate = ValidateUtils.isDay(date);
                if (!checkDate) {
                    System.out.println("LỖI! Vui lòng nhập lại (cú pháp : dd-MM-yyyy)");
                }
            } while (!checkDate);
            int count = 0;
            for (int i = 0; i < orderList.size(); i++) {
                if (DateFormat.converDateToString2(orderList.get(i).getCreateDateOrder()).contains(date) && orderList.get(i).getStatus().equals(EStatus.PAID)) {
                    count += 1;
                }
            }
            if (count == 0) {
                System.out.println("Xin lỗi, mục này trống");
            } else {
                double totalRevenueByDay = 0;
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < orderList.size(); i++) {
                    if (DateFormat.converDateToString2(orderList.get(i).getCreateDateOrder()).contains(date) && orderList.get(i).getStatus().equals(EStatus.PAID)) {
                        totalRevenueByDay += orderList.get(i).getTotalPrice();
                        System.out.printf(orderList.get(i).orderView()).println();
                    }
                }
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("║TỔNG DOANH THU THEO NGÀY ║ %-13s    ", CurrencyFormat.convertPriceToString(totalRevenueByDay)).println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void displayRevenueByMonth() throws IOException {
        AdminView adminView = new AdminView();
        List<Order> orderList = orderService.getAllOrderAll();
        if (orderList.isEmpty()) {
            System.out.println("Xin lỗi, mục này trống");
        } else {
            String month = null;
            boolean checkMonth = false;
            do {
                System.out.println("Vui lòng nhập tháng để xem:");
                month = scanner.nextLine();
                checkMonth = ValidateUtils.isMonth(month);
                if (!checkMonth) {
                    System.out.println("LỖI! Vui lòng nhập lại (cú pháp : MM-yyyy)");
                }
            } while (!checkMonth);
            int count = 0;
            for (int i = 0; i < orderList.size(); i++) {
                if (DateFormat.converDateToString2(orderList.get(i).getCreateDateOrder()).contains(month) && orderList.get(i).getStatus().equals(EStatus.PAID)) {
                    count += 1;
                }
            }
            if (count == 0) {
                System.out.println("Xin lỗi, mục này trống");
            } else {
                double totalRevenueByMonth = 0;
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < orderList.size(); i++) {
                    if (DateFormat.converDateToString2(orderList.get(i).getCreateDateOrder()).contains(month) && orderList.get(i).getStatus().equals(EStatus.PAID)) {
                        totalRevenueByMonth += orderList.get(i).getTotalPrice();
                        System.out.printf(orderList.get(i).orderView()).println();
                    }
                }
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("║TỔNG DOANH THU THEO THÁNG ║ %-13s    ", CurrencyFormat.convertPriceToString(totalRevenueByMonth)).println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void displayTotalRevenue() throws IOException {
        List<Order> orderList = orderService.getAllOrderAll();
        if (orderList.isEmpty()) {
            System.out.println("Xin lỗi, mục này trống");
        } else {
            double totalRevenue = 0;
            for (int i = 0; i < orderList.size(); i++) {
                totalRevenue += orderList.get(i).getTotalPrice();
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getStatus().equals(EStatus.PAID)) {
                    System.out.printf(orderList.get(i).orderView()).println();
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("║  TỔNG DOANH THU  ║ %-13s   ", CurrencyFormat.convertPriceToString(totalRevenue)).println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

    }

    public void payOrder() throws IOException {
        FileService fileService = new FileService();
        CustomerView customerView = new CustomerView();
        List<Order> orders = orderService.getAllOrder();
        List<Order> ordersNew = new ArrayList<>();
        List<Order> orderAll = orderService.getAllOrderAll();
        List<User> users = userService.getAllCustomers();
        double totalPrice = 0;
        int count = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getIdCustomer() == users.get(0).getId()) {
                count += 1;
            }
        }
        if(count == 0){
            System.out.println("Bạn chưa thực hiện đặt món, bạn có muốn đặt món?");
            boolean checkEdit = false;
            do{
                System.out.println("Nhập y để đặt món hoặc n để quay lại");
                String input = scanner.nextLine();
                switch (input.toLowerCase()){
                    case "y":
                        checkEdit = true;
                        addDrinkToOrderByIDCustomer();
                        break;
                    case "n":
                        checkEdit = true;
                        customerView.launcher();
                        break;
                    default:
                        checkEdit = false;
                        break;
                }
            }while (!checkEdit);
        }else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("║ %-15s║ %-15s║ %-30s║ %-30s║ %-15s║ %-15s║ %-14s║ %-30s║ %-15s║", "ID Đơn hàng", "ID Khách hàng", "TÊN KHÁCH HÀNG", "TÊN MÓN", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN", "NGÀY ĐẶT HÀNG", "TRẠNG THÁI").println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for(int i = 0; i< orders.size();i++){
                if(orders.get(i).getIdCustomer() == users.get(0).getId()){
                    totalPrice += orders.get(i).getTotalPrice();
                    orders.get(i).setStatus(EStatus.PAID);
                    System.out.printf(orders.get(i).orderView()).println();
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("║  TỔNG TIỀN   ║ %-13s ║   ", CurrencyFormat.convertPriceToString(totalPrice)).println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for(int i =0; i< orderAll.size();i++){
                if(orderAll.get(i).getIdCustomer() == users.get(0).getId()){
                    orderAll.get(i).setStatus(EStatus.PAID);
                }
            }
            for (int i =0;i<orders.size();i++){
                if(orders.get(i).getIdCustomer()!= users.get(0).getId()){
                    ordersNew.add(orders.get(i));
                }
            }
        }
        fileService.writeData(FILE_PATH_ORDER,ordersNew);
        fileService.writeData(FILE_PATH_ORDERALL,orderAll);
        System.out.println("Cám ơn, Thao tác thanh toán của bạn hoàn tất");
    }
}



