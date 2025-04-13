import java.util.UUID;

/**
 * Evento cuando se reduce el stock del producto.
 */
class ProductStockDecreasedEvent implements DomainEvent {
    private final UUID productId;
    private final int quantity;

    public ProductStockDecreasedEvent(UUID productId, int quantity) {
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
