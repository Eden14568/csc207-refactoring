package theater;

/**
 * Abstract base class for performance calculators.
 * @author ZhengyangXiang
 */
public abstract class AbstractPerformanceCalculator {
    private Performance performance;
    private Play play;

    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }

    /**
     * Calculates the amount for this performance.
     * @return the calculated amount
     */
    public abstract int getAmount();

    public int getVolumeCredits() {
        return Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }

    /**
     * Factory method to create the appropriate calculator instance.
     * @param performance the performance data
     * @param play the play data
     * @return a concrete subclass of AbstractPerformanceCalculator
     * @throws RuntimeException if the play type is unknown
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            case "history":
                return new HistoryCalculator(performance, play);
            case "pastoral":
                return new PastoralCalculator(performance, play);
            default:
                throw new RuntimeException(String.format("unknown type: %s", play.getType()));
        }
    }
}
