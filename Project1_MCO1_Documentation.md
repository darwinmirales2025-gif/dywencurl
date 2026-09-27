# PROJECT 1 – MCO1 (INDIVIDUAL)

# Student Event Budget & Contribution System

**A System Developed in Two Programming Languages (Java and Python)**

Bachelor of Science in Computer Science
Northwest Samar State University

**Prepared by:** DARWIN CARL F. MIRALES  
**Program:** BSCS  
**Year Level:** 3rd Year  
**Project:** MCO1 – Individual

---

## Table of Contents

1. Phase 1 – Project Overview
2. Phase 2 – Input-Process-Output
3. Phase 3 – System Algorithm
4. Phase 4 – Sample Computation
5. Phase 5 – Java Version
6. Phase 6 – Python Version
7. Phase 7 – Testing
8. Phase 8 – Comparison of the Two Versions
9. Phase 9 – Documentation
10. Phase 10 – Presentation Script

---

# PHASE 1 – PROJECT OVERVIEW

## 1.1 Project Title

**Student Event Budget & Contribution System**

## 1.2 Background

In college, students are always organizing something. A seminar, a class Christmas party,
a departmental outreach, an IT Week activity, a simple study session with snacks. Almost
every one of these activities needs money, and that money usually comes from the students
themselves through a contribution or "share."

The problem is that the computation is normally done by hand, on a piece of paper or on a
phone calculator, by one class officer who is already busy with other things. The officer
adds the food cost, the venue cost, the transportation cost, and so on, then multiplies the
number of students by the planned contribution, then tries to figure out whether the money
will be enough. When any single expense changes, the whole computation has to be repeated
from the beginning.

This project turns that repeated manual computation into a small, dependable system.

## 1.3 Problem Statement

Student organizers compute event budgets manually, which creates three problems:

1. **Errors.** Adding five or more expense items by hand is easy to get wrong, and a wrong
   total means either a shortage on the day of the event or an over-collection from classmates.
2. **Slow revision.** When one expense changes, the organizer has to recompute everything.
3. **No clear answer to the key question.** Organizers often know the total cost but do not
   immediately know the *fair required share per student*, or exactly how much money is still
   missing.

There is no simple tool made specifically for this situation. A spreadsheet can do it, but it
has to be built from scratch each time and not every student knows how to build one correctly.

## 1.4 Purpose

The purpose of this project is to provide a simple and reliable system that computes the
financial requirements of a student event automatically, so that student organizers can see
their total expenses, their total collected contributions, their budget status, and the fair
required contribution per student in one organized summary.

A second purpose, required by the subject, is to demonstrate that a single system design can
be implemented in two different programming languages and still produce exactly the same
results from the same input.

## 1.5 Target Users

- College students organizing a class activity
- Class officers (president, treasurer, secretary)
- Student organizations and councils
- Any student who needs to compute a shared event budget

## 1.6 Objectives

**General Objective**

To develop a Student Event Budget & Contribution System in two programming languages, Java
and Python, that process the same input and produce the same output.

**Specific Objectives**

1. To accept the event name, number of participating students, five categories of expenses,
   and the planned contribution per student.
2. To compute the total expenses, total collected contribution, budget difference, and
   required contribution per student.
3. To determine whether the budget is in SURPLUS, BALANCED, or SHORTAGE.
4. To compute the additional amount needed when there is a shortage, and the remaining
   balance when there is a surplus.
5. To validate all input so that negative amounts, non-numeric entries, and zero students
   are rejected.
6. To display the results in one clean, organized summary using Philippine Peso.
7. To implement the system in both Java and Python using identical formulas and identical
   output formatting.
8. To verify through testing that the two versions produce equivalent results.

## 1.7 Scope

The system covers:

- Input of one event at a time, with the option to compute another event afterwards.
- Five fixed expense categories: food, venue, transportation, materials/supplies, and other.
- One contribution rate that applies equally to every participating student.
- Computation of totals, difference, required share, status, shortage amount, and surplus amount.
- Input validation for empty names, non-numeric values, negative values, and zero students.
- A console version and an optional GUI version in each language.
- Philippine Peso formatting with two decimal places and thousands separators.

## 1.8 Limitations

1. The system does not save data. Once the program is closed, the computation is gone.
   There is no database and no file storage.
2. The system assumes every student contributes the **same** amount. It cannot handle
   different contribution rates for different students.
3. The system does not track who has already paid and who has not. It computes the budget,
   not the collection.
4. The system does not handle multiple events at the same time or compare events.
5. The system does not handle currencies other than the Philippine Peso.
6. There is no user account, login, or printing feature.
7. All amounts are treated as decimal numbers; the system does not check whether an amount
   is realistic (it will accept ₱1,000,000 for snacks if the user types it).

---

# PHASE 2 – INPUT-PROCESS-OUTPUT

## 2.1 Input Table

| # | Input | Description | Data Type (Java) | Data Type (Python) | Validation Rule |
|---|-------|-------------|------------------|--------------------|-----------------|
| 1 | Event Name | Name or title of the school activity | `String` | `str` | Must not be empty |
| 2 | Number of Students | How many students are joining and sharing the cost | `int` | `int` | Whole number, must be greater than 0 |
| 3 | Food Expense | Cost of meals, snacks, drinks | `double` | `float` | Number, must be 0 or greater |
| 4 | Venue Expense | Cost of the hall, room, or place rental | `double` | `float` | Number, must be 0 or greater |
| 5 | Transportation Expense | Cost of the vehicle, fare, or fuel | `double` | `float` | Number, must be 0 or greater |
| 6 | Materials/Supplies Expense | Tarpaulin, printing, decorations, handouts | `double` | `float` | Number, must be 0 or greater |
| 7 | Other Expenses | Any cost that does not fit the categories above | `double` | `float` | Number, must be 0 or greater |
| 8 | Contribution per Student | The amount each student is planned to pay | `double` | `float` | Number, must be 0 or greater |

## 2.2 Output Table

| # | Output | Description | Data Type |
|---|--------|-------------|-----------|
| 1 | Total Expenses | Sum of all five expense categories | decimal |
| 2 | Total Collected Contribution | Number of students × contribution per student | decimal |
| 3 | Required Contribution per Student | The fair share each student should really pay | decimal |
| 4 | Budget Difference | Total contribution minus total expenses | decimal |
| 5 | Budget Status | SURPLUS, BALANCED, or SHORTAGE | text |
| 6 | Additional Amount Needed | Missing money when there is a shortage | decimal |
| 7 | Remaining Balance | Leftover money when there is a surplus | decimal |

## 2.3 Input → Process → Output Diagram

```
        INPUT                          PROCESS                         OUTPUT
+----------------------+   +--------------------------------+   +---------------------+
| Event Name           |   | 1. Add the five expenses       |   | Total Expenses      |
| Number of Students   |   |    -> Total Expenses           |   | Total Collected     |
| Food Expense         |   |                                |   | Required Share      |
| Venue Expense        |-->| 2. Students x Contribution     |-->| Budget Difference   |
| Transportation       |   |    -> Total Contribution       |   | Budget Status       |
| Materials/Supplies   |   |                                |   | Additional Needed   |
| Other Expenses       |   | 3. Contribution - Expenses     |   | Remaining Balance   |
| Contribution/Student |   |    -> Budget Difference        |   |                     |
+----------------------+   |                                |   +---------------------+
                           | 4. Expenses / Students         |
                           |    -> Required Share           |
                           |                                |
                           | 5. Compare -> Status           |
                           |                                |
                           | 6. Shortage -> Amount Needed   |
                           | 7. Surplus  -> Remaining       |
                           +--------------------------------+
```

## 2.4 The Formulas

```
Total Expenses           = Food + Venue + Transportation + Materials + Other

Total Contribution       = Number of Students × Contribution per Student

Budget Difference        = Total Contribution − Total Expenses

Required Contribution    = Total Expenses ÷ Number of Students
    per Student

Budget Status            = SURPLUS   if Budget Difference > 0
                           BALANCED  if Budget Difference = 0
                           SHORTAGE  if Budget Difference < 0

Additional Amount Needed = Total Expenses − Total Contribution   (only when SHORTAGE)
                         = 0                                      (otherwise)

Remaining Balance        = Total Contribution − Total Expenses    (only when SURPLUS)
                         = 0                                      (otherwise)
```

**Note on the two "difference" values.** Additional Amount Needed and Remaining Balance are
really just the Budget Difference with its sign removed. The system shows both fields at all
times so the output layout never changes; the one that does not apply simply shows ₱0.00.

---

# PHASE 3 – SYSTEM ALGORITHM

## 3.1 Step-by-Step Algorithm

1. **Start.**
2. Display the program title.
3. Ask the user for the **event name**. If it is empty, show an error and ask again.
4. Ask for the **number of students**. If it is not a whole number, or if it is not greater
   than 0, show an error and ask again.
5. Ask for the **food expense**. If it is not a number, or if it is negative, show an error
   and ask again.
6. Repeat step 5 for the **venue**, **transportation**, **materials/supplies**, and **other**
   expenses.
7. Ask for the **contribution per student**, using the same validation as step 5.
8. Compute `totalExpenses = food + venue + transportation + materials + other`.
9. Compute `totalContribution = numberOfStudents × contributionPerStudent`.
10. Compute `budgetDifference = totalContribution − totalExpenses`.
11. Compute `requiredContributionPerStudent = totalExpenses ÷ numberOfStudents`.
    (This is safe because step 4 already guaranteed the number of students is above zero.)
12. Determine the **budget status**:
    - If `budgetDifference > 0`, status is SURPLUS.
    - If `budgetDifference < 0`, status is SHORTAGE.
    - Otherwise, status is BALANCED.
13. If the status is SHORTAGE, set `additionalAmountNeeded = −budgetDifference`;
    otherwise set it to 0.
14. If the status is SURPLUS, set `remainingBalance = budgetDifference`;
    otherwise set it to 0.
15. Round every peso amount to 2 decimal places.
16. Display the complete budget summary.
17. Ask the user whether to compute another event. If yes, go back to step 3.
18. Display a closing message.
19. **End.**

## 3.2 Pseudocode

```
BEGIN StudentEventBudgetSystem

    DISPLAY program title

    REPEAT
        // ---------- INPUT ----------
        REPEAT
            READ eventName
            IF eventName is empty THEN DISPLAY "Event name cannot be empty."
        UNTIL eventName is not empty

        REPEAT
            READ numberOfStudents
            IF numberOfStudents is not a whole number THEN
                DISPLAY "Please enter a whole number."
            ELSE IF numberOfStudents <= 0 THEN
                DISPLAY "Number of students must be greater than 0."
            END IF
        UNTIL numberOfStudents is a whole number AND numberOfStudents > 0

        FOR EACH amount IN (food, venue, transportation, materials, other, contribution)
            REPEAT
                READ amount
                IF amount is not a number THEN
                    DISPLAY "Please enter a number."
                ELSE IF amount < 0 THEN
                    DISPLAY "Amount cannot be negative."
                END IF
            UNTIL amount is a number AND amount >= 0
        END FOR

        // ---------- PROCESS ----------
        totalExpenses      <- ROUND(food + venue + transportation + materials + other)
        totalContribution  <- ROUND(numberOfStudents * contribution)
        budgetDifference   <- ROUND(totalContribution - totalExpenses)
        requiredShare      <- ROUND(totalExpenses / numberOfStudents)

        IF budgetDifference > 0 THEN
            budgetStatus     <- "SURPLUS"
            remainingBalance <- budgetDifference
            additionalNeeded <- 0
        ELSE IF budgetDifference < 0 THEN
            budgetStatus     <- "SHORTAGE"
            additionalNeeded <- -budgetDifference
            remainingBalance <- 0
        ELSE
            budgetStatus     <- "BALANCED"
            additionalNeeded <- 0
            remainingBalance <- 0
        END IF

        // ---------- OUTPUT ----------
        DISPLAY eventName, numberOfStudents
        DISPLAY food, venue, transportation, materials, other
        DISPLAY totalExpenses
        DISPLAY contribution, totalContribution
        DISPLAY requiredShare, budgetDifference, budgetStatus
        DISPLAY additionalNeeded, remainingBalance

        READ continueChoice
    UNTIL continueChoice = "N"

    DISPLAY closing message

END
```

## 3.3 Flowchart Description

The flowchart uses the standard symbols: an oval for Start/End, a parallelogram for
input/output, a rectangle for processing, and a diamond for decisions.

**Flow of the diagram, top to bottom:**

1. **Oval — START**
2. **Parallelogram — Input Event Name**
3. **Diamond — "Is the event name empty?"**
   → **Yes**: go to a parallelogram "Display error message", then loop back to step 2.
   → **No**: continue downward.
4. **Parallelogram — Input Number of Students**
5. **Diamond — "Is it a whole number greater than 0?"**
   → **No**: display error, loop back to step 4.
   → **Yes**: continue downward.
6. **Parallelogram — Input Food, Venue, Transportation, Materials, Other, and Contribution**
7. **Diamond — "Are all amounts valid numbers and not negative?"**
   → **No**: display error, loop back to step 6.
   → **Yes**: continue downward.
8. **Rectangle — Compute Total Expenses**
   `Total Expenses = Food + Venue + Transportation + Materials + Other`
9. **Rectangle — Compute Total Contribution**
   `Total Contribution = Students × Contribution per Student`
10. **Rectangle — Compute Budget Difference**
    `Budget Difference = Total Contribution − Total Expenses`
11. **Rectangle — Compute Required Contribution per Student**
    `Required Share = Total Expenses ÷ Students`
12. **Diamond — "Budget Difference > 0?"**
    → **Yes**: rectangle "Status = SURPLUS; Remaining Balance = Budget Difference;
      Additional Needed = 0", then go to step 15.
    → **No**: continue to step 13.
13. **Diamond — "Budget Difference < 0?"**
    → **Yes**: rectangle "Status = SHORTAGE; Additional Needed = −Budget Difference;
      Remaining Balance = 0", then go to step 15.
    → **No**: continue to step 14.
14. **Rectangle — "Status = BALANCED; Additional Needed = 0; Remaining Balance = 0"**
15. **Parallelogram — Display the Budget Summary** (all inputs and all computed values)
16. **Diamond — "Compute another event?"**
    → **Yes**: loop back to step 2.
    → **No**: continue downward.
17. **Oval — END**

**Simplified text flowchart:**

```
              ( START )
                  |
          [Input Event Name] <-------------+
                  |                        |
          < Name empty? > --- Yes ---------+
                  | No
      [Input Number of Students] <---------+
                  |                        |
          < Whole no. > 0? > --- No -------+
                  | Yes
      [Input 5 Expenses + Contribution] <--+
                  |                        |
          < All valid & >= 0? > --- No ----+
                  | Yes
      [ Total Expenses = sum of 5 ]
                  |
      [ Total Contribution = n x rate ]
                  |
      [ Difference = Contribution - Expenses ]
                  |
      [ Required Share = Expenses / n ]
                  |
          < Difference > 0 ? >
             |Yes        |No
   [SURPLUS: Remaining    < Difference < 0 ? >
    = Difference]           |Yes        |No
             |       [SHORTAGE:      [BALANCED:
             |        Needed =        both = 0]
             |        -Difference]        |
             +------------+---------------+
                          |
              [Display Budget Summary]
                          |
              < Another event? > -- Yes --> back to Input Event Name
                          | No
                      (  END  )
```

---

# PHASE 4 – SAMPLE COMPUTATION

## 4.1 Sample Event Data

| Input | Value |
|-------|-------|
| Event Name | BSCS Student Seminar |
| Number of Students | 30 |
| Food Expense | ₱4,500.00 |
| Venue Expense | ₱1,500.00 |
| Transportation Expense | ₱1,000.00 |
| Materials/Supplies Expense | ₱800.00 |
| Other Expenses | ₱500.00 |
| Contribution per Student | ₱300.00 |

## 4.2 Step 1 — Total Expenses

```
Total Expenses = Food + Venue + Transportation + Materials + Other
Total Expenses = 4,500 + 1,500 + 1,000 + 800 + 500
```

Adding one at a time:

```
4,500 + 1,500 = 6,000
6,000 + 1,000 = 7,000
7,000 +   800 = 7,800
7,800 +   500 = 8,300
```

**Total Expenses = ₱8,300.00**

## 4.3 Step 2 — Total Collected Contribution

```
Total Contribution = Number of Students × Contribution per Student
Total Contribution = 30 × 300
Total Contribution = 9,000
```

**Total Collected = ₱9,000.00**

## 4.4 Step 3 — Budget Difference

```
Budget Difference = Total Contribution − Total Expenses
Budget Difference = 9,000 − 8,300
Budget Difference = 700
```

**Budget Difference = ₱700.00**

## 4.5 Step 4 — Required Contribution per Student

```
Required Share = Total Expenses ÷ Number of Students
Required Share = 8,300 ÷ 30
Required Share = 276.666666...
Rounded to 2 decimal places = 276.67
```

**Required Contribution per Student = ₱276.67**

This tells the organizer something useful: each student was asked for ₱300.00, but the event
really only costs ₱276.67 per head. The contribution is ₱23.33 higher than it needs to be.

## 4.6 Step 5 — Budget Status

```
Is Total Contribution > Total Expenses ?
   9,000 > 8,300  ->  TRUE
```

**Budget Status = SURPLUS**

## 4.7 Step 6 — Additional Amount Needed

The status is SURPLUS, not SHORTAGE, so no additional money is needed.

**Additional Amount Needed = ₱0.00**

## 4.8 Step 7 — Remaining Balance

```
Remaining Balance = Total Contribution − Total Expenses
Remaining Balance = 9,000 − 8,300
Remaining Balance = 700
```

**Remaining Balance = ₱700.00**

## 4.9 Expected Final Output

This is the exact text produced by **both** the Java program and the Python program:

```
----------------------------------------
     STUDENT EVENT BUDGET SUMMARY
----------------------------------------

Event Name        : BSCS Student Seminar
Number of Students: 30

EXPENSES
  Food             : ₱4,500.00
  Venue            : ₱1,500.00
  Transportation   : ₱1,000.00
  Materials/Supply : ₱800.00
  Other            : ₱500.00
  ------------------------------------
  Total Expenses   : ₱8,300.00

CONTRIBUTION
  Per Student      : ₱300.00
  Total Collected  : ₱9,000.00

RESULT
  Required Contribution per Student : ₱276.67
  Budget Difference                 : ₱700.00
  Budget Status                     : SURPLUS
  Additional Amount Needed          : ₱0.00
  Remaining Balance                 : ₱700.00
----------------------------------------
```

---

# PHASE 5 – JAVA VERSION

## 5.1 Files

| File | What it does |
|------|--------------|
| `BudgetCalculator.java` | Holds all the formulas and builds the summary text |
| `EventBudgetConsole.java` | The console program: asks questions, validates, prints |
| `EventBudgetGUI.java` | The optional Swing window version |

## 5.2 How to Compile and Run

```
javac BudgetCalculator.java EventBudgetConsole.java
java EventBudgetConsole
```

For the GUI version:

```
javac BudgetCalculator.java EventBudgetGUI.java
java EventBudgetGUI
```

## 5.3 Explanation of the Important Parts

**Why there is a separate `BudgetCalculator` class.**
All the math lives in one class. The console program and the GUI program both create a
`BudgetCalculator` object and ask it for the answers. This means the two Java programs
physically cannot compute different results, because there is only one copy of the formulas.
It also makes the class easy to point at during the defense: "this file is the system, the
other two are just ways of talking to it."

**The constructor.**

```java
public BudgetCalculator(String eventName, int numberOfStudents, double foodExpense, ...)
```

All eight inputs are passed in at once and stored in `final` fields. Once a calculator object
is created, its inputs cannot be changed by accident.

**The `roundMoney` method.**

```java
public static double roundMoney(double value) {
    if (value < 0) {
        return -Math.floor((-value) * 100.0 + 0.5) / 100.0;
    }
    return Math.floor(value * 100.0 + 0.5) / 100.0;
}
```

This is the most important method in the whole project for the "same output" requirement.
Java's default rounding and Python's built-in `round()` do **not** behave the same way —
Python uses banker's rounding, which rounds 2.675 down to 2.67 in some cases where Java would
round it up. Instead of trusting either language's built-in rounding, both programs use this
identical hand-written formula: multiply by 100, add 0.5, chop off the decimals, divide by 100.
Because both languages use the same 64-bit floating point numbers, this produces exactly the
same result every time. The negative branch makes the rounding symmetric so that −2.675 and
2.675 round to the same distance from zero.

**The `formatPeso` method.**

```java
return PESO + String.format(Locale.US, "%,.2f", rounded);
```

`Locale.US` is forced on purpose. Without it, Java uses the computer's regional settings, and
on some systems the thousands separator becomes a period instead of a comma — the output would
then differ from Python on the same machine. Forcing the locale removes that risk. The peso
sign is written as `"\u20B1"` rather than typed directly, so the file compiles correctly no
matter what encoding the editor saved it in.

**The computation methods.**
Each formula gets its own small method with a name that reads like the formula:
`getTotalExpenses()`, `getTotalContribution()`, `getBudgetDifference()`,
`getRequiredContributionPerStudent()`. Every one of them wraps its result in `roundMoney()`,
so no unrounded value ever escapes.

**The status decision.**

```java
double difference = getBudgetDifference();
if (difference > 0)      return SURPLUS;
else if (difference < 0) return SHORTAGE;
else                     return BALANCED;
```

The status is decided from the **already-rounded** difference. This matters: without rounding
first, a difference of 0.0000000001 caused by floating-point error would be reported as a
SURPLUS when the budget is really balanced.

**The `buildSummary` method.**
The whole report is built as one `String` using `StringBuilder`, with `"\n"` written out
explicitly instead of using `%n`. `%n` would insert `\r\n` on Windows and `\n` on Linux, which
would make the Java output differ from the Python output at the byte level. Writing `"\n"`
keeps them identical everywhere.

**Input validation in the console.**
Each input has its own small reader method containing a `while (true)` loop. The loop only
ends with a `return` when the value is acceptable:

```java
private static int readStudentCount(Scanner input) {
    while (true) {
        out.print("Number of Students: ");
        String value = input.nextLine().trim();
        try {
            int students = Integer.parseInt(value);
            if (students > 0) return students;
            out.println("  >> Invalid input: number of students must be greater than 0.");
        } catch (NumberFormatException e) {
            out.println("  >> Invalid input: please enter a whole number.");
        }
    }
}
```

Note that the program reads a whole line with `nextLine()` and then converts it with
`Integer.parseInt()`, instead of using `nextInt()`. `nextInt()` crashes the program when the
user types letters and leaves a leftover newline that breaks the next input. Reading the full
line and parsing it inside a `try-catch` avoids both problems.

The check `students > 0` is what prevents the division-by-zero in
`getRequiredContributionPerStudent()`. The program never needs to test for zero later, because
a zero can never get that far.

**UTF-8 output.**

```java
out = new PrintStream(System.out, true, "UTF-8");
```

On Windows, the default console encoding often cannot display the ₱ character and prints `?`
instead. Wrapping the output stream in UTF-8 fixes this. If the wrapping fails for any reason,
the program falls back to the normal `System.out` and still runs.

**The GUI version.**
`EventBudgetGUI` uses Swing, which is built into Java — nothing has to be downloaded. The eight
input fields are laid out in a `GridLayout`, the Calculate and Clear buttons sit below them, and
the results appear in a read-only `JTextArea` using a monospaced font so the columns line up.
When Calculate is pressed, the program validates every field and throws an
`IllegalArgumentException` with a clear message on the first bad value; that message is shown
in a `JOptionPane` warning dialog. If everything is valid, it creates a `BudgetCalculator` and
puts `buildSummary()` straight into the text area — the same text the console prints.

---

# PHASE 6 – PYTHON VERSION

## 6.1 Files

| File | What it does |
|------|--------------|
| `budget_calculator.py` | Holds all the formulas and builds the summary text |
| `event_budget_console.py` | The console program: asks questions, validates, prints |
| `event_budget_gui.py` | The optional Tkinter window version |

## 6.2 How to Run

```
python event_budget_console.py
```

For the GUI version:

```
python event_budget_gui.py
```

All three files must be in the same folder, because the two programs import
`budget_calculator`.

## 6.3 Explanation of the Important Parts

**The same structure as Java.**
`budget_calculator.py` is a deliberate twin of `BudgetCalculator.java`. The methods appear in
the same order, use the same names converted to Python style
(`getTotalExpenses` → `get_total_expenses`), and contain the same lines of arithmetic. Anyone
can put the two files side by side and check them line by line, which is exactly how the "same
process" requirement was verified.

**`round_money`.**

```python
def round_money(value):
    if value < 0:
        return -math.floor((-value) * 100.0 + 0.5) / 100.0
    return math.floor(value * 100.0 + 0.5) / 100.0
```

