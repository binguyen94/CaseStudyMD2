package views;

import model.User;
import service.FileService;
import service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginView {
    private final String FILE_PATH_CUSTORMER = "D:\\case module 2\\Case-Study-2\\src\\data\\customer.csv";
    private final String FILE_PATH_ADMIN = "D:\\case module 2\\Case-Study-2\\src\\data\\admin.csv";
    private Menu menu;
    private UserService userService;
    private FileService fileService;
    private Scanner scanner;

    public LoginView() {
        menu = new Menu();
        userService = new UserService();
        fileService = new FileService();
        scanner = new Scanner(System.in);
    }
    public void loginAdmin() throws IOException {
        int count = 0;
        do {
            System.out.print("Tài khoản: ");
            String username = scanner.nextLine();
            System.out.print("Mật khẩu: ");
            String password = scanner.nextLine();
            boolean checkInfoLogin = userService.checkLoginAdmin(username, password);
            if (!checkInfoLogin) {
                System.out.println("Tài khoản hoặc mật khẩu vừa nhập không đúng. Xin vui lòng nhập lại!");
                count++;
            } else {
                AdminView adminView = new AdminView();
                adminView.launcher();
            }
        } while (count != 5);
        if (count == 5) {
            System.out.println("Xin lỗi bạn nhập sai quá nhiều lần! Hãy chọn lại tác vụ");
            menu.login();
        }
    }

    public void displayInfoCustomer() throws IOException {
        List<User> users = userService.getCustomerList();
        if (users.isEmpty()) {
            System.out.println("Xin lỗi, Hệ thống không tìm thấy khách hàng nào cả!");
        } else {
            System.out.println("-------");
            System.out.printf("║%7s║%-15s║ %-20s║ %-15s║ %-15s║%-15s║ %-15s║ %-36s║ %-30s║", "ID", "TÀI KHOẢN", "TÊN KHÁCH HÀNG", "SỐ ĐIỆN THOẠI", "GIỚI TÍNH", "CCCD", "SINH NHẬT", "EMAIL", "ĐỊA CHỈ").println();
            System.out.println("------");
            for (User user : users) {
                System.out.printf(user.userView()).println();
            }
            System.out.println("-----");
        }
    }

    private void noChange() {
        System.out.println("Nhập : 0 để quay lại màn hình chính");
    }

    public void loginCusTomer() throws IOException {
        int count = 0;
        do {
            System.out.print("Tài khoản: ");
            String username = scanner.nextLine();
            System.out.print("Mật khẩu: ");
            String pass = scanner.nextLine();
            boolean checkLogin = userService.checkLoginCustomer(username, pass);
            if (!checkLogin) {
                System.out.println("Xin lỗi, tài khoản hoặc mật khẩu bạn nhập chưa chính xác");
                count++;
            } else {
                List<User> userList = new ArrayList<>();
                User user = userService.loginCustomer(username, pass);
                userList.add(user);
                fileService.writeData(FILE_PATH_CUSTORMER, userList);
                CustomerView customerView = new CustomerView();
                customerView.launcher();
            }
        } while (count != 5);
        if (count == 5) {
            System.out.println("Xin lỗi bạn nhập sai quá nhiều lần! Hãy chọn lại tác vụ");
            menu.login();
        }
    }
}
