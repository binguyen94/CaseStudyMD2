package views;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    public Menu(){
        scanner = new Scanner(System.in);
    }
    public void menuLogin(){
        System.out.println();
        System.out.println("                          ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪  TIỆM TRÀ SỮA TAKE-AWAY CHILLSA  ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪");
        System.out.println("                          ✪                                                                    ✪");
        System.out.println("                          ✪   Vui lòng chọn mục:                                               ✪");
        System.out.println("                          ✪         <Nhấn phím 1> Đăng nhập bằng tài khoản Admin               ✪");
        System.out.println("                          ✪         <Nhấn phím 2> Đăng nhập bằng tài khoản Khách Hàng          ✪");
        System.out.println("                          ✪         <Nhấn phím 3> Thoát                                        ✪");
        System.out.println("                          ✪                                                                    ✪");
        System.out.println("                          ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪ ✪");
    }
    public void login() throws IOException {
        int select;
        boolean checkAction = false;
        do {
            menuLogin();
            System.out.print("Mời lựa chọn: ");
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Xin vui lòng nhập lại lựa chọn!");
                select = 0;
                continue;
            }
            switch (select) {
                case 1:
                    admin();
                    break;
                case 2:
                    customer();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("LỖI! Chọn lại");
                    checkAction = false;
                    break;
            }
        }while (!checkAction);
    }

    public void admin() throws IOException{
        LoginView loginView = new LoginView();
        loginView.loginAdmin();
    }
    public void  customer() throws IOException{
        LoginView loginView = new LoginView();
        loginView.loginCusTomer();
    }
    public void exit(){
        System.out.println();
        System.out.println("                     ★ ★ ★ ★ ★ CHILLSA TẠM BIỆT! HẸN GẶP LẠI NHÉ ! ★ ★ ★ ★ ★   ");
        System.exit(0);
    }
}
