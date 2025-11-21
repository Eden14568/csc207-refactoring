package theater;

import java.util.List;

public class StatementData {

    private String customer;
    private List<Performance> performances;

    public StatementData(Invoice invoice) {
        this.customer = invoice.getCustomer();
        this.performances = invoice.getPerformances();
    }

    public String getCustomer() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }
}