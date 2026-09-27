"""
event_budget_console.py
Student Event Budget & Contribution System - Python console version

Project 1 - MCO1 (Individual)

How to run:
    python event_budget_console.py
"""

import math

from budget_calculator import BudgetCalculator, PESO


def read_event_name():
    """Asks for the event name and refuses an empty answer."""
    while True:
        value = input("Event Name: ").strip()
        if value:
            return value
        print("  >> Invalid input: event name cannot be empty.")


def read_student_count():
    """
    Asks for the number of students.
    Must be a whole number greater than 0, otherwise the program would
    divide by zero when computing the required contribution per student.
    """
    while True:
        value = input("Number of Students: ").strip()
        try:
            students = int(value)
            if students > 0:
                return students
            print("  >> Invalid input: number of students must be greater than 0.")
        except ValueError:
            print("  >> Invalid input: please enter a whole number.")


def read_money(label):
    """Asks for a peso amount. Must be a number and must not be negative."""
    while True:
        value = input(f"{label} ({PESO}): ").strip()
        try:
            amount = float(value)
            if not math.isfinite(amount):
                print(f"  >> Invalid input: {label} must be a finite number.")
            elif amount >= 0:
                return amount
            else:
                print(f"  >> Invalid input: {label} cannot be negative.")
        except ValueError:
            print("  >> Invalid input: please enter a number.")


def ask_yes_or_no(question):
    """Simple yes/no question used for the repeat loop."""
    while True:
        value = input(question).strip().upper()
        if value in ("Y", "YES"):
            return True
        if value in ("N", "NO"):
            return False
        print("  >> Invalid input: please type Y or N.")


def main():
    print("========================================")
    print("  STUDENT EVENT BUDGET & CONTRIBUTION")
    print("             SYSTEM")
    print("========================================")

    keep_going = True
    while keep_going:
        print()
        print("Please enter the event details.")
        print()

        event_name = read_event_name()
        number_of_students = read_student_count()
        food_expense = read_money("Food Expense")
        venue_expense = read_money("Venue Expense")
        transportation_expense = read_money("Transportation Expense")
        materials_expense = read_money("Materials/Supplies Expense")
        other_expenses = read_money("Other Expenses")
        contribution_per_student = read_money("Planned Contribution per Student")

        calculator = BudgetCalculator(
            event_name, number_of_students, food_expense, venue_expense,
            transportation_expense, materials_expense, other_expenses,
            contribution_per_student)

        print()
        print(calculator.build_summary())

        keep_going = ask_yes_or_no("Compute another event? (Y/N): ")

    print()
    print("Thank you for using the system. Good luck with your event!")


if __name__ == "__main__":
    main()
