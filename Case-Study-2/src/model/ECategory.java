package model;

public enum ECategory {
    BESTSELLER(1, "Best Seller"),
    MILKTEA(2, "Milk Tea"),
    COFFEE(3, "Coffee");

    private int id;
    private String name;

    ECategory(int id, String name) {
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

    public static ECategory getCategoryById(int id) {
        for (ECategory category : values()) {
            if (category.getId() == id){
                return category;
            }
        }
        return null;
    }

    public static ECategory getCategoryByName(String name){
        for (ECategory category : values()){
            if(category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Vui lòng nhập lại");
    }
}
