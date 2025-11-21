package theater;

/**
 * Data class for performance information.
 */
public class PerformanceData {
    private Performance performance;
    private Play play;
    private AbstractPerformanceCalculator calculator;

    public PerformanceData(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
        this.calculator = AbstractPerformanceCalculator.createPerformanceCalculator(performance, play);
    }

    public String getName() {
        return play.getName();
    }

    public String getType() {
        return play.getType();
    }

    public int getAudience() {
        return performance.getAudience();
    }

    public int getAmount() {
        return calculator.getAmount();
    }

    public int getVolumeCredits() {
        return calculator.getVolumeCredits();
    }
}
