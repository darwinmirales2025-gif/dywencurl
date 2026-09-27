"""
budget_calculator.py
Student Event Budget & Contribution System

This module holds ALL the computation and ALL the report formatting.
The console version and the GUI version both import this module,
so the two of them can never drift apart.

This file is a line-for-line twin of BudgetCalculator.java.
"""

import math

# Budget status labels (kept as constants so the spelling is always the same)
SURPLUS = "SURPLUS"
BALANCED = "BALANCED"
SHORTAGE = "SHORTAGE"

# Peso sign written as a unicode escape so the source file works
# no matter what encoding the text editor saves it in.
PESO = "\u20B1"

LINE = "----------------------------------------"
INNER_LINE = "  ------------------------------------"


def round_money(value):
    """
    Rounds a peso amount to 2 decimal places using "half away from zero".
    Python's built-in round() uses banker's rounding, which would NOT match
    Java, so both programs use this same manual formula instead.
    """
    if value < 0:
        return -math.floor((-value) * 100.0 + 0.5) / 100.0
    return math.floor(value * 100.0 + 0.5) / 100.0


def format_peso(value):
    """Turns a number into a peso string, e.g. 4500 becomes 'P4,500.00'."""
    rounded = round_money(value)
    if rounded < 0:
        return "-" + PESO + f"{-rounded:,.2f}"
    return PESO + f"{rounded:,.2f}"


class BudgetCalculator:
    """Does the same processing as the Java class with the same name."""

    def __init__(self, event_name, number_of_students, food_expense,
                 venue_expense, transportation_expense, materials_expense,
                 other_expenses, contribution_per_student):
        self.event_name = event_name
        self.number_of_students = number_of_students
        self.food_expense = food_expense
        self.venue_expense = venue_expense
        self.transportation_expense = transportation_expense
        self.materials_expense = materials_expense
        self.other_expenses = other_expenses
        self.contribution_per_student = contribution_per_student

    # ---------- Processing ----------

    def get_total_expenses(self):
        """Total Expenses = Food + Venue + Transportation + Materials + Other"""
        return round_money(self.food_expense + self.venue_expense
                           + self.transportation_expense
                           + self.materials_expense + self.other_expenses)

    def get_total_contribution(self):
        """Total Contribution = Number of Students x Contribution per Student"""
        return round_money(self.number_of_students * self.contribution_per_student)

    def get_budget_difference(self):
        """Budget Difference = Total Contribution - Total Expenses"""
        return round_money(self.get_total_contribution() - self.get_total_expenses())

    def get_required_contribution_per_student(self):
        """Required Contribution per Student = Total Expenses / Number of Students"""
        return round_money(self.get_total_expenses() / self.number_of_students)

    def get_budget_status(self):
        """SURPLUS, BALANCED or SHORTAGE, decided from the rounded difference."""
        difference = self.get_budget_difference()
        if difference > 0:
            return SURPLUS
        elif difference < 0:
            return SHORTAGE
        else:
            return BALANCED

    def get_additional_amount_needed(self):
        """Additional Amount Needed = Total Expenses - Total Contribution (0 if none)."""
        difference = self.get_budget_difference()
        return -difference if difference < 0 else 0.0

    def get_remaining_balance(self):
        """Remaining Balance = Total Contribution - Total Expenses (0 if none)."""
        difference = self.get_budget_difference()
        return difference if difference > 0 else 0.0

    # ---------- Output ----------

    def build_summary(self):
        """Builds the complete summary report as one block of text."""
        report = []
        report.append(LINE)
        report.append("     STUDENT EVENT BUDGET SUMMARY")
        report.append(LINE)
        report.append("")
        report.append("Event Name        : " + self.event_name)
        report.append("Number of Students: " + str(self.number_of_students))
        report.append("")
        report.append("EXPENSES")
        report.append("  Food             : " + format_peso(self.food_expense))
        report.append("  Venue            : " + format_peso(self.venue_expense))
        report.append("  Transportation   : " + format_peso(self.transportation_expense))
        report.append("  Materials/Supply : " + format_peso(self.materials_expense))
        report.append("  Other            : " + format_peso(self.other_expenses))
        report.append(INNER_LINE)
        report.append("  Total Expenses   : " + format_peso(self.get_total_expenses()))
        report.append("")
        report.append("CONTRIBUTION")
        report.append("  Per Student      : " + format_peso(self.contribution_per_student))
        report.append("  Total Collected  : " + format_peso(self.get_total_contribution()))
        report.append("")
        report.append("RESULT")
        report.append("  Required Contribution per Student : "
                      + format_peso(self.get_required_contribution_per_student()))
        report.append("  Budget Difference                 : "
                      + format_peso(self.get_budget_difference()))
        report.append("  Budget Status                     : "
                      + self.get_budget_status())
        report.append("  Additional Amount Needed          : "
                      + format_peso(self.get_additional_amount_needed()))
        report.append("  Remaining Balance                 : "
                      + format_peso(self.get_remaining_balance()))
        report.append(LINE)
        return "\n".join(report)
