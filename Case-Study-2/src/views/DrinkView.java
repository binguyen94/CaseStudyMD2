package views;

import model.Drink;
import model.ECategory;
import service.DrinkService;
import service.FileService;
import utils.SortDrinkByIDIncrease;
import utils.SortDrinkByPriceIncrease;
import utils.SortDrinksByPriceDecrease;
import utils.ValidateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static utils.ValidateUtils.getDoubleWithBoundary;
import static utils.ValidateUtils.getNumberWithBoundary;

public class DrinkView {
    private final String FILE_PATH_DRINK = "D:\\case module 2\\Case-Study-2\\src\\data\\drink.csv";
    private final String FILE_PATH_DRINK_UPDATE = "D:\\case module 2\\Case-Study-2\\src\\data\\drinkUpdate.csv";
    private FileService fileService;
    private DrinkService drinkService;
    private Scanner scanner;

    public DrinkView() {
        fileService = new FileService();
        drinkService = new DrinkService();
        scanner = new Scanner(System.in);
    }

    public DrinkView(FileService fileService, DrinkService drinkService, Scanner scanner) {
        this.fileService = fileService;
        this.drinkService = drinkService;
        this.scanner = scanner;
    }

    public void menuDrink() {
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● MENU QUẢN LÝ SẢN PHẨM ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.println("                          ●                                                                   ●");
        System.out.println("                          ●   Vui lòng chọn mục:                                              ●");
        System.out.println("                          ●         <Nhấn phím 1> Xem danh sách tất cả thức uống              ●");
        System.out.println("                          ●         <Nhấn phím 2> Xem danh sách thức uống theo từng loại      ●");
        System.out.println("                          ●         <Nhấn phím 3> Cho biết thức uống có thứ tự giá tăng dần   ●");
        System.out.println("                          ●         <Nhấn phím 4> Cho biết thức uống có thứ tự giá giảm dần   ●");
        System.out.println("                          ●         <Nhấn phím 5> Thêm thức uống vào Menu                     ●");
        System.out.println("                          ●         <Nhấn phím 6> Sửa thức uống theo ID                       ●");
        System.out.println("                          ●         <Nhấn phím 7> Xóa kiếm thức uống theo ID                  ●");
        System.out.println("                          ●         <Nhấn phím 8> Tìm thức uống theo ID                       ●");
        System.out.println("                          ●         <Nhấn phím 9> Quay lại                                    ●");
        System.out.println("                          ●                                                                   ●");
        System.out.println("                          ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ● ●");
        System.out.print("Mời lựa chọn: ");
    }

