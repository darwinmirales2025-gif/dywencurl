"""
Automated verification for Project 1 - MCO1.

Run from this folder:
    python test_mco1.py

The script compiles the Java version, runs the Java and Python console
versions with the exact same inputs, and compares their complete outputs.
It also checks invalid-input cases.
"""

from pathlib import Path
import difflib
import subprocess
import sys

ROOT = Path(__file__).resolve().parent

CASES = [
    ("Normal case (Surplus)",
     "BSCS Student Seminar\n30\n4500\n1500\n1000\n800\n500\n300\nN\n"),
    ("Balanced budget",
     "Class Christmas Party\n25\n2000\n1000\n500\n300\n200\n160\nN\n"),
    ("Shortage",
     "IT Week Workshop\n20\n5000\n2000\n1200\n600\n200\n400\nN\n"),
    ("Surplus with repeating decimal",
     "Departmental Outreach\n3\n600\n250\n100\n30\n20\n350\nN\n"),
    ("Centavo rounding stress test",
     "Small Study Session\n7\n333.33\n100.005\n0\n0\n0\n62.50\nN\n"),
    ("Invalid input - zero students",
     "Event\n0\n5\n1\n1\n1\n1\n1\n10\nN\n"),
    ("Invalid input - negative expense",
     "Event\n5\n-500\n500\n500\n500\n500\n500\n500\nN\n"),
    ("Invalid input - non-numeric expense",
     "Event\n5\nabc\n500\n500\n500\n500\n500\n500\nN\n"),
    ("Invalid input - empty event name",
     "\nEvent\n5\n500\n500\n500\n500\n500\n1000\nN\n"),
    ("Invalid input - NaN and Infinity",
     "Event\n5\nNaN\nInfinity\n500\n500\n500\n500\n500\n500\nN\n"),
]


def run(command, data):
    result = subprocess.run(
        command,
        cwd=ROOT,
        input=data,
        text=True,
        capture_output=True,
        timeout=15,
    )
    if result.returncode != 0:
        raise RuntimeError(
            f"Command failed: {' '.join(command)}\n"
            f"STDERR:\n{result.stderr}"
        )
    return result.stdout


def main():
    print("PROJECT 1 - MCO1 AUTOMATED TEST")
    print("=" * 50)

    print("Compiling Java source files...")
    subprocess.run(
        ["javac", "BudgetCalculator.java", "EventBudgetConsole.java", "EventBudgetGUI.java"],
        cwd=ROOT,
        check=True,
        capture_output=True,
        text=True,
    )
    print("Java compilation: PASS")
    print()

    passed = 0
    for number, (name, data) in enumerate(CASES, start=1):
        java_output = run(["java", "EventBudgetConsole"], data)
        python_output = run([sys.executable, "event_budget_console.py"], data)

        if java_output != python_output:
            print(f"Test {number}: FAIL - {name}")
            print("Output difference:")
            print("".join(difflib.unified_diff(
                java_output.splitlines(True),
                python_output.splitlines(True),
                fromfile="Java",
                tofile="Python",
            )))
            return 1

        print(f"Test {number}: PASS - {name}")
        passed += 1

    print()
    print(f"Result: {passed}/{len(CASES)} test cases matched exactly.")
    print("Java and Python produced identical complete console output for every case.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
