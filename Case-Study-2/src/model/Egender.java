package model;

public enum Egender {
    Nam(1,"Nam"),
    Nữ(2,"Nữ"),
    LGBT(3,"LGBT");
    private int id;
    private String name;

    Egender(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Egender getGenderById(int id){
        for(Egender gender : values()){
            if(gender.id == id){
                return gender;
            }
        }
        return null;
    }

    public static Egender getGenderByName(String name){
        for(Egender gender : values()){
            if(gender.getName().equals(name)){
                return gender;
            }
        }
        throw new IllegalArgumentException("Vui lòng nhập lại!");
    }


}
