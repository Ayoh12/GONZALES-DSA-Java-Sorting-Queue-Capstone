
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ParkingTicketSystem {

    // INPUT
    static Scanner Input = new Scanner(System.in);

    // VEHICLE RECORDS
    static ArrayList<Integer> TicketNumbers = new ArrayList<>();
    static ArrayList<String> PlateNumbers = new ArrayList<>();
    static ArrayList<String> VehicleTypes = new ArrayList<>();
    static ArrayList<String> EntryTimes = new ArrayList<>();
    static ArrayList<String> VehicleStatuses = new ArrayList<>();

    // WAITING QUEUE
    static Queue<Integer> WaitingQueue = new LinkedList<>();

    // SYSTEM VARIABLES
    static int NextTicketNumber = 1;
    static boolean IsRunning = true;

    public static void main(String[] args) {

        while (IsRunning) {

            DisplayMenu();
            int Choice = GetMenuChoice();

            if (Choice == 1) {
                RegisterVehicle();

            } else if (Choice == 2) {
                ViewWaitingQueue();

            } else if (Choice == 3) {
                AdmitNextVehicle();

            } else if (Choice == 4) {
                ViewNextVehicle();

            } else if (Choice == 5) {
                SearchVehicle();

            } else if (Choice == 6) {
                ViewAllVehicles();

            } else if (Choice == 7) {
                SortTicketsAscending();

            } else if (Choice == 8) {
                SortPlatesAlphabetically();

            } else if (Choice == 9) {
                ExitParking();

            } else if (Choice == 10) {
                System.out.println("Thank you for using the system.");
                IsRunning = false;

            } else {
                System.out.println("Invalid choice. Please select 1-10.");
            }
        }

        Input.close();
    }

    // METHOD 1: DISPLAY MENU
    public static void DisplayMenu() {

        System.out.println("\n======================================");
        System.out.println("       PARKING TICKET SYSTEM");
        System.out.println("======================================");
        System.out.println("1. Register Vehicle");
        System.out.println("2. View Waiting Queue");
        System.out.println("3. Admit Next Vehicle");
        System.out.println("4. View Next Vehicle");
        System.out.println("5. Search Vehicle");
        System.out.println("6. View All Vehicle Records");
        System.out.println("7. Sort by Ticket Number ");
        System.out.println("8. Sort by Plate Number ");
        System.out.println("9. Exit Parking");
        System.out.println("10. Exit Program");
        System.out.println("======================================");
        System.out.print("Enter your choice: ");
    }

    // METHOD 2: VALIDATE MENU INPUT
    public static int GetMenuChoice() {

        try {
            return Integer.parseInt(Input.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return 0;
        }
    }

    // METHOD 3: REGISTER VEHICLE
    public static void RegisterVehicle() {

        System.out.println("\n--- REGISTER VEHICLE ---");
        System.out.print("Enter plate number: ");

        String PlateNumber = Input.nextLine().trim().toUpperCase();

        if (PlateNumber.isEmpty()) {
            System.out.println("Plate number cannot be empty.");
            return;
        }

        // PREVENT DUPLICATE ACTIVE PLATES
        for (int I = 0; I < PlateNumbers.size(); I++) {

            if (PlateNumbers.get(I).equals(PlateNumber)
                    && !VehicleStatuses.get(I).equals("Exited")) {

                System.out.println(
                    "This vehicle already has an active parking record."
                );
                return;
            }
        }

        System.out.println("\nSelect vehicle type:");
        System.out.println("1. Motorcycle");
        System.out.println("2. Car");
        System.out.println("3. Van");
        System.out.println("4. Truck");
        System.out.print("Enter vehicle type: ");

        int TypeChoice;

        try {
            TypeChoice = Integer.parseInt(Input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid vehicle type.");
            return;
        }

        String VehicleType;

        if (TypeChoice == 1) {
            VehicleType = "Motorcycle";
        } else if (TypeChoice == 2) {
            VehicleType = "Car";
        } else if (TypeChoice == 3) {
            VehicleType = "Van";
        } else if (TypeChoice == 4) {
            VehicleType = "Truck";
        } else {
            System.out.println("Invalid vehicle type.");
            return;
        }

        System.out.print("Enter entry time (e.g., 09:30 AM): ");
        String EntryTime = Input.nextLine().trim();

        if (EntryTime.isEmpty()) {
            System.out.println("Entry time cannot be empty.");
            return;
        }

        int NewTicketNumber = NextTicketNumber;

        TicketNumbers.add(NewTicketNumber);
        PlateNumbers.add(PlateNumber);
        VehicleTypes.add(VehicleType);
        EntryTimes.add(EntryTime);
        VehicleStatuses.add("Waiting");

        WaitingQueue.add(NewTicketNumber);

        System.out.println("\nVehicle registered successfully!");
        System.out.printf("Ticket Number: P-%03d%n", NewTicketNumber);
        System.out.println("Plate Number: " + PlateNumber);
        System.out.println("Vehicle Type: " + VehicleType);
        System.out.println("Entry Time: " + EntryTime);
        System.out.println("Queue Position: " + WaitingQueue.size());

        NextTicketNumber++;
    }

    // METHOD 4: VIEW WAITING QUEUE
    public static void ViewWaitingQueue() {

        System.out.println("\n--- WAITING QUEUE ---");

        if (WaitingQueue.isEmpty()) {
            System.out.println("No vehicles are waiting.");
            return;
        }

        System.out.println("Position | Ticket | Plate Number");
        System.out.println("--------------------------------");

        int Position = 1;

        for (int TicketNumber : WaitingQueue) {

            int VehicleIndex = TicketNumbers.indexOf(TicketNumber);

            System.out.printf(
                "%-8d | P-%03d | %s%n",
                Position,
                TicketNumber,
                PlateNumbers.get(VehicleIndex)
            );

            Position++;
        }

        System.out.println("--------------------------------");
        System.out.println("Total Waiting: " + WaitingQueue.size());
    }

    // METHOD 5: ADMIT NEXT VEHICLE
    public static void AdmitNextVehicle() {

        System.out.println("\n--- ADMIT NEXT VEHICLE ---");

        if (WaitingQueue.isEmpty()) {
            System.out.println("No vehicles are waiting to enter.");
            return;
        }

        int TicketNumber = WaitingQueue.poll();
        int VehicleIndex = TicketNumbers.indexOf(TicketNumber);

        VehicleStatuses.set(VehicleIndex, "Parked");

        System.out.println("Vehicle admitted successfully!");
        DisplayVehicleRecord(VehicleIndex);

        System.out.println(
            "Vehicles still waiting: " + WaitingQueue.size()
        );
    }

    // METHOD 6: VIEW NEXT VEHICLE WITHOUT REMOVING IT
    public static void ViewNextVehicle() {

        System.out.println("\n--- NEXT VEHICLE ---");

        if (WaitingQueue.isEmpty()) {
            System.out.println("No vehicles are waiting.");
            return;
        }

        int TicketNumber = WaitingQueue.peek();
        int VehicleIndex = TicketNumbers.indexOf(TicketNumber);

        DisplayVehicleRecord(VehicleIndex);
    }

    // METHOD 7: SEARCH VEHICLE BY TICKET OR PLATE
    public static void SearchVehicle() {

        System.out.println("\n--- SEARCH VEHICLE ---");
        System.out.println("1. Search by Ticket Number");
        System.out.println("2. Search by Plate Number");
        System.out.print("Choose search method: ");

        int SearchChoice;

        try {
            SearchChoice = Integer.parseInt(Input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        int VehicleIndex = -1;

        if (SearchChoice == 1) {

            System.out.print("Enter ticket number (digits only): ");

            try {
                int SearchedTicket =
                    Integer.parseInt(Input.nextLine());

                VehicleIndex = TicketNumbers.indexOf(SearchedTicket);

            } catch (NumberFormatException e) {
                System.out.println("Invalid ticket number.");
                return;
            }

        } else if (SearchChoice == 2) {

            System.out.print("Enter plate number: ");
            String SearchedPlate =
                Input.nextLine().trim().toUpperCase();

            for (int I = 0; I < PlateNumbers.size(); I++) {

                if (PlateNumbers.get(I).equals(SearchedPlate)
                        && !VehicleStatuses.get(I).equals("Exited")) {

                    VehicleIndex = I;
                    break;
                }
            }

        } else {
            System.out.println("Invalid search option.");
            return;
        }

        if (VehicleIndex == -1) {
            System.out.println("Vehicle record not found.");
            return;
        }

        DisplayVehicleRecord(VehicleIndex);
    }

    // METHOD 8: VIEW ALL VEHICLE RECORDS
    public static void ViewAllVehicles() {

        System.out.println("\n--- ALL VEHICLE RECORDS ---");

        if (TicketNumbers.isEmpty()) {
            System.out.println("No vehicle records available.");
            return;
        }

        for (int I = 0; I < TicketNumbers.size(); I++) {
            DisplayVehicleRecord(I);
            System.out.println("--------------------------------");
        }
    }

    // METHOD 9: BUBBLE SORT BY TICKET NUMBER
    public static void SortTicketsAscending() {

        System.out.println("\n--- SORT BY TICKET NUMBER ---");

        if (TicketNumbers.isEmpty()) {
            System.out.println("No vehicle records to sort.");
            return;
        }

        // COPY ALL TICKET NUMBERS
        ArrayList<Integer> SortedTickets = new ArrayList<>();

        for (int TicketNumber : TicketNumbers) {
            SortedTickets.add(TicketNumber);
        }

        // BUBBLE SORT IN ASCENDING ORDER
        for (int I = 0; I < SortedTickets.size() - 1; I++) {

            for (int J = 0; J < SortedTickets.size() - 1 - I; J++) {

                if (SortedTickets.get(J) > SortedTickets.get(J + 1)) {

                    int Temporary = SortedTickets.get(J);

                    SortedTickets.set(J, SortedTickets.get(J + 1));
                    SortedTickets.set(J + 1, Temporary);
                }
            }
        }

        System.out.println("Ticket | Plate Number | Status");
        System.out.println("--------------------------------");

        for (int TicketNumber : SortedTickets) {

            int VehicleIndex = TicketNumbers.indexOf(TicketNumber);

            System.out.printf(
                "P-%03d  | %-12s | %s%n",
                TicketNumber,
                PlateNumbers.get(VehicleIndex),
                VehicleStatuses.get(VehicleIndex)
            );
        }
    }

    // METHOD 10: INSERTION SORT BY PLATE NUMBER
    public static void SortPlatesAlphabetically() {

        System.out.println("\n--- SORT BY PLATE NUMBER ---");

        if (TicketNumbers.isEmpty()) {
            System.out.println("No vehicle records to sort.");
            return;
        }

        // COPY TICKET NUMBERS
        ArrayList<Integer> SortedTickets = new ArrayList<>();

        for (int TicketNumber : TicketNumbers) {
            SortedTickets.add(TicketNumber);
        }

        // INSERTION SORT USING PLATE NUMBERS
        for (int I = 1; I < SortedTickets.size(); I++) {

            int KeyTicket = SortedTickets.get(I);
            int KeyIndex = TicketNumbers.indexOf(KeyTicket);
            String KeyPlate = PlateNumbers.get(KeyIndex);

            int J = I - 1;

            while (J >= 0) {

                int PreviousTicket = SortedTickets.get(J);
                int PreviousIndex = TicketNumbers.indexOf(PreviousTicket);
                String PreviousPlate = PlateNumbers.get(PreviousIndex);

                if (PreviousPlate.compareToIgnoreCase(KeyPlate) > 0) {

                    SortedTickets.set(J + 1, SortedTickets.get(J));
                    J--;

                } else {
                    break;
                }
            }

            SortedTickets.set(J + 1, KeyTicket);
        }

        System.out.println("Plate Number | Ticket | Status");
        System.out.println("--------------------------------");

        for (int TicketNumber : SortedTickets) {

            int VehicleIndex = TicketNumbers.indexOf(TicketNumber);

            System.out.printf(
                "%-12s | P-%03d  | %s%n",
                PlateNumbers.get(VehicleIndex),
                TicketNumber,
                VehicleStatuses.get(VehicleIndex)
            );
        }
    }

    // METHOD 11: EXIT PARKING
    public static void ExitParking() {

        System.out.println("\n--- EXIT PARKING ---");
        System.out.print("Enter ticket number (digits only): ");

        int TicketNumber;

        try {
            TicketNumber = Integer.parseInt(Input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ticket number.");
            return;
        }

        int VehicleIndex = TicketNumbers.indexOf(TicketNumber);

        if (VehicleIndex == -1) {
            System.out.println("Ticket number not found.");
            return;
        }

        String CurrentStatus = VehicleStatuses.get(VehicleIndex);

        if (CurrentStatus.equals("Waiting")) {
            System.out.println(
                "This vehicle has not entered the parking area yet."
            );
            return;
        }

        if (CurrentStatus.equals("Exited")) {
            System.out.println("This vehicle has already exited.");
            return;
        }

        VehicleStatuses.set(VehicleIndex, "Exited");

        System.out.println("Vehicle exit recorded successfully!");
        DisplayVehicleRecord(VehicleIndex);
    }

    // METHOD 12: DISPLAY ONE VEHICLE RECORD
    public static void DisplayVehicleRecord(int VehicleIndex) {

        System.out.printf(
            "Ticket Number: P-%03d%n",
            TicketNumbers.get(VehicleIndex)
        );

        System.out.println(
            "Plate Number: " + PlateNumbers.get(VehicleIndex)
        );

        System.out.println(
            "Vehicle Type: " + VehicleTypes.get(VehicleIndex)
        );

        System.out.println(
            "Entry Time: " + EntryTimes.get(VehicleIndex)
        );

        System.out.println(
            "Status: " + VehicleStatuses.get(VehicleIndex)
        );
    }
}
