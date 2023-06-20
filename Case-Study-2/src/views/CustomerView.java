package views;

import service.FileService;
import service.OrderService;
import service.UserService;

import java.io.IOException;
import java.util.Scanner;

public class CustomerView {
    private static final String FILE_PATH_ORDER = "D:\\case module 2\\Case-Study-2\\src\\data\\order.csv";
    private final String FILE_PATH_CUSTOMER = "D:\\case module 2\\Case-Study-2\\src\\data\\customer.csv";
    private UserService userService = new UserService();
    private OrderView orderView = new OrderView();
    private DrinkView drinkView = new DrinkView();
    private Scanner scanner = new Scanner(System.in);
    private FileService fileService = new FileService();
    private LoginView loginView = new LoginView();

    public CustomerView() {
    }

    public CustomerView(UserService userService, OrderView orderView, DrinkView drinkView, Scanner scanner, FileService fileService, LoginView loginView) {
        this.userService = userService;
        this.orderView = orderView;
        this.drinkView = drinkView;
        this.scanner = scanner;
        this.fileService = fileService;
        this.loginView = loginView;
    }

    public void menuCustomer() {
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● ỨNG DỤNG KHÁCH HÀNG ● ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.println("                          ●                                                                   ●");
        System.out.println("                          ●   Vui lòng chọn mục:                                              ●");
        System.out.println("                          ●         <Nhấn phím 1> Hiển thị toàn bộ thức uống                  ●");
        System.out.println("                          ●         <Nhấn phím 2> Xem danh sách thức uống theo từng loại      ●");
        System.out.println("                          ●         <Nhấn phím 3> Cho biết thức uống có thứ tự giá tăng dần   ●");
        System.out.println("                          ●         <Nhấn phím 4> Cho biết thức uống có thứ tự giá giảm dần   ●");
        System.out.println("                          ●         <Nhấn phím 5> Tìm kiếm thức uống theo keyword             ●");
        System.out.println("                          ●         <Nhấn phím 6> Đặt thức uống theo ID                       ●");
        System.out.println("                          ●         <Nhấn phím 7> Thanh toán                                  ●");
        System.out.println("                          ●         <Nhấn phím 8> Thoát                                       ●");
        System.out.println("                          ●                                                                   ●");
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.print("Mời lựa chọn: ");
    }

    public void launcher() throws IOException {
        int select = 0;
        boolean checkAction = false;
        do {
            menuCustomer();
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Xin vui lòng nhập lại");
                select = 0;
                continue;
            }
            switch (select) {
                case 1:
                    drinkView.displayDrinkList();
                    checkAction = actionContinue();
                    break;
                case 2:
                    drinkView.displayDrinkListByType();
                    checkAction = actionContinue();
                    break;
                case 3:
                    drinkView.sortPriceByIncrease();
                    checkAction = actionContinue();
                    break;
                case 4:
                    drinkView.sortPriceByDecrease();
                    checkAction = actionContinue();
                    break;
                case 5:
                    drinkView.findDrinkByKeyword();
                    checkAction = actionContinue();
                    break;
                case 6:
                    orderView.addDrinkToOrderByIDCustomer();
                    checkAction = actionContinue();
                    break;
                case 7:
                    orderView.payOrder();
                    checkAction = actionContinue();
                    break;
                case 8:
                    fileService.clearData(FILE_PATH_CUSTOMER);
                    Menu menu = new Menu();
                    menu.login();
                    break;
                default:
                    System.out.println("LỖI! Xin vui lòng nhập đúng SỐ tác vụ");
                    checkAction = false;
                    break;
            }
        } while (!checkAction);
        if (checkAction) {
            launcher();
        }
    }


    public boolean actionContinue() {
        boolean actionContinue = false;
        do {
            System.out.println("Nhập phím y để quay lại, nhập n để về menu chính");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "y":
                    return false;
                case "n":
                    return true;
                default:
                    System.out.println("LỖI! Bạn vừa nhập không đúng lựa chọn");
                    actionContinue = false;
            }
        } while (!actionContinue);
        return true;
    }
}
