/**
 * UseCase3InventorySetup.java
 *
 * This class demonstrates centralized room inventory management
 * for the Hotel Booking Management System using HashMap.
 * It replaces scattered availability variables with a single,
 * consistent data structure and encapsulates inventory logic.
 *
 * Key Concepts:
 * - Centralized inventory using HashMap<String, Integer>
 * - Encapsulation of inventory operations
 * - Single source of truth for availability
 * - Scalability for adding new room types
 *
 * @author Sreevalli
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

// Abstract Room class from previous use case
abstract class Room {
    private String name;
    private int numberOfBeds;
    private double pricePerNight;

    public Room(String name, int numberOfBeds, double pricePerNight) {
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public abstract void displayRoomInfo();
}

// Concrete Room classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println(getName() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 90.0);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println(getName() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 150.0);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println(getName() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

// Centralized Room Inventory class
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register a room type with initial availability
    public void addRoomType(String roomName, int count) {
        inventory.put(roomName, count);
    }

    // Get current availability
    public int getAvailability(String roomName) {
        return inventory.getOrDefault(roomName, 0);
    }

    // Update availability (for bookings or cancellations)
    public void updateAvailability(String roomName, int newCount) {
        if (inventory.containsKey(roomName)) {
            inventory.put(roomName, newCount);
        } else {
            System.out.println("Room type not found in inventory.");
        }
    }

    // Display all room availability
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " | Available: " + entry.getValue());
        }
        System.out.println();
    }
}

// Main application entry point
public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App!");
        System.out.println("Hotel Booking Management System v3.1");
        System.out.println("Setting up centralized room inventory...\n");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room info
        single.displayRoomInfo();
        doubleRoom.displayRoomInfo();
        suite.displayRoomInfo();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(single.getName(), 5);
        inventory.addRoomType(doubleRoom.getName(), 3);
        inventory.addRoomType(suite.getName(), 2);

        // Display current inventory
        inventory.displayInventory();

        // Example update
        System.out.println("Booking 1 Double Room...");
        int updatedDouble = inventory.getAvailability(doubleRoom.getName()) - 1;
        inventory.updateAvailability(doubleRoom.getName(), updatedDouble);

        // Display inventory after update
        inventory.displayInventory();

        System.out.println("Inventory setup complete.");
    }
}