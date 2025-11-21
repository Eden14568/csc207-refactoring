package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {
    private Invoice invoice;
    private Map<String, Play> plays;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public Map<String, Play> getPlays() {
        return plays;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData);
    }

    public String htmlStatement() {
        StatementData statementData = new StatementData(invoice, plays);
        return renderHtml(statementData);
    }

    private String renderPlainText(StatementData statementData) {
        final StringBuilder result = new StringBuilder("Statement for " + statementData.getCustomer()
                + System.lineSeparator());

        for (PerformanceData p : statementData.getPerformances()) {
            result.append(String.format("  %s: %s (%s seats)%n", p.getName(),
                    usd(p.getAmount()), p.getAudience()));
        }

        result.append(String.format("Amount owed is %s%n", usd(statementData.totalAmount())));
        result.append(String.format("You earned %s credits%n", statementData.totalVolumeCredits()));
        return result.toString();
    }

    private String renderHtml(StatementData statementData) {
        final StringBuilder result = new StringBuilder("<h1>Statement for " + statementData.getCustomer()
                + "</h1>" + System.lineSeparator());
        result.append("<table>" + System.lineSeparator());

        result.append(" <caption>Statement for " + statementData.getCustomer() + "</caption>" + System.lineSeparator());

        result.append(" <tr><th>play</th><th>seats</th><th>cost</th></tr>" + System.lineSeparator());

        for (PerformanceData p : statementData.getPerformances()) {
            result.append(String.format(" <tr><td>%s</td><td>%s</td><td>%s</td></tr>%n",
                    p.getName(), p.getAudience(), usd(p.getAmount())));
        }

        result.append("</table>" + System.lineSeparator());
        result.append(String.format("<p>Amount owed is <em>%s</em></p>%n", usd(statementData.totalAmount())));
        result.append(String.format("<p>You earned <em>%s</em> credits</p>%n", statementData.totalVolumeCredits()));
        return result.toString();
    }

    private String usd(long aNumber) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(aNumber / Constants.PERCENT_FACTOR);
    }
}