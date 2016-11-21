package xpadro.spring.integration.mongodb.entity;

public class Product {
    private static final int DAYS_STANDARD = 5;
    private static final int DAYS_PREMIUM = 3;

    private String id;
    private boolean premium;
    private int daysToDeliver;
    private boolean processed;


    public Product(String id, boolean premium) {
        this.id = id;
        this.premium = premium;
        this.daysToDeliver = premium ? DAYS_PREMIUM : DAYS_STANDARD;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public boolean isPremium() {
        return premium;
    }

    public int getDaysToDeliver() {
        return daysToDeliver;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", premium=" + premium +
                ", daysToDeliver=" + daysToDeliver +
                '}';
    }
}
