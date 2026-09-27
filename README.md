# Project 1 – MCO1 (Individual)

## Student Event Budget & Contribution System

A system implemented in **Java** and **Python** that processes the same inputs, uses the same calculations, and produces the same output.

### Files

| File | Purpose |
|---|---|
| `BudgetCalculator.java` | Shared Java calculation and report logic |
| `EventBudgetConsole.java` | Java console version |
| `EventBudgetGUI.java` | Java Swing GUI version |
| `budget_calculator.py` | Shared Python calculation and report logic |
| `event_budget_console.py` | Python console version |
| `event_budget_gui.py` | Python Tkinter GUI version |
| `test_mco1.py` | Automated Java-vs-Python verification |
| `Project1_MCO1_Documentation.md` | Complete project documentation and presentation script |
| `TEST_RESULTS.txt` | Recorded result of the automated test run |

## Requirements

- Java JDK 8 or newer
- Python 3.x
- No external Python packages are required.
- No external Java libraries are required; Swing is included with Java.

## Run the Java console version

Open a terminal in this folder:

```text
javac BudgetCalculator.java EventBudgetConsole.java
java EventBudgetConsole
```

## Run the Python console version

```text
python event_budget_console.py
```

If your Windows installation uses `py` instead of `python`:

```text
py event_budget_console.py
```

## Run the Java GUI

```text
javac BudgetCalculator.java EventBudgetGUI.java
java EventBudgetGUI
```

## Run the Python GUI

```text
python event_budget_gui.py
```

## Verify the same input/output requirement

Run:

```text
python test_mco1.py
```

The test script compiles the Java files, runs both console programs with the same ten test cases, and compares their complete outputs character by character.

Expected final line:

```text
Result: 10/10 test cases matched exactly.
```

## Main formulas

**Total Expenses** = Food + Venue + Transportation + Materials/Supplies + Other Expenses

**Total Contribution** = Number of Students × Contribution per Student

**Budget Difference** = Total Contribution − Total Expenses

**Required Contribution per Student** = Total Expenses ÷ Number of Students

- Positive difference → **SURPLUS**
- Zero difference → **BALANCED**
- Negative difference → **SHORTAGE**

## Project requirement satisfied

The Java and Python versions use equivalent inputs, equivalent processing, and equivalent outputs. The automated test verifies the requirement rather than relying only on visual inspection.
