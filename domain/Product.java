import java.util.UUID;


public class Product {
    private final UUID id;
    private String name;
    private double price;
    private int stock;

    public Product(UUID id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void increaseStock(int quantity) {
        if (quantity > 0) {
            this.stock += quantity;
        }
    }

    public void decreaseStock(int quantity) {
        if (quantity > 0 && this.stock >= quantity) {
            this.stock -= quantity;
        } else {
            throw new IllegalArgumentException("Stock insuficiente");
        }
    }
}

