package model;

import java.time.Instant;

public class Product {
    private long idProduct = System.currentTimeMillis();
    private String nameProduct;
    private ECategory category;
    private int quantity;
    private Double priceProduct;
    private Instant createAt;
    private Instant deleteAt;

    public Product() {
    }

    public Product(long idProduct, String nameProduct, ECategory category, int quantity, Double priceProduct, Instant createAt) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.category = category;
        this.quantity = quantity;
        this.priceProduct = priceProduct;
        this.createAt = createAt;
    }

    public Product(long idProduct, String nameProduct, ECategory category, int quantity, Double priceProduct, Instant createAt, Instant deleteAt) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.category = category;
        this.quantity = quantity;
        this.priceProduct = priceProduct;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public ECategory geteCategory() {
        return category;
    }

    public void seteCategory(ECategory eCategory) {
        this.category = eCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",this.idProduct,this.nameProduct,this.quantity);
    }
}
