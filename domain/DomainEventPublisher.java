class DomainEventPublisher {
    private final ReactiveKafkaProducerTemplate<String, DomainEvent> kafkaTemplate;

    public DomainEventPublisher(ReactiveKafkaProducerTemplate<String, DomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> publish(DomainEvent event) {
        return kafkaTemplate.send("product-events", event.getClass().getSimpleName(), event)
                .then();
    }
}