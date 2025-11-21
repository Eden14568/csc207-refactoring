package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data class to hold statement information.
 * @author ZhengyangXiang
 */
public class StatementData {

    private String customer;
    private List<PerformanceData> performances;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.getCustomer();
        this.performances = new ArrayList<>();
        for (Performance p : invoice.getPerformances()) {
            this.performances.add(new PerformanceData(p, plays.get(p.getPlayID())));
        }
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Calculates the total amount for the statement.
     * @return the total amount
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData p : performances) {
            result += p.getAmount();
        }
        return result;
    }

    /**
     * Calculates the total volume credits for the statement.
     * @return the total volume credits
     */
    public int totalVolumeCredits() {
        int result = 0;
        for (PerformanceData p : performances) {
            result += p.getVolumeCredits();
        }
        return result;
    }
}
