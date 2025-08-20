package QTechSolutionsProjects.Project2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private int totalSlots;
    private List<Integer> availableSlots;
    private Map<Integer, Vehicle> parkedVehicles;
    private double hourlyRate;

    // Constructor
    public ParkingLot(int totalSlots, double hourlyRate) {
        this.totalSlots = totalSlots;
        this.hourlyRate = hourlyRate;
        this.availableSlots = new ArrayList<>();
        this.parkedVehicles = new HashMap<>();

        // Fill available slots
        for (int i = 1; i <= totalSlots; i++) {
            availableSlots.add(i);
        }
    }

    // Park a vehicle
    public boolean parkVehicle(Vehicle v) {
        if (availableSlots.isEmpty()) {
            System.out.println("Parking Lot is Full!");
            return false;
        }

        int slot = availableSlots.remove(0);  // take first available slot
        v.setEntryTime(LocalDateTime.now());
        parkedVehicles.put(slot, v);

        System.out.println("Vehicle " + v.getVehicleNumber() + " parked at slot " + slot);
        return true;
    }

    // Remove vehicle and calculate fee
    public double removeVehicle(int slotNumber) {
        if (!parkedVehicles.containsKey(slotNumber)) {
            System.out.println("No vehicle found in slot " + slotNumber);
            return 0.0;
        }

        Vehicle v = parkedVehicles.remove(slotNumber);
        v.setExitTime(LocalDateTime.now());
        availableSlots.add(slotNumber);
        Collections.sort(availableSlots); // keep slots ordered

        double fee = calculateFee(v);
        System.out.println("Vehicle " + v.getVehicleNumber() + " removed from slot " + slotNumber);
        System.out.println("Parking Fee: ₹" + fee);

        return fee;
    }

    // Display status of parking lot
    public void displayStatus() {
        System.out.println("Total Slots: " + totalSlots);
        System.out.println("Available Slots: " + availableSlots.size());
        System.out.println("Occupied Slots: " + parkedVehicles.size());

        for (Map.Entry<Integer, Vehicle> entry : parkedVehicles.entrySet()) {
            System.out.println("Slot " + entry.getKey() + ": Vehicle " + entry.getValue().getVehicleNumber());
        }
    }

    // Calculate parking fee
    private double calculateFee(Vehicle v) {
        Duration duration = Duration.between(v.getEntryTime(), v.getExitTime());
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0) { 
            hours++; // charge for partial hour
        }
        return hours * hourlyRate;
    }

    public boolean isFull() {
        return availableSlots.isEmpty();
    }

    public boolean isEmpty() {
        return parkedVehicles.isEmpty();
    }
}
