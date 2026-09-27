"""
event_budget_gui.py
Student Event Budget & Contribution System - Python GUI version (Tkinter)

Tkinter is built into Python, so no extra library needs to be installed.
All the math is done by budget_calculator.py, the very same module the
console version uses, so the GUI cannot produce a different answer.

How to run:
    python event_budget_gui.py
"""

import tkinter as tk
import math
from tkinter import messagebox

from budget_calculator import BudgetCalculator

FIELD_LABELS = [
    ("event_name", "Event Name:"),
    ("students", "Number of Students:"),
    ("food", "Food Expense:"),
    ("venue", "Venue Expense:"),
    ("transportation", "Transportation Expense:"),
    ("materials", "Materials/Supplies:"),
    ("other", "Other Expenses:"),
    ("contribution", "Contribution per Student:"),
]


class EventBudgetGUI:
    def __init__(self, root):
        self.root = root
        root.title("Student Event Budget & Contribution System")

        # ----- Input frame -----
        input_frame = tk.LabelFrame(root, text="Event Details", padx=10, pady=10)
        input_frame.pack(fill="x", padx=10, pady=10)

        self.entries = {}
        for row, (key, label) in enumerate(FIELD_LABELS):
            tk.Label(input_frame, text=label, anchor="w").grid(
                row=row, column=0, sticky="w", pady=3)
            entry = tk.Entry(input_frame, width=30)
            entry.grid(row=row, column=1, pady=3)
            self.entries[key] = entry

        # ----- Buttons -----
        button_frame = tk.Frame(root)
        button_frame.pack(pady=5)
        tk.Button(button_frame, text="Calculate", width=12,
                  command=self.calculate).pack(side="left", padx=5)
        tk.Button(button_frame, text="Clear", width=12,
                  command=self.clear_all).pack(side="left", padx=5)

        # ----- Result area -----
        result_frame = tk.LabelFrame(root, text="Results", padx=10, pady=10)
        result_frame.pack(fill="both", expand=True, padx=10, pady=10)
        self.result_area = tk.Text(result_frame, width=52, height=20,
                                   font=("Courier New", 10))
        self.result_area.pack(fill="both", expand=True)
        self.result_area.config(state="disabled")

    def read_whole_number(self, key, label):
        try:
            return int(self.entries[key].get().strip())
        except ValueError:
            raise ValueError(f"{label} must be a whole number.")

    def read_money(self, key, label):
        try:
            amount = float(self.entries[key].get().strip())
        except ValueError:
            raise ValueError(f"{label} must be a number.")
        if not math.isfinite(amount):
            raise ValueError(f"{label} must be a finite number.")
        if amount < 0:
            raise ValueError(f"{label} cannot be negative.")
        return amount

    def calculate(self):
        """Validates every field, then shows the summary built by BudgetCalculator."""
        try:
            event_name = self.entries["event_name"].get().strip()
            if not event_name:
                raise ValueError("Event name cannot be empty.")

            number_of_students = self.read_whole_number("students", "Number of students")
            if number_of_students <= 0:
                raise ValueError("Number of students must be greater than 0.")

            food = self.read_money("food", "Food expense")
            venue = self.read_money("venue", "Venue expense")
            transportation = self.read_money("transportation", "Transportation expense")
            materials = self.read_money("materials", "Materials/supplies expense")
            other = self.read_money("other", "Other expenses")
            contribution = self.read_money("contribution", "Contribution per student")

            calculator = BudgetCalculator(
                event_name, number_of_students, food, venue,
                transportation, materials, other, contribution)

            self.show_result(calculator.build_summary())
        except ValueError as error:
            messagebox.showwarning("Invalid Input", str(error))

    def show_result(self, text):
        self.result_area.config(state="normal")
        self.result_area.delete("1.0", tk.END)
        self.result_area.insert(tk.END, text)
        self.result_area.config(state="disabled")

    def clear_all(self):
        for entry in self.entries.values():
            entry.delete(0, tk.END)
        self.show_result("")


def main():
    root = tk.Tk()
    EventBudgetGUI(root)
    root.mainloop()


if __name__ == "__main__":
    main()