This is the Python twin of the Java method. Python's built-in `round()` is deliberately **not**
used, because `round(2.675, 2)` gives 2.67 in Python (banker's rounding on a binary float) while
Java's formatter would give 2.68. Using the same manual formula in both languages removes the
disagreement entirely. `math.floor` returns an integer in Python and a `float` in Java, but
after dividing by 100.0 both produce the same floating-point value, so the results match.

**`format_peso`.**

```python
return PESO + f"{rounded:,.2f}"
```

Python's f-string format specifier `:,.2f` always uses a comma for thousands and a period for
decimals, regardless of the computer's regional settings. This is why the Java version had to
be forced to `Locale.US` — to match this behaviour.

**The class methods.**
`get_total_expenses`, `get_total_contribution`, `get_budget_difference`,
`get_required_contribution_per_student`, `get_budget_status`, `get_additional_amount_needed`,
and `get_remaining_balance` mirror the Java methods exactly, each one wrapping its answer in
`round_money`.

**The status decision** uses the same if / elif / else on the rounded difference, and the
shortage and surplus amounts use the same conditional expressions:

```python
return -difference if difference < 0 else 0.0
```

**`build_summary`.**
Instead of Java's `StringBuilder`, Python appends each line to a list and joins them with
`"\n".join(report)` at the end. This is the natural Python way to build a multi-line string,
and it guarantees the line ending is `\n` on every operating system — the same choice made in
the Java version.

**Input validation in the console.**

```python
def read_money(label):
    while True:
        value = input(f"{label} ({PESO}): ").strip()
        try:
            amount = float(value)
            if amount >= 0:
                return amount
            print(f"  >> Invalid input: {label} cannot be negative.")
        except ValueError:
            print("  >> Invalid input: please enter a number.")
```

The pattern is identical to Java's: an endless loop, a conversion inside a `try`, a range check,
and an error message that repeats the question. Java catches `NumberFormatException`; Python
catches `ValueError`. Different exception names, same idea. The error messages are worded
exactly the same in both languages so the two programs behave identically even when the user
makes a mistake.

**The GUI version.**
`event_budget_gui.py` uses Tkinter, which ships with Python, so nothing has to be installed
with pip. The eight entry boxes are created from a list of labels using a `for` loop and placed
with `.grid()`. The Calculate and Clear buttons call `self.calculate` and `self.clear_all`.
Validation raises `ValueError` with a clear message, caught once and shown through
`messagebox.showwarning` — the direct equivalent of Java's `JOptionPane`. The result is placed
in a `Text` widget using the Courier New font so the alignment matches the console.

---

# PHASE 7 – TESTING

Both programs were run with the same inputs and their outputs were compared character by
character.

## 7.1 Test Case Table

| # | Test Case | Input | Expected Result | Java Result | Python Result | Match? |
|---|-----------|-------|-----------------|-------------|---------------|--------|
| 1 | Normal case (Surplus) | BSCS Student Seminar; 30 students; 4500 / 1500 / 1000 / 800 / 500; contribution 300 | Total Expenses ₱8,300.00; Collected ₱9,000.00; Required ₱276.67; Difference ₱700.00; **SURPLUS**; Needed ₱0.00; Remaining ₱700.00 | Same as expected | Same as expected | ✔ YES |
| 2 | Balanced budget | Class Christmas Party; 25 students; 2000 / 1000 / 500 / 300 / 200; contribution 160 | Total Expenses ₱4,000.00; Collected ₱4,000.00; Required ₱160.00; Difference ₱0.00; **BALANCED**; Needed ₱0.00; Remaining ₱0.00 | Same as expected | Same as expected | ✔ YES |
| 3 | Shortage | IT Week Workshop; 20 students; 5000 / 2000 / 1200 / 600 / 200; contribution 400 | Total Expenses ₱9,000.00; Collected ₱8,000.00; Required ₱450.00; Difference −₱1,000.00; **SHORTAGE**; Needed ₱1,000.00; Remaining ₱0.00 | Same as expected | Same as expected | ✔ YES |
| 4 | Surplus with a repeating decimal | Departmental Outreach; 3 students; 600 / 250 / 100 / 30 / 20; contribution 350 | Total Expenses ₱1,000.00; Collected ₱1,050.00; Required ₱333.33 (from 333.333…); Difference ₱50.00; **SURPLUS**; Remaining ₱50.00 | Same as expected | Same as expected | ✔ YES |
| 5 | Decimal centavo amounts (rounding stress test) | Small Study Session; 7 students; 333.33 / 100.005 / 0 / 0 / 0; contribution 62.50 | Venue rounds to ₱100.01; Total Expenses ₱433.34; Collected ₱437.50; Required ₱61.91 (from 61.9057…); Difference ₱4.16; **SURPLUS**; Remaining ₱4.16 | Same as expected | Same as expected | ✔ YES |
| 6 | Invalid input — zero students | Number of Students = 0 | Rejected with "Invalid input: number of students must be greater than 0." and asked again; no division by zero | Same as expected | Same as expected | ✔ YES |
| 7 | Invalid input — negative expense | Food Expense = −500 | Rejected with "Invalid input: Food Expense cannot be negative." and asked again | Same as expected | Same as expected | ✔ YES |
| 8 | Invalid input — letters instead of a number | Food Expense = abc | Rejected with "Invalid input: please enter a number."; program does not crash | Same as expected | Same as expected | ✔ YES |
| 9 | Invalid input — empty event name | Event Name = (blank) | Rejected with "Invalid input: event name cannot be empty." and asked again | Same as expected | Same as expected | ✔ YES |
| 10 | Invalid input — NaN/Infinity | Food Expense = NaN; then Infinity | Rejected with "must be a finite number." and asked again; program continues normally | Same as expected | Same as expected | ✔ YES |

**Result: 10 of 10 test cases matched exactly.**

## 7.2 Why Test Case 5 Matters

Test case 5 was designed on purpose to try to break the "same output" requirement. The venue
expense of 100.005 sits exactly on a rounding boundary, and 433.34 ÷ 7 = 61.90571428… does not
divide evenly. These are the situations where Java and Python would normally disagree, because
Python's built-in `round()` uses banker's rounding. Since both programs use the shared
`roundMoney` / `round_money` formula instead of the built-in rounding, both produce ₱100.01
and ₱61.91. This test case is the strongest evidence that the two versions really are
equivalent and not just accidentally similar.

## 7.3 Verification Method

The outputs of the two programs were not compared by eye alone. The automated `test_mco1.py`
script compiles the Java files, runs both console programs with all ten test cases, and compares
their complete output character by character. The verification run recorded in `TEST_RESULTS.txt`
reported **10/10 test cases matched exactly**.

---

# PHASE 8 – COMPARISON OF THE TWO VERSIONS

## 8.1 Side-by-Side Comparison

| Aspect | Java Version | Python Version | Same? |
|--------|--------------|----------------|-------|
| Inputs accepted | Event name, students, 5 expenses, contribution | Event name, students, 5 expenses, contribution | ✔ Same |
| Total Expenses formula | `food + venue + transportation + materials + other` | `food + venue + transportation + materials + other` | ✔ Same |
| Total Contribution formula | `numberOfStudents * contributionPerStudent` | `number_of_students * contribution_per_student` | ✔ Same |
| Budget Difference formula | `totalContribution - totalExpenses` | `total_contribution - total_expenses` | ✔ Same |
| Required Share formula | `totalExpenses / numberOfStudents` | `total_expenses / number_of_students` | ✔ Same |
| Rounding method | `Math.floor(v * 100 + 0.5) / 100` | `math.floor(v * 100 + 0.5) / 100` | ✔ Same |
| Status rule | `> 0` SURPLUS, `< 0` SHORTAGE, else BALANCED | `> 0` SURPLUS, `< 0` SHORTAGE, else BALANCED | ✔ Same |
| Number formatting | `String.format(Locale.US, "%,.2f", v)` | `f"{v:,.2f}"` | ✔ Same result |
| Currency displayed | ₱ (`\u20B1`) | ₱ (`\u20B1`) | ✔ Same |
| Output layout | Built by `buildSummary()` | Built by `build_summary()` | ✔ Same text |
| Validation rules | Non-empty name, students > 0, amounts ≥ 0 | Non-empty name, students > 0, amounts ≥ 0 | ✔ Same |
| Error messages | Identical wording | Identical wording | ✔ Same |
| GUI toolkit | Swing (built into Java) | Tkinter (built into Python) | Different tool, same fields and same result |

## 8.2 Where the Two Languages Differ (in writing style only)

These differences are about how each language is written, not about what the system does:

| Detail | Java | Python |
|--------|------|--------|
| Declaring a number | `double food = 4500;` | `food = 4500` |
| Naming style | `numberOfStudents` (camelCase) | `number_of_students` (snake_case) |
| Blocks are marked by | Curly braces `{ }` | Indentation |
| Converting text to a number | `Double.parseDouble(text)` | `float(text)` |
| Error raised by a bad conversion | `NumberFormatException` | `ValueError` |
| Building a long string | `StringBuilder` | list of lines + `"\n".join(...)` |
| Running the program | Compile with `javac`, then `java` | Run directly with `python` |

None of these differences change a single computed value. They are simply the vocabulary each
language uses to express the same instructions.

## 8.3 Conclusion of the Comparison

Both versions accept the same eight inputs, apply the same seven formulas in the same order,
round in the same way, decide the status with the same rule, and print the same report.
Given the same input, they produce the same output. Neither language is treated as better than
the other in this project — each one is simply a different way of writing down the same system
design, and the system design is what the project is really about.

---

# PHASE 9 – DOCUMENTATION

## 9.1 Introduction

This document presents the Student Event Budget & Contribution System, an individual project
developed for Project 1 – MCO1. The requirement is to develop one system using two programming
languages that process the same input and produce the same output. The chosen languages are
Java and Python.

The system helps college students compute the financial requirements of a school activity. It
takes the expected expenses and the planned contribution per student, and it returns the total
cost, the total money that will be collected, the fair share per student, and whether the
budget will end up short, exact, or with money left over.

## 9.2 Background of the Project

School activities are a normal part of college life, and almost all of them are funded by
student contributions. The computation behind them is not difficult, but it is repetitive and
easy to get wrong when done by hand, especially when expenses keep changing during planning.
The idea for this project came from that ordinary, familiar situation rather than from a
textbook example, which is why the inputs are the actual things students spend on: food,
venue, transportation, materials, and miscellaneous costs.

## 9.3 Problem Statement

Student organizers compute event budgets manually, which leads to arithmetic errors, slow
recomputation whenever a cost changes, and uncertainty about the fair required contribution per
student and the exact amount still missing. No simple, purpose-built tool exists for this task.

## 9.4 Objectives

**General:** To develop a Student Event Budget & Contribution System in Java and Python that
process the same input and produce the same output.

**Specific:**
1. To accept eight inputs describing the event and its costs.
2. To compute the total expenses, total contribution, budget difference, and required
   contribution per student.
3. To classify the budget as SURPLUS, BALANCED, or SHORTAGE.
4. To compute the additional amount needed or the remaining balance.
5. To validate all input and prevent division by zero and negative amounts.
6. To display an organized summary in Philippine Peso.
7. To implement identical logic in both languages.
8. To verify equivalence through testing.

## 9.5 Scope and Limitations

**Scope.** One event at a time; five expense categories; one uniform contribution rate;
complete computation of totals, status, shortage, and surplus; full input validation; console
and GUI versions in both languages; Philippine Peso with two decimal places.

**Limitations.** No data storage or database; no per-student payment tracking; no support for
different contribution rates per student; no multi-event comparison; no currency other than the
peso; no login or printing; no check on whether amounts are realistic.

## 9.6 System Description

The system is a calculation program with a single, clear job. The user supplies the event name,
the number of participating students, five expense amounts, and the planned contribution per
student. The system validates each entry as it is typed, so an invalid value never reaches the
computation. It then applies the seven project formulas in a fixed order, rounds every peso
amount to two decimal places, and prints one organized summary.

The system is available in four programs:

| Program | Language | Interface |
|---------|----------|-----------|
| `EventBudgetConsole` | Java | Text / console |
| `EventBudgetGUI` | Java | Swing window |
| `event_budget_console.py` | Python | Text / console |
| `event_budget_gui.py` | Python | Tkinter window |

In each language, the console program and the GUI program share one calculation file
(`BudgetCalculator.java` in Java, `budget_calculator.py` in Python). Because the formulas exist
in only one place per language, and those two places are line-for-line twins, all four programs
are guaranteed to agree.

## 9.7 Input-Process-Output

See **Phase 2** for the full input table, output table, and IPO diagram. In summary:

- **Input:** event name, number of students, food, venue, transportation, materials/supplies,
  other expenses, contribution per student.
- **Process:** add the expenses, multiply students by the contribution rate, subtract to get
  the difference, divide the expenses by the students to get the required share, compare to
  determine the status, and derive the shortage or surplus amount.
- **Output:** a formatted summary showing every input, the total expenses, the total collected,
  the required share, the difference, the status, the additional amount needed, and the
  remaining balance.

## 9.8 Algorithm

See **Phase 3** for the nineteen-step algorithm and the full pseudocode. The essential sequence
is: validate input → compute totals → compute difference and required share → determine status
→ derive shortage or surplus → display summary → optionally repeat.

## 9.9 Flowchart Description

See **Phase 3, Section 3.3**. The flowchart begins with START, passes through three validation
loops for the name, the student count, and the money amounts, then moves through four
processing rectangles for the four main formulas, then through two decision diamonds that
select SURPLUS, SHORTAGE, or BALANCED, then to the output symbol, then to a final decision
asking whether to compute another event, and finally to END.

## 9.10 Programming Language 1 – Java

See **Phase 5** for the file list, the run instructions, and the explanation of the code. Key
points: the formulas live in one `BudgetCalculator` class used by both the console and the GUI;
rounding uses a hand-written half-away-from-zero formula instead of the built-in behaviour;
number formatting is locked to `Locale.US`; line endings are written as `"\n"` instead of `%n`;
input is read as a full line and parsed inside a `try-catch` so bad input never crashes the
program; and the student count is required to be above zero, which is what makes the division
in the required-share formula permanently safe.

## 9.11 Programming Language 2 – Python

See **Phase 6**. The Python version mirrors the Java version. `budget_calculator.py` contains the same
calculation methods in the same order with the same arithmetic. `round_money` replaces
Python's built-in `round()` because the built-in uses banker's rounding and would disagree with
Java. F-string formatting with `:,.2f` produces the same peso strings as Java's locale-locked
formatter. The console program uses the same validation loops with the same error messages, and
the Tkinter GUI presents the same eight fields, the same two buttons, and the same result text.

## 9.12 Testing and Results

See **Phase 7**. Ten test cases were run covering a normal surplus, a balanced budget, a
shortage, a repeating decimal, a centavo-level rounding boundary, zero students, a negative
expense, non-numeric text, an empty event name, and non-finite values such as NaN/Infinity.
All ten produced matching behaviour in Java and Python. The automated `test_mco1.py` script
compared the complete console output and recorded 10/10 exact matches.

## 9.13 Conclusion

The Student Event Budget & Contribution System successfully meets the requirement of the
project: a single system, implemented in two programming languages, processing the same input
and producing the same output.

The project showed that making two programs agree is not automatic, even when the formulas look
identical on paper. The real work was in the details that are easy to overlook — rounding
behaviour, locale-dependent number formatting, and line endings. Each of those three would have
produced a mismatch, and each had to be handled deliberately in both languages. Writing the
same system twice made those differences visible in a way that writing it once never would.

The system itself is genuinely useful for its intended users. A class officer can enter the
planned costs, see immediately whether the planned contribution will cover them, and know the
exact fair share per student instead of guessing. The design is simple enough to explain in a
few minutes and to extend later, for example by saving past events or by tracking who has
already paid.

---

# PHASE 10 – PRESENTATION SCRIPT

> *Speak naturally; the script is a guide, not something to memorize word for word.
> Estimated length: about 5 to 6 minutes.*

---

**Opening**

Good morning, Sir/Ma'am. For my Project 1 – MCO1, I developed a system called the **Student
Event Budget and Contribution System**. I built it in two programming languages, Java and
Python, and both versions process the same input and produce the same output.

**What the system does**

The system computes the budget of a school event. You enter the event name, how many students
are joining, the five main expenses — food, venue, transportation, materials, and other — and
the contribution you are planning to collect from each student. The system then tells you the
total cost, the total money you will collect, whether you will have extra money or you will
fall short, and the exact amount each student really needs to pay.

**Why I made it**

I chose this because it is something I have actually seen happen. Every time our class or our
organization holds an activity, somebody has to sit down and compute the budget by hand, and
when one expense changes, the whole computation starts over. It is simple math, but it is
repetitive and easy to get wrong. So instead of a generic calculator project, I made something
that solves a real problem that students in this department deal with regularly.

**How the system works — the inputs**

There are eight inputs. The event name, the number of students, then the five expenses, then
the planned contribution per student.

Every input is validated. The event name cannot be blank. The expenses cannot be negative,
because there is no such thing as negative spending. And the number of students has to be
greater than zero — this one is important, because the system divides the total expenses by
the number of students, so if I allowed zero the program would crash with a division-by-zero
error. By rejecting zero at the input stage, that division is permanently safe. I never have to
check for it again later.

**How the calculations work**

There are four main formulas.

First, total expenses — I just add the five expense categories together.

Second, total contribution — the number of students multiplied by the contribution per student.

Third, budget difference — the total contribution minus the total expenses. If that is
positive, the status is SURPLUS. If it is negative, the status is SHORTAGE. If it is exactly
zero, the status is BALANCED.

Fourth, the required contribution per student — the total expenses divided by the number of
students. This is the part I find most useful, because it tells you the fair share. In my
sample, the students were being asked for ₱300 each, but the event actually only costs ₱276.67
per person. So you can see right away that you are over-collecting.

And then if there is a shortage, the system shows how much money is still missing; if there is
a surplus, it shows how much will be left over.

**The sample computation**

In my sample I used a BSCS Student Seminar with 30 students. The expenses were ₱4,500 for food,
₱1,500 for the venue, ₱1,000 for transportation, ₱800 for materials, and ₱500 for other, so the
total is ₱8,300. Thirty students times ₱300 gives ₱9,000 collected. The difference is ₱700, so
the status is SURPLUS with ₱700 remaining, and the required share is ₱8,300 divided by 30,
which is ₱276.67.

**Why two programming languages**

The requirement was to use two languages that process the same input and output, so I chose
Java and Python. I picked these two because they are different in the ways that matter for this
exercise — Java is compiled and statically typed with curly braces, Python is interpreted and
dynamically typed with indentation — so if I could make these two agree exactly, that would be
a real demonstration and not just a copy-paste.

To keep them consistent, I put all the formulas in one file per language. In Java that is
`BudgetCalculator.java`; in Python it is `budget_calculator.py`. The console version and the
GUI version both use that same file, so within each language there is only one copy of the math.
And the two files are written as line-for-line twins, so you can put them side by side and check
them method by method.

**How I verified they produce the same results**

This is the part that surprised me the most. I assumed that if the formulas were the same, the
answers would automatically be the same. They were not — I found three things that would have
caused mismatches.

The first was **rounding**. Python's built-in `round` function uses banker's rounding, so in
some cases it rounds down where Java rounds up. So instead of using either language's built-in
rounding, I wrote the same manual formula in both: multiply by 100, add 0.5, take the floor,
divide by 100.

The second was **number formatting**. Java uses the computer's regional settings by default, so
on some machines the thousands separator would come out as a period instead of a comma. I
forced `Locale.US` in the Java version to match what Python always does.

The third was **line endings**. Java's `%n` produces a different line ending on Windows than on
Linux, so I wrote `\n` explicitly in both versions instead.

After fixing those three, I tested with ten test cases — a normal surplus, a balanced budget, a
shortage, a repeating decimal, a rounding boundary case with centavos, and invalid-input cases
including zero, negative values, non-numeric text, an empty event name, and non-finite values.
I designed the fifth one specifically to try to break the match, using an amount that sits exactly
on a rounding boundary. The automated `test_mco1.py` script then compared the complete output of
both programs. All 10 cases matched exactly.

**Closing**

So to summarize: the system solves a real problem for student organizers, it validates all its
input, it computes the total cost, the status, and the fair share per student, and it is
implemented in both Java and Python with verified identical results.

Thank you, Sir/Ma'am. I am ready for any questions.

---

## Likely Questions and Suggested Answers

**"Why did you write your own rounding instead of using the built-in one?"**
Because the built-in ones disagree. Python's `round()` uses banker's rounding — it rounds to
the nearest even number on a tie — while Java's formatter rounds half up. With money that
difference shows up as one centavo, which would break the "same output" requirement. Writing
the same formula in both languages removes the disagreement.

**"What happens if the user enters zero students?"**
The input is rejected and the question is asked again. That is deliberate, because the required
contribution per student is the total expenses divided by the number of students, so a zero
there would cause a division-by-zero error. I prevented it at the input instead of handling it
later.

**"Why do you show both the additional amount needed and the remaining balance?"**
So the output layout never changes. Whichever one does not apply simply shows ₱0.00. It makes
the report easier to read and easier to compare between the two languages.

**"Could you add a database?"**
Yes, that would be the natural next step — saving past events and tracking who has already paid.
I left it out on purpose to keep the project within the scope of this machine problem and simple
enough to explain fully.

**"Which language was better for this?"**
Neither, honestly. Python was faster to write because it needs less setup code, and Java caught
a couple of my mistakes at compile time that Python only showed me when I ran it. For a system
this size they are equally suitable — the design is what matters, and the design is the same in
both.

---

*End of documentation.*