    public void launcher() throws IOException {
        boolean checkAction = false;
        int select = 0;
        do {
            menuDrink();
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Nhập không đúng, xin vui lòng nhập lại đúng SỐ đại diện tính năng");
                select = 0;
                continue;
            }
            switch (select) {
                case 1:
                    displayDrinkList();
                    checkAction = actionContinue();
                    break;
                case 2:
                    displayDrinkListByType();
                    checkAction = actionContinue();
                    break;
                case 3:
                    sortPriceByIncrease();
                    checkAction = actionContinue();
                    break;
                case 4:
                    sortPriceByDecrease();
                    checkAction = actionContinue();
                    break;
                case 5:
                    addDrink();
                    checkAction = actionContinue();
                    break;
                case 6:
                    editDrinkByID();
                    checkAction = actionContinue();
                    break;
                case 7:
                    deleteDrinkByID();
                    checkAction = actionContinue();
                    break;
                case 8:
                    finDrinksByID();
                    checkAction = actionContinue();
                    break;
                case 9:
                    AdminView adminView = new AdminView();
                    adminView.launcher();
                    checkAction = actionContinue();
                    break;
                default:
                    System.out.println("LỖI! Xin vui lòng nhập đúng SỐ tác vụ");
                    checkAction = false;
                    break;
            }
        } while (!checkAction);
        if (checkAction) {
            AdminView adminView = new AdminView();
            adminView.launcher();
        }
    }

    public void finDrinksByID() throws IOException {
        List<Drink> drinks = drinkService.getAllDrink();
        boolean checkAction = false;
        int id = 0;
        do {
            System.out.println("Vui lòng nhập ID thức uống mà bạn tìm");
            String input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Vui lòng nhập lại (ID là một số)");
                id = 0;
                continue;
            }
            if (id < 0) {
                System.out.println("LỖI! Vui lòng nhập lại (ID là một số lớn hơn 0)");
                id = 0;
                continue;
            }
            int check = (int) drinkService.checkIdDrink(id);
            switch (check) {
                case 1:
                    for (int i = 0; i < drinks.size(); i++) {
                        if (drinks.get(i).getIdDrink() == id) {
                            System.out.println(" --------------------------------------------------------------------------------------------------");
                            System.out.printf("%10s ║ %-30s ║ %-10s ║ %15s ║ %20s", "ID", "Tên Thức Uống", "Số Lượng", "Giá Tiền", "Loại").println();
                            System.out.println(" --------------------------------------------------------------------------------------------------");
                            System.out.printf(drinks.get(i).drinkView()).println();
                            System.out.println(" --------------------------------------------------------------------------------------------------");
                        }
                    }
                    checkAction = true;
                    break;
                case -1:
                    System.out.println("LỖI! Vui lòng nhập lai ID (Hệ thống không tìm thấy ID mà bạn vừa nhập)");
                    checkAction = false;
                    break;
            }
        } while (!checkAction);
    }

    public void deleteDrinkByID() throws IOException {
        displayDrinkList();
        List<Drink> drink = drinkService.getAllDrink();
        List<Drink> drinkUpdate = drinkService.getAllDrinkUpdate();
        int id = 0;
        boolean checkID = false;
        do {
            System.out.println("Vui lòng nhập ID thức uống mà bạn muốn xóa:");
            String input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Vui lòng nhập lại (ID là một số)");
                id = 0;
                continue;
            }
            if (id < 0) {
                System.out.println("LỖI! Vui lòng nhập lại (ID là một số lớn hơn 0)");
                id = 0;
                continue;
            }
            int check = (int) drinkService.checkIdDrink(id);
            switch (check) {
                case 1:
                    drinkService.deleteDrinkById(id);
                    drinkService.deleteDrinkUpdateById(id);
                    checkID = true;
                    break;
                case -1:
                    System.out.println("LỖI! Vui lòng nhập lai ID (Hệ thống không tìm thấy ID mà bạn vừa nhập)");
            }
        } while (!checkID);
        displayDrinkList();
        System.out.println("Thao tác xóa thức uống đã hoàn tất!");
    }

    public void editDrinkByID() throws IOException {
        displayDrinkList();
        List<Drink> drinks = drinkService.getAllDrink();
        DrinkView drinkView = new DrinkView();
        int id = 0;
        do {
            boolean checkAction = false;
            System.out.println("Vui lòng nhập ID thức uống mà bạn muốn chỉnh sửa thông tin");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                System.out.println("LỖI! Vui lòng nhập lại (ID là một số)");
                id = 0;
                continue;
            }
            if (id < 0) {
                System.out.println("LỖI! Vui lòng nhập lại (ID là một số lớn hơn 0)");
                id = 0;
                continue;
            }
            int check = (int) drinkService.checkIdDrink(id);
            if (check == -1) {
                System.out.println("LỖI! Vui lòng nhập lại (Hệ thống không tìm thấy ID)");
                continue;
            }

            Drink drink = null;
            for (Drink item : drinks) {
                if (item.getID() == id)
                    drink = item;
            }

            do {
                menuEditDrinkDisplay();
                int select = 0;
                try {
                    select = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("LỖI! Xin vui lòng nhập lại (chỉ nhập số đúng với tính năng)");
                    continue;
                }
                switch (select) {
                    case 1:
                        String nameDrink = null;
                        boolean checkValidName = false;
                        do {
                            System.out.println("Vui lòng nhập tên mới :");
                            nameDrink = scanner.nextLine();
                            checkValidName = ValidateUtils.isNameDrink(nameDrink);
                            if (!checkValidName) {
                                System.out.println("LỖI! Xin vui lòng nhập lại (Tên không quá 20 kí tự)");
                            }
                        } while (!checkValidName);
                        drink.setNameDrink(nameDrink);
                        checkAction = true;
                        break;
                    case 2:
                        int quantity = 0;
                        boolean checkValid = false;
                        boolean checkValidQuatity = false;
                        do {
                            System.out.println("Vui lòng nhập số lượng mới:");
                            String input1 = scanner.nextLine();
                            checkValid = ValidateUtils.isQuantity(quantity, input1);
                            if (!checkValid) {
                                checkValidQuatity = false;
                            } else {
                                quantity = Integer.parseInt(input1);
                                drink.setQuantity(quantity);
                                checkValidQuatity = true;
                            }
                        } while (!checkValidQuatity);
                        checkAction = true;
                        break;
                    case 3:
                        double price = 0;
                        boolean checkValidPrice = false;
                        do {
                            System.out.println("Xin vui lòng nhập giá tiền mới:");
                            String input1 = scanner.nextLine();
                            try {
                                price = Double.parseDouble(input1);
                            } catch (NumberFormatException numberFormatException) {
                                System.out.println("LỖI! Xin vui lòng nập lại (giá tiền phải là số)");
                                price = 0;
                                continue;
                            }
                            checkValidPrice = ValidateUtils.isValidPrice(price);
                            if (checkValidPrice == false) {
                                System.out.println("LỖI! Xin vui lòng nhập lại(giá tiền phải dưới 100.000 Đ");
                            }
                        } while (!checkValidPrice);
                        drink.setPriceDrink(price);
                        checkAction = true;
                        break;
                    case 4:
                        ECategory typeOfDrink = null;
                        do {
                            System.out.println("Xin vui lòng nhập loại thức uống muốn sửa:");
                            System.out.println("Nhập 1: Best Seller");
                            System.out.println("Nhập 2: Milk Tea");
                            System.out.println("Nhập 3: Coffee");

                            try {
                                int typeOfDrinkk = Integer.parseInt(scanner.nextLine());//scanner.nextLine().trim().toLowerCase();
                                typeOfDrink = ECategory.getCategoryById(typeOfDrinkk);
                                break;
                            } catch (Exception ex) {
                                System.out.println("Không hợp lệ");
                            }
                        } while (true);
                        drink.seteCategory(typeOfDrink);
                        checkAction = true;
                        break;
                    case 5:
                        drinkView.editDrinkByID();
                        checkAction = true;
                        break;
                    default:
                        System.out.println("LỖI! Vui lòng nhập lại (Chỉ nhập đúng số tác vụ hiển thị )");
                        checkAction = false;
                        break;
                }
            } while (!checkAction);
            break;
        } while (true);
        fileService.writeData(FILE_PATH_DRINK, drinks);
        displayDrinkList();
        System.out.println("Thao tác chỉnh sửa hoàn tất!");

    }

    public void menuEditDrinkDisplay() {
        System.out.println("Nhập lựa chọn bạn muốn sửa:");
        System.out.println("1. Sửa tên thức uống");
        System.out.println("2. Sửa số lượng thức uống");
        System.out.println("3. Sửa giá tiền thức uống");
        System.out.println("4. Sửa loại thức uống");
        System.out.println("5. Quay lại");
    }

    public void addDrink() throws IOException {
        List<Drink> drinks = drinkService.getAllDrink();
        List<Drink> drinksUpdate = drinkService.getAllDrinkUpdate();
        Drink drink = new Drink();

        ECategory typeOfDrink = null;
        boolean checkType = false;
        do {
            System.out.println("Xin vui lòng nhập loại thức uống muốn xem:");
            System.out.println("Nhập 1: Best Seller");
            System.out.println("Nhập 2: Milk Tea");
            System.out.println("Nhập 3: Coffee");

            try {
                int typeOfDrinkk = Integer.parseInt(scanner.nextLine());//scanner.nextLine().trim().toLowerCase();
                typeOfDrink = ECategory.getCategoryById(typeOfDrinkk);
                checkType = true;
            } catch (Exception ex) {
                System.out.println("Không hợp lệ");
            }
        } while (!checkType);
        boolean checkNameDrink = false;
        do {
            String nameDrink = null;
            boolean checkValidNameDrink = false;
            do {
                System.out.println("Mời nhập tên thức uống muốn thêm:");
                nameDrink = scanner.nextLine();
                checkValidNameDrink = ValidateUtils.isNameDrink(nameDrink);
                if (!checkValidNameDrink) {
                    System.out.println("LỖI! Xin vui lòng nhập lại (tối đa 25 kí tự)");
                }
            } while (!checkValidNameDrink);
            int checkName = drinkService.checkNameDrink(nameDrink);
            switch (checkName) {
                case 1:
                    checkNameDrink = true;
                    Drink drinkExsit = new Drink();
                    int index = 0;
                    for (int i = 0; i < drinks.size(); i++) {
                        if (drinks.get(i).getNameDrink().equals(nameDrink)) {
                            drinkExsit = drinks.get(i);
                            index = i;
                            break;
                        }
                    }
                    int quantity = getNumberWithBoundary("Thức uống đã có, vui lòng nhập số lượng", 0, 500 - drinkExsit.getQuantity());
                    drinkExsit.setQuantity(drinkExsit.getQuantity() + quantity);
                    drinks.set(index, drinkExsit);
                    break;
                case -1:
                    int quantity1 = getNumberWithBoundary("Mời nhập số lượng:", 0, 500);
                    double price = getDoubleWithBoundary("Mời nhập giá tiền", 10_000, 100_000);
                    drinks.sort(new SortDrinkByIDIncrease());
                    long id;
                    if (drinks.size() == 0) {
                        id = 1;
                    } else {
                        id = drinks.get(drinks.size() - 1).getIdDrink() + 1;
                    }
                    drink.setIdDrink(id);
                    drink.setNameDrink(nameDrink);
                    drink.setQuantity(quantity1);
                    drink.setPriceDrink(price);
                    drink.seteCategory(typeOfDrink);
                    drinks.add(drink);
                    drinksUpdate.add(drink);
                    checkNameDrink = true;
                    break;
            }
        } while (!checkNameDrink);
        fileService.writeData(FILE_PATH_DRINK, drinks);
        fileService.writeData(FILE_PATH_DRINK_UPDATE, drinksUpdate);
        displayDrinkList();
        System.out.println("Thao tác thêm thức uống mới hoàn tất!");
    }

    public void sortPriceByIncrease() throws IOException {
        List<Drink> drinks = drinkService.getAllDrink();
        drinks.sort(new SortDrinkByPriceIncrease());
        fileService.writeData(FILE_PATH_DRINK, drinks);
        displayDrinkList();
    }

    public void sortPriceByDecrease() throws IOException {
        List<Drink> drinks = drinkService.getAllDrink();
        drinks.sort(new SortDrinksByPriceDecrease());
        fileService.writeData(FILE_PATH_DRINK, drinks);
        displayDrinkList();
    }

    public void displayDrinkListByType() throws IOException {
        List<Drink> drinks = drinkService.getAllDrink();
        ECategory typeOfDrink = null;
        boolean checkType = false;
        do {
            //doBack();
            System.out.println("Xin vui lòng nhập loại thức uống muốn xem:");
            System.out.println("Nhập 1: Best Seller");
            System.out.println("Nhập 2: Milk Tea");
            System.out.println("Nhập 3: Coffee");

            try {
                int typeOfDrinkk = Integer.parseInt(scanner.nextLine());//scanner.nextLine().trim().toLowerCase();
                typeOfDrink = ECategory.getCategoryById(typeOfDrinkk);
                checkType = true;
            } catch (Exception ex) {
                System.out.println("Ko hop le");
            }

//
//            if (typeOfDrink.equals("0")) {
//                checkType = true;
//                launcher();
//            }
//            switch (typeOfDrink.replace(" ", "")) {
//                case "coffee":
//                    checkType = true;
//                    break;
//                case "milktea":
//                    checkType = true;
//                    break;
//                case "bestseller":
//                    checkType = true;
//                    break;
//                default:
//                    checkType = false;
//                    System.out.println("LỖI! Vui lòng nhập lại!");
//                    break;
//            }
        } while (!checkType);
        drinks.sort(new SortDrinkByIDIncrease());
        System.out.println(" --------------------------------------------------------------------------------------------------");
        System.out.printf("%10s ║ %-30s ║ %-10s ║ %15s ║ %20s", "ID", "Tên Thức Uống", "Số Lượng", "Giá Tiền", "Loại").println();
        System.out.println(" --------------------------------------------------------------------------------------------------");
//        ECategory eCategory = ECategory.getCategoryById(typeOfDrink);
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).geteCategory() == typeOfDrink) {
                System.out.printf(drinks.get(i).drinkView()).println();
            }
        }
        System.out.println(" --------------------------------------------------------------------------------------------------");
    }

    public void findDrinkByKeyword() throws IOException {
        CustomerView customerView = new CustomerView();
        List<Drink> results = new ArrayList<>();
        List<Drink> drinks = drinkService.getAllDrink();

        boolean checkKw = false;
        do {
            System.out.println("Vui lòng nhập từ khóa để hệ thống tìm kiếm: ");
            String kw = scanner.nextLine();
            boolean checkOut = true;
            for (int i = 0; i < drinks.size(); i++) {
                if (drinks.get(i).getNameDrink().toUpperCase().contains(kw.toUpperCase())) {
                    results.add(drinks.get(i));
                    checkOut = true;
                }
            }
            if (!checkOut) {
                System.out.println("Không tìm thấy thức uống nào với từ khóa bạn vừa nhập");
                checkKw = false;
            } else {
                checkKw = true;
            }
        } while (!checkKw);
        displayDrinkList(results);
    }

    public boolean actionContinue() {
        boolean actionContinue = false;
        do {
            System.out.println("Nhập phím q để quay lại, phím a để về menu Admin");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "q":
                    return false;
                case "a":
                    return true;
                default:
                    System.out.println("LỖI! Bạn vừa nhập không đúng lựa chọn");
                    actionContinue = false;
            }
        } while (!actionContinue);
        return true;
    }

    public void displayDrinkList(List<Drink> drinks) throws IOException {
        System.out.println(" --------------------------------------------------------------------------------------------------");
        System.out.printf("%10s ║ %-30s ║ %-10s ║ %15s ║ %20s", "ID", "Tên Thức Uống", "Số Lượng", "Giá Tiền", "Loại").println();
        System.out.println(" --------------------------------------------------------------------------------------------------");
        for (Drink drink : drinks) {
            System.out.printf(drink.drinkView()).println();
        }
        System.out.println(" --------------------------------------------------------------------------------------------------");
    }

    public void displayDrinkList() throws IOException {
        List<Drink> drinks = drinkService.getAllDrink();
        System.out.println(" --------------------------------------------------------------------------------------------------");
        System.out.printf("%10s ║ %-30s ║ %-10s ║ %15s ║ %20s", "ID", "Tên Thức Uống", "Số Lượng", "Giá Tiền", "Loại").println();
        System.out.println(" --------------------------------------------------------------------------------------------------");
        for (int i = 0; i < drinks.size(); i++) {
            System.out.printf(drinks.get(i).drinkView()).println();
        }
        System.out.println("---------------------------------------------------------------------------------------------------");

    }

    public void doBack() {
        System.out.println("Nhập 0 để hủy tác vụ và quay lại");
    }
}
