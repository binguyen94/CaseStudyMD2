package views;

import service.FileService;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class AdminView {
    private static final String File_ORDER = "D:\\case module 2\\Case-Study-2\\src\\data\\order.csv";
    private DrinkView drinkView;
    private OrderView orderView;
    private FileService fileService;
    private Scanner scanner;

    public AdminView() {
        drinkView = new DrinkView();
        orderView = new OrderView();
        fileService = new FileService();
        scanner = new Scanner(System.in);
    }

    public AdminView(DrinkView drinkView, OrderView orderView, FileService fileService) {
        this.drinkView = drinkView;
        this.orderView = orderView;
        this.fileService = fileService;
    }

    public void menuAdmin() {
        System.out.println();
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● TRÌNH QUẢN LÝ ADMIN ● ● ● ● ● ● ● ● ● ●");
        System.out.println("                          ●                                                         ●");
        System.out.println("                          ●   Vui lòng chọn mục:                                    ●");
        System.out.println("                          ●         <Nhấn phím 1> Quản lý sản phẩm                  ●");
        System.out.println("                          ●         <Nhấn phím 2> Quản lý đơn hàng                  ●");
        System.out.println("                          ●         <Nhấn phím 3> Doanh thu                         ●");
        System.out.println("                          ●         <Nhấn phím 4> Thoát                             ●");
        System.out.println("                          ●                                                         ●");
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.print("Mời lựa chọn: ");

    }

    public void launcher() throws IOException {
        LoginView loginView = new LoginView();
        int select = 0;
        boolean checkAction = false;
        do {
            menuAdmin();
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Xin vui lòng nhập đúng số tác vụ!");
                select = 0;
                continue;
            }
            switch (select) {
                case 1:
                    drinkView.launcher();
                    checkAction = actionContinue();
                    break;
                case 2:
                    laucherOrder();
                    checkAction = actionContinue();
                    break;
                case 3:
                    displayRevenue();
                    checkAction = actionContinue();
                    break;
                case 4:
                    Menu menu = new Menu();
                    menu.login();
                    checkAction = actionContinue();
                    break;
                default:
                    System.out.println("LỖI! Vui lòng nhập đúng SỐ tác vụ");
                    checkAction = false;
                    break;
            }
        } while (!checkAction);
        if (checkAction) {
            launcher();
        }
    }

    public void displayMenuRevenue() {
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● DOANH THU ● ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.println("                          ●                                                         ●");
        System.out.println("                          ●   Vui lòng chọn mục:                                    ●");
        System.out.println("                          ●         <Nhấn phím 1> Hiển thị doanh thu theo ngày      ●");
        System.out.println("                          ●         <Nhấn phím 2> Hiển thị doanh thu theo tháng     ●");
        System.out.println("                          ●         <Nhấn phím 3> Hiển thị doanh thu trong năm       ●");
        System.out.println("                          ●         <Nhấn phím 4> Quay lại                          ●");
        System.out.println("                          ●                                                         ●");
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.print("Mời nhập lựa chọn: ");

    }

    public void displayRevenue() throws IOException {
        OrderView orderView = new OrderView();
        int select = 0;
        boolean checkAction = false;
        do {
            displayMenuRevenue();
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Xin vui lòng nhập lại tác vụ");
                select = 0;
                continue;
            }
            switch (select) {
                case 1:
                    orderView.displayRevenueByDay();
                    checkAction = actionContinue();
                    break;
                case 2:
                    orderView.displayRevenueByMonth();
                    checkAction = actionContinue();
                    break;
                case 3:
                    orderView.displayTotalRevenue();
                    checkAction = actionContinue();
                    break;
                case 4:
                    launcher();
                    checkAction = actionContinue();
                    break;
                default:
                    System.out.println("LỖI! Vui lòng chọn đúng SỐ tác vụ");
                    checkAction = false;
                    break;
            }
        } while (!checkAction);
        if (checkAction) {
            launcher();

        }
    }

    public void laucherOrder() throws IOException {
        OrderView orderView = new OrderView();
        int select = 0;
        boolean checkAction = false;
        do {
            displayOrderManager();
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Vui lòng nhập lại");
                select = 0;
                continue;
            }
            switch (select) {
                case 1:
                    orderView.displayOrderAll();
                    checkAction = actionContinue();
                    break;
                case 2:
                    orderView.displayOrderUnpaid();
                    checkAction = actionContinue();
                    break;
                case 3:
                    orderView.displayOrderPaid();
                    checkAction = actionContinue();
                    break;
                case 4:
                    launcher();
                    checkAction = actionContinue();
                    break;
                default:
                    System.out.println("LỖI! Vui lòng nhập đúng SỐ tác vụ");
                    checkAction = false;
                    break;
            }
        } while (!checkAction);
        if (checkAction) {
            launcher();
        }
    }

    public void displayOrderManager() {
        System.out.println("                          ● ● ● ● ● ● ● ● ● ●  TRÌNH QUẢN LÝ ĐƠN HÀNG ● ● ● ● ● ● ● ● ● ●");
        System.out.println("                          ●                                                             ●");
        System.out.println("                          ●   Vui lòng chọn mục:                                        ●");
        System.out.println("                          ●         <Nhấn phím 1> Hiển thị toàn bộ các đơn hàng         ●");
        System.out.println("                          ●         <Nhấn phím 2> Hiển thị đơn hàng chưa thanh toán     ●");
        System.out.println("                          ●         <Nhấn phím 3> Hiển thị đơn hàng đã thanh toán       ●");
        System.out.println("                          ●         <Nhấn phím 4> Quay lại                              ●");
        System.out.println("                          ●                                                             ●");
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.print("Mời nhập lựa chọn: ");
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
