import javax.swing.*;
import java.awt.*;

/**
 * EventBudgetGUI.java
 * Student Event Budget & Contribution System - Java GUI version (Swing)
 *
 * Swing is built into Java, so no extra framework or library is needed.
 * All the math is done by BudgetCalculator, the very same class the
 * console version uses, so the GUI cannot produce a different answer.
 *
 * How to run:
 *     javac BudgetCalculator.java EventBudgetGUI.java
 *     java EventBudgetGUI
 */
public class EventBudgetGUI extends JFrame {

    private final JTextField eventNameField = new JTextField();
    private final JTextField studentsField = new JTextField();
    private final JTextField foodField = new JTextField();
    private final JTextField venueField = new JTextField();
    private final JTextField transportationField = new JTextField();
    private final JTextField materialsField = new JTextField();
    private final JTextField otherField = new JTextField();
    private final JTextField contributionField = new JTextField();
    private final JTextArea resultArea = new JTextArea(18, 40);

    public EventBudgetGUI() {
        setTitle("Student Event Budget & Contribution System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ----- Input panel -----
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 6, 6));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Event Details"));
        addRow(inputPanel, "Event Name:", eventNameField);
        addRow(inputPanel, "Number of Students:", studentsField);
        addRow(inputPanel, "Food Expense:", foodField);
        addRow(inputPanel, "Venue Expense:", venueField);
        addRow(inputPanel, "Transportation Expense:", transportationField);
        addRow(inputPanel, "Materials/Supplies:", materialsField);
        addRow(inputPanel, "Other Expenses:", otherField);
        addRow(inputPanel, "Contribution per Student:", contributionField);

        // ----- Buttons -----
        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        calculateButton.addActionListener(e -> calculate());
        clearButton.addActionListener(e -> clearAll());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        // ----- Result area -----
        resultArea.setEditable(false);
        resultArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        resultArea.setBorder(BorderFactory.createTitledBorder("Results"));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void addRow(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    /** Validates every field, then shows the summary built by BudgetCalculator. */
    private void calculate() {
        try {
            String eventName = eventNameField.getText().trim();
            if (eventName.isEmpty()) {
                throw new IllegalArgumentException("Event name cannot be empty.");
            }

            int numberOfStudents = readWholeNumber(studentsField.getText(), "Number of students");
            if (numberOfStudents <= 0) {
                throw new IllegalArgumentException("Number of students must be greater than 0.");
            }

            double food = readMoney(foodField.getText(), "Food expense");
            double venue = readMoney(venueField.getText(), "Venue expense");
            double transportation = readMoney(transportationField.getText(), "Transportation expense");
            double materials = readMoney(materialsField.getText(), "Materials/supplies expense");
            double other = readMoney(otherField.getText(), "Other expenses");
            double contribution = readMoney(contributionField.getText(), "Contribution per student");

            BudgetCalculator calculator = new BudgetCalculator(
                    eventName, numberOfStudents, food, venue,
                    transportation, materials, other, contribution);

            resultArea.setText(calculator.buildSummary());
        } catch (IllegalArgumentException error) {
            JOptionPane.showMessageDialog(this, error.getMessage(),
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private int readWholeNumber(String text, String label) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(label + " must be a whole number.");
        }
    }

    private double readMoney(String text, String label) {
        double amount;
        try {
            amount = Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(label + " must be a number.");
        }
        if (!Double.isFinite(amount)) {
            throw new IllegalArgumentException(label + " must be a finite number.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException(label + " cannot be negative.");
        }
        return amount;
    }

    private void clearAll() {
        eventNameField.setText("");
        studentsField.setText("");
        foodField.setText("");
        venueField.setText("");
        transportationField.setText("");
        materialsField.setText("");
        otherField.setText("");
        contributionField.setText("");
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventBudgetGUI().setVisible(true));
    }
}
