package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public int totalAmount() {
        int result = 0;
        for (PerformanceData p : performances) {
            result += p.getAmount();
        }
        return result;
    }

    public int totalVolumeCredits() {
        int result = 0;
        for (PerformanceData p : performances) {
            result += p.getVolumeCredits();
        }
        return result;
    }
}