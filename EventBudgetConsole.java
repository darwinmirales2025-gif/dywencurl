import java.io.PrintStream;
import java.util.Scanner;

/**
 * EventBudgetConsole.java
 * Student Event Budget & Contribution System - Java console version
 *
 * Project 1 - MCO1 (Individual)
 *
 * How to run:
 *     javac BudgetCalculator.java EventBudgetConsole.java
 *     java EventBudgetConsole
 */
public class EventBudgetConsole {

    private static PrintStream out = System.out;

    public static void main(String[] args) {
        // Force UTF-8 output so the peso sign prints correctly on Windows too.
        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (Exception e) {
            out = System.out; // fall back to the normal output stream
        }

        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;

        out.println("========================================");
        out.println("  STUDENT EVENT BUDGET & CONTRIBUTION");
        out.println("             SYSTEM");
        out.println("========================================");

        while (keepGoing) {
            out.println();
            out.println("Please enter the event details.");
            out.println();

            String eventName = readEventName(input);
            int numberOfStudents = readStudentCount(input);
            double foodExpense = readMoney(input, "Food Expense");
            double venueExpense = readMoney(input, "Venue Expense");
            double transportationExpense = readMoney(input, "Transportation Expense");
            double materialsExpense = readMoney(input, "Materials/Supplies Expense");
            double otherExpenses = readMoney(input, "Other Expenses");
            double contributionPerStudent = readMoney(input, "Planned Contribution per Student");

            BudgetCalculator calculator = new BudgetCalculator(
                    eventName, numberOfStudents, foodExpense, venueExpense,
                    transportationExpense, materialsExpense, otherExpenses,
                    contributionPerStudent);

            out.println();
            out.println(calculator.buildSummary());

            keepGoing = askYesOrNo(input, "Compute another event? (Y/N): ");
        }

        out.println();
        out.println("Thank you for using the system. Good luck with your event!");
        input.close();
    }

    /** Asks for the event name and refuses an empty answer. */
    private static String readEventName(Scanner input) {
        while (true) {
            out.print("Event Name: ");
            String value = input.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            out.println("  >> Invalid input: event name cannot be empty.");
        }
    }

    /**
     * Asks for the number of students.
     * Must be a whole number greater than 0, otherwise the program would
     * divide by zero when computing the required contribution per student.
     */
    private static int readStudentCount(Scanner input) {
        while (true) {
            out.print("Number of Students: ");
            String value = input.nextLine().trim();
            try {
                int students = Integer.parseInt(value);
                if (students > 0) {
                    return students;
                }
                out.println("  >> Invalid input: number of students must be greater than 0.");
            } catch (NumberFormatException e) {
                out.println("  >> Invalid input: please enter a whole number.");
            }
        }
    }

    /** Asks for a peso amount. Must be a number and must not be negative. */
    private static double readMoney(Scanner input, String label) {
        while (true) {
            out.print(label + " (" + BudgetCalculator.PESO + "): ");
            String value = input.nextLine().trim();
            try {
                double amount = Double.parseDouble(value);
                if (!Double.isFinite(amount)) {
                    out.println("  >> Invalid input: " + label + " must be a finite number.");
                } else if (amount >= 0) {
                    return amount;
                } else {
                    out.println("  >> Invalid input: " + label + " cannot be negative.");
                }
            } catch (NumberFormatException e) {
                out.println("  >> Invalid input: please enter a number.");
            }
        }
    }

    /** Simple yes/no question used for the repeat loop. */
    private static boolean askYesOrNo(Scanner input, String question) {
        while (true) {
            out.print(question);
            String value = input.nextLine().trim().toUpperCase();
            if (value.equals("Y") || value.equals("YES")) {
                return true;
            }
            if (value.equals("N") || value.equals("NO")) {
                return false;
            }
            out.println("  >> Invalid input: please type Y or N.");
        }
    }
}
