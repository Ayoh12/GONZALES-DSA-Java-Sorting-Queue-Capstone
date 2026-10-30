# GONZALES-DSA-Java-Sorting-Queue-Capstone
# Parking Ticket Management System

## Project Description

The Parking Ticket Management System is a console-based Java application designed to manage vehicles waiting to enter a parking area. It issues parking ticket numbers, records vehicle information, manages the waiting queue, and tracks whether a vehicle is waiting, parked, or has exited.

The system uses a **First-In, First-Out (FIFO) queue**, allowing vehicles to enter in the order they were registered. It also implements Bubble Sort and Insertion Sort to organize and display vehicle records.

## Features

1. **Register Vehicle** – Records the vehicle's plate number, vehicle type, and manually entered entry time. Each vehicle receives a unique ticket number.
2. **View Waiting Queue** – Displays vehicles waiting to enter, including their queue positions, ticket numbers, and plate numbers.
3. **Admit Next Vehicle** – Admits the first vehicle in the waiting queue and updates its status to `Parked`.
4. **View Next Vehicle** – Displays the next vehicle waiting to enter without removing it from the queue.
5. **Search Vehicle** – Searches for a vehicle using its ticket number or plate number.
6. **View All Vehicle Records** – Displays all registered vehicles and their current statuses.
7. **Sort by Ticket Number** – Uses Bubble Sort to arrange vehicle records by ticket number in ascending order.
8. **Sort by Plate Number** – Uses Insertion Sort to arrange vehicle records alphabetically by plate number.
9. **Exit Parking** – Updates a parked vehicle's status to `Exited` using its ticket number.
10. **Exit Program** – Terminates the application.

## Technologies Used

- **Programming Language:** Java
- **Input Handling:** `Scanner`
- **Dynamic Lists:** `ArrayList`
- **Queue Implementation:** `Queue` and `LinkedList`
- **Sorting Algorithms:** Bubble Sort and Insertion Sort
- **Application Type:** Console-based application

## Data Structures

### Queue

The system uses a `Queue<Integer>` implemented with `LinkedList` to manage vehicles waiting to enter the parking area.

- `add()` adds a ticket number to the end of the queue.
- `poll()` removes and returns the first ticket number.
- `peek()` retrieves the next ticket number without removing it.
- `isEmpty()` checks whether the queue contains waiting vehicles.

### ArrayList

The system maintains parallel `ArrayList` collections to store:

- Ticket numbers
- Vehicle plate numbers
- Vehicle types
- Entry times
- Vehicle statuses

The ticket number acts as the reference for locating the corresponding vehicle information in these lists.

## Sorting Algorithms

### Bubble Sort

Bubble Sort arranges ticket numbers in ascending order, displaying older tickets before newer ones. The algorithm repeatedly compares adjacent ticket numbers and swaps them when they are in the wrong order.

### Insertion Sort

Insertion Sort arranges vehicle records alphabetically by plate number. It inserts each ticket into its correct position in a temporary list based on the corresponding plate number.

Both sorting methods use temporary lists, so they do not change the original waiting queue or the stored vehicle records.

## Vehicle Statuses

The system tracks three vehicle statuses:

- **Waiting** – The vehicle is registered and waiting to enter.
- **Parked** – The vehicle has been admitted into the parking area.
- **Exited** – The vehicle has left the parking area.

A vehicle cannot exit before being admitted, and an already exited vehicle cannot exit again using the same record.

## Program Workflow

1. The user starts the program and selects an option from the menu.
2. When registering a vehicle, the system validates the plate number, vehicle type, and entry time.
3. The system generates a unique ticket number and adds it to the waiting queue.
4. The user can view, search, or sort vehicle records.
5. When a vehicle is admitted, the first ticket in the queue is removed and its status changes to `Parked`.
6. When the vehicle leaves, its status changes to `Exited`.
7. The user can continue managing records or exit the program.

## How to Run the Program

### Requirements

- Java Development Kit (JDK) installed
- IntelliJ IDEA or another Java IDE

### Instructions

1. Create a Java project.
2. Create a Java class named `ParkingTicketSystem`.
3. Paste the source code into `ParkingTicketSystem.java`.
4. Compile and run the program.
5. Select a menu option and follow the instructions displayed in the console.

To run it through a terminal, navigate to the folder containing the source file and execute:

```bash
javac ParkingTicketSystem.java
java ParkingTicketSystem
```

## Limitations

- Vehicle records are stored in memory and are lost when the program terminates.
- Entry times are entered manually by the user.
- The system does not calculate parking fees.
- The system does not enforce a maximum parking capacity.
- The program does not use a database or graphical user interface.
- The queue manages vehicles waiting to enter; it does not represent physical parking-space availability.

## Conclusion

The Parking Ticket Management System demonstrates the practical use of Java collections, FIFO queue operations, input validation, searching, and manual sorting 

algorithms. It provides a simple way to manage vehicle registration and parking status while demonstrating how data structures and algorithms can be applied to a real-world scenario.

# Photos of the System

**Main Menu**
