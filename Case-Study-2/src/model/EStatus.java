package model;

public enum EStatus {
    PAID(1,"paid"),
    UNPAID(2,"unpaid");

    private int id;
    private String name;

    EStatus(int id, String name) {
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

    public static EStatus getStatusById(int id){
        for(EStatus status : values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }
    public static EStatus getStatusByName(String name){
        for(EStatus status : values()){
            if(status.getName().equals(name)){
                return status;
            }
        }
        throw new IllegalArgumentException("Xin vui lòng nhập lại");
    }
}

