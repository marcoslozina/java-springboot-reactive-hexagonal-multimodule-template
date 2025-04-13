import java.util.UUID;

class ProductStockIncreasedEvent implements DomainEvent {
    private final UUID productId;
    private final int quantity;

    public ProductStockIncreasedEvent(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
