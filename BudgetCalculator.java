import java.util.Locale;

/**
 * BudgetCalculator.java
 * Student Event Budget & Contribution System
 *
 * This class holds ALL the computation and ALL the report formatting.
 * The console version and the GUI version both use this same class,
 * so the two of them can never drift apart.
 *
 * The Python file budget_calculator.py is a line-for-line twin of this file.
 */
public class BudgetCalculator {

    // Budget status labels (kept as constants so the spelling is always the same)
    public static final String SURPLUS = "SURPLUS";
    public static final String BALANCED = "BALANCED";
    public static final String SHORTAGE = "SHORTAGE";

    // Peso sign written as a unicode escape so the source file compiles
    // correctly no matter what encoding the text editor saves it in.
    public static final String PESO = "\u20B1";

    private static final String LINE = "----------------------------------------";
    private static final String INNER_LINE = "  ------------------------------------";

    // ---------- Inputs ----------
    private final String eventName;
    private final int numberOfStudents;
    private final double foodExpense;
    private final double venueExpense;
    private final double transportationExpense;
    private final double materialsExpense;
    private final double otherExpenses;
    private final double contributionPerStudent;

    public BudgetCalculator(String eventName,
                            int numberOfStudents,
                            double foodExpense,
                            double venueExpense,
                            double transportationExpense,
                            double materialsExpense,
                            double otherExpenses,
                            double contributionPerStudent) {
        this.eventName = eventName;
        this.numberOfStudents = numberOfStudents;
        this.foodExpense = foodExpense;
        this.venueExpense = venueExpense;
        this.transportationExpense = transportationExpense;
        this.materialsExpense = materialsExpense;
        this.otherExpenses = otherExpenses;
        this.contributionPerStudent = contributionPerStudent;
    }

    // ---------- Money helpers ----------

    /**
     * Rounds a peso amount to 2 decimal places using "half away from zero".
     * Python's round() uses banker's rounding, which would NOT match Java,
     * so both programs use this same manual formula instead.
     */
    public static double roundMoney(double value) {
        if (value < 0) {
            return -Math.floor((-value) * 100.0 + 0.5) / 100.0;
        }
        return Math.floor(value * 100.0 + 0.5) / 100.0;
    }

    /**
     * Turns a number into a peso string, e.g. 4500 becomes "P4,500.00".
     * Locale.US is forced so the thousands separator is always a comma,
     * which is exactly what the Python version produces.
     */
    public static String formatPeso(double value) {
        double rounded = roundMoney(value);
        if (rounded < 0) {
            return "-" + PESO + String.format(Locale.US, "%,.2f", -rounded);
        }
        return PESO + String.format(Locale.US, "%,.2f", rounded);
    }

    // ---------- Processing ----------

    /** Total Expenses = Food + Venue + Transportation + Materials + Other */
    public double getTotalExpenses() {
        return roundMoney(foodExpense + venueExpense + transportationExpense
                + materialsExpense + otherExpenses);
    }

    /** Total Contribution = Number of Students x Contribution per Student */
    public double getTotalContribution() {
        return roundMoney(numberOfStudents * contributionPerStudent);
    }

    /** Budget Difference = Total Contribution - Total Expenses */
    public double getBudgetDifference() {
        return roundMoney(getTotalContribution() - getTotalExpenses());
    }

    /** Required Contribution per Student = Total Expenses / Number of Students */
    public double getRequiredContributionPerStudent() {
        return roundMoney(getTotalExpenses() / numberOfStudents);
    }

    /** SURPLUS, BALANCED or SHORTAGE, decided from the rounded difference. */
    public String getBudgetStatus() {
        double difference = getBudgetDifference();
        if (difference > 0) {
            return SURPLUS;
        } else if (difference < 0) {
            return SHORTAGE;
        } else {
            return BALANCED;
        }
    }

    /** Additional Amount Needed = Total Expenses - Total Contribution (0 if none). */
    public double getAdditionalAmountNeeded() {
        double difference = getBudgetDifference();
        return (difference < 0) ? -difference : 0.0;
    }

    /** Remaining Balance = Total Contribution - Total Expenses (0 if none). */
    public double getRemainingBalance() {
        double difference = getBudgetDifference();
        return (difference > 0) ? difference : 0.0;
    }

    // ---------- Output ----------

    /**
     * Builds the complete summary report as one block of text.
     * The GUI shows this in a text area; the console prints it.
     */
    public String buildSummary() {
        StringBuilder report = new StringBuilder();
        report.append(LINE).append("\n");
        report.append("     STUDENT EVENT BUDGET SUMMARY").append("\n");
        report.append(LINE).append("\n");
        report.append("\n");
        report.append("Event Name        : ").append(eventName).append("\n");
        report.append("Number of Students: ").append(numberOfStudents).append("\n");
        report.append("\n");
        report.append("EXPENSES").append("\n");
        report.append("  Food             : ").append(formatPeso(foodExpense)).append("\n");
        report.append("  Venue            : ").append(formatPeso(venueExpense)).append("\n");
        report.append("  Transportation   : ").append(formatPeso(transportationExpense)).append("\n");
        report.append("  Materials/Supply : ").append(formatPeso(materialsExpense)).append("\n");
        report.append("  Other            : ").append(formatPeso(otherExpenses)).append("\n");
        report.append(INNER_LINE).append("\n");
        report.append("  Total Expenses   : ").append(formatPeso(getTotalExpenses())).append("\n");
        report.append("\n");
        report.append("CONTRIBUTION").append("\n");
        report.append("  Per Student      : ").append(formatPeso(contributionPerStudent)).append("\n");
        report.append("  Total Collected  : ").append(formatPeso(getTotalContribution())).append("\n");
        report.append("\n");
        report.append("RESULT").append("\n");
        report.append("  Required Contribution per Student : ")
              .append(formatPeso(getRequiredContributionPerStudent())).append("\n");
        report.append("  Budget Difference                 : ")
              .append(formatPeso(getBudgetDifference())).append("\n");
        report.append("  Budget Status                     : ")
              .append(getBudgetStatus()).append("\n");
        report.append("  Additional Amount Needed          : ")
              .append(formatPeso(getAdditionalAmountNeeded())).append("\n");
        report.append("  Remaining Balance                 : ")
              .append(formatPeso(getRemainingBalance())).append("\n");
        report.append(LINE);
        return report.toString();
    }

    // ---------- Simple getters ----------
    public String getEventName() { return eventName; }
    public int getNumberOfStudents() { return numberOfStudents; }
    public double getFoodExpense() { return foodExpense; }
    public double getVenueExpense() { return venueExpense; }
    public double getTransportationExpense() { return transportationExpense; }
    public double getMaterialsExpense() { return materialsExpense; }
    public double getOtherExpenses() { return otherExpenses; }
    public double getContributionPerStudent() { return contributionPerStudent; }
}
