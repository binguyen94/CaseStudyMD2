package model;

import repository.IModel;
import utils.DateFormat;
import utils.ValidateUtils;

import java.util.Date;

public class User implements IModel<User> {
    private long id = System.currentTimeMillis();
    private String username;
    private String password;
    private String fullname;
    private String phoneNumber;
    private Egender gender;
    private String cccd;
    private Date birthday;
    private String email;
    private String address;
    private ERole eRole;

    public User() {
    }

    public User(int id, String username, String password, String fullname, String phoneNumber, Egender gender, String cccd, Date birthday, String email, String address, ERole eRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.cccd = cccd;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
        this.eRole = eRole;
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public String getName() {
        return fullname;
    }

    @Override
    public void update(User obj) {
        this.id = obj.id;
        this.username = obj.username;
        this.password = obj.password;
        this.fullname = obj.phoneNumber;
        this.gender = obj.gender;
        this.cccd = obj.cccd;
        this.birthday = obj.birthday;
        this.email = obj.email;
        this.address = obj.address;
        this.eRole = obj.eRole;
    }

    @Override
    public User parseData(String line) {
        User user = new User();
        String[] strings = line.split(",");
        int id = Integer.parseInt(strings[0]);
        String username = strings[1];
        String password = strings[2];
        String fullname = strings[3];
        String phoneNumber = strings[4];
        Egender gender = Egender.getGenderByName(strings[5]);
        String cccd = strings[6];
        Date birthDay = DateFormat.parseDate1(strings[7]);
        String email = strings[8];
        String address = ValidateUtils.parseCharToComma(strings[9]);
        ERole eRole = ERole.getRoleByName(strings[10]);

        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);

        user.setPhoneNumber(phoneNumber);
        user.setGender(gender);
        user.setCccd(cccd);
        user.setBirthday(birthDay);
        user.setEmail(email);
        user.setAddress(address);
        user.seteRole(eRole);

        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Egender getGender() {
        return gender;
    }

    public void setGender(Egender gender) {
        this.gender = gender;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ERole geteRole() {
        return eRole;
    }

    public void seteRole(ERole eRole) {
        this.eRole = eRole;
    }

    public String userView() {
        return String.format("║%7s║%-15s║%-20s║%-15s║%-15s║%-35s║%-30s║",this.id,this.username,this.fullname,this.gender.getName(),this.cccd,this.phoneNumber, DateFormat.converDateToString1(this.birthday),this.email,this.address);
    }

    @Override
    public String toString() {
        return id + "," + username + "," + password + "," + fullname + "," + phoneNumber + "," + gender.getName() + "," + cccd + "," + DateFormat.converDateToString1(birthday) + "," + email + "," + ValidateUtils.parseCommaToChar(address) + "," + eRole.getName();
    }
}
