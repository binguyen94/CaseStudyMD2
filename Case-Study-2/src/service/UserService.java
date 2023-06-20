package service;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.ERole;
import model.User;
import repository.UserRepository;
import repository.CustomerRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private CustomerRepository customerRepository = new CustomerRepository();

    public UserService(){
        userRepository = new UserRepository();
    }

    public List<User> getAllUser() throws IOException{
        return userRepository.getAll();
    }
    public boolean checkLoginAdmin(String username, String pw) throws IOException {
        List<User> allAdmins = getAllUser();
        for(int i = 0; i <allAdmins.size();i++){
            if(allAdmins.get(i).getUsername().equals(username) && allAdmins.get(i).getPassword().equals(pw) && allAdmins.get(i).geteRole().getName().equals("Admin")){
                return true;
            }
        }
        return false;
    }

    public boolean checkLoginCustomer(String username,String pw) throws IOException{
        List<User> allCustomers = getAllUser();
        for(int i = 0; i<allCustomers.size();i++){
            if(allCustomers.get(i).getUsername().equals(username) && allCustomers.get(i).getPassword().equals(pw)&&allCustomers.get(i).geteRole().equals(ERole.Customer)){
                return true;
            }
        }
        return false;
    }
    public List<User> getCustomerList() throws IOException{
        List<User> allCustomers = userRepository.getAll();
        List<User> customerList = new ArrayList<>();
        for(User user : allCustomers){
            if(user.geteRole()==ERole.Customer){
                customerList.add(user);
            }
        }
        return customerList;
    }

    public User loginCustomer(String username, String password) throws IOException {
        List<User> allUsers = getAllUser();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getUsername().equals(username) && allUsers.get(i).getPassword().equals(password) && allUsers.get(i).geteRole().equals(ERole.Customer)) {
                return allUsers.get(i);
            }
        }
        return null;
    }
    public boolean checkUserName(String username) throws IOException {
        List<User> allUsers = getAllUser();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkPhoneNumber(String phoneNumber) throws IOException {
        List<User> allUsers = getAllUser();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCCCD(String cccd) throws IOException {
        List<User> allUsers = getAllUser();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getCccd().equals(cccd)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmail(String email) throws IOException {
        List<User> allUsers = getAllUser();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkPassword(String password) throws IOException {
        List<User> allUsers = getAllCustomers();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getAllCustomers() throws IOException {
        return customerRepository.getAll();
    }

}
