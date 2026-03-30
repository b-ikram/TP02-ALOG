# TP02 - Software Architecture: Pipe & Filter Pattern

This project implements a multifunctional calculator using the Pipe & Filter architectural style. The objective is to demonstrate the separation of concerns into autonomous components (Filters) communicating via synchronized data streams (Pipes).

---

## System Architecture

The application relies on a continuous data cycle between 3 components (Filters) connected by Blocking Queues (Pipes).

### Components (Filters)

1.  CMP1: GuiFilter (User Interface)
    * Role: Handles operand input and operation selection (Sum, Product, Factorial).
    * Action: Formats the request (e.g., SUM;10;5) and sends it into the first Pipe. It then waits for a response to update the display label.
2.  CMP2: CalculatorFilter (Logic Engine)
    * Role: Performs the business logic.
    * Action: Parses the received string, computes the result, and transmits the success message to the next component.
3.  CMP3: TraceFilter (Persistence & Logging)
    * Role: Data archiving.
    * Action: Records every operation into a local trace.txt file (append mode) and forwards the data back to the GUI to complete the display cycle.

### Connector (Pipe)

* BlockingQueue: This connector utilizes Java's wait() and notify() mechanisms to ensure perfect synchronization between Threads. A filter only consumes CPU cycles when data is available in its input pipe.

---

## Repository Structure

```text
TP02-ALOG/
├── bin/                       # Compiled files (.class)
├── src/                       # Java source code
│   ├── MainApp.java           # Thread assembly and startup
│   └── pipeandfilter/         # Framework package
│       ├── Pipe.java          # Abstract connector class
│       ├── Filter.java        # Abstract runnable component class
│       ├── BlockingQueue.java # Synchronized Pipe implementation
│       ├── GuiFilter.java     # CMP1 (Swing UI)
│       ├── CalculatorFilter.java # CMP2 (Arithmetic Logic)
│       └── TraceFilter.java   # CMP3 (File Saving .txt)
├── run.bat                    # Automation script for Windows
├── trace.txt                  # Automatically generated log file
└── README.md
