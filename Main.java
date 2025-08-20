package QTechSolutionsProjects.Project2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create parking lot with 5 slots and ₹20 per hour rate
        ParkingLot parkingLot = new ParkingLot(5, 20.0);

        while (true) {
            System.out.println("\n=== Car Parking Management System ===");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Display Parking Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle Number: ");
                    String number = sc.nextLine();
                    System.out.print("Enter Vehicle Type (Car/Bike): ");
                    String type = sc.nextLine();
                    Vehicle v = new Vehicle(number, type);
                    parkingLot.parkVehicle(v);
                    break;

                case 2:
                    System.out.print("Enter Slot Number to remove vehicle: ");
                    int slot = sc.nextInt();
                    parkingLot.removeVehicle(slot);
                    break;

                case 3:
                    parkingLot.displayStatus();
                    break;

                case 4:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

