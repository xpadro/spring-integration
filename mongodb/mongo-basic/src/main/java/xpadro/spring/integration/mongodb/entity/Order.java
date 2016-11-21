package xpadro.spring.integration.mongodb.entity;

public class Order {
    private final String id;
    private final boolean premium;

    public Order(String id, boolean premium) {
        this.id = id;
        this.premium = premium;
    }

    public String getId() {
        return id;
    }

    public boolean isPremium() {
        return premium;
    }
}
