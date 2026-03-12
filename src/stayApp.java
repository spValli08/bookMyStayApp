/**
 * UseCase4RoomSearch.java
 * This class demonstrates read-only room search functionality for
 * the Hotel Booking Management System. Guests can view available
 * room types and their details without modifying the system state.
 * Key Concepts:
 * - Read-only access to centralized inventory
 * - Separation of concerns between search and booking
 * - Filtering unavailable room types
 * - Defensive programming to ensure valid data usage
 *
 * @author Sreevalli
 * @version 4.0
 */

import java.util.HashMap;
import java.util.Map;

// Abstract Room class
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

    // Get current availability (read-only)
    public int getAvailability(String roomName) {
        return inventory.getOrDefault(roomName, 0);
    }

    // Display all room availability (read-only)
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " | Available: " + entry.getValue());
        }
        System.out.println();
    }
}

// Search Service for read-only room search
class RoomSearchService {
    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Display available rooms
    public void displayAvailableRooms(Room[] rooms) {
        System.out.println("\nAvailable Rooms for Booking:");
        boolean anyAvailable = false;
        for (Room room : rooms) {
            int availableCount = inventory.getAvailability(room.getName());
            if (availableCount > 0) { // Only show rooms with availability
                room.displayRoomInfo();
                System.out.println("Available: " + availableCount + "\n");
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No rooms available at the moment.\n");
        }
    }
}

// Main application entry point
public class stayApp {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App!");
        System.out.println("Hotel Booking Management System v4.0");
        System.out.println("Performing read-only room search...\n");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        Room[] allRooms = { single, doubleRoom, suite };

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(single.getName(), 5);
        inventory.addRoomType(doubleRoom.getName(), 0); // simulate fully booked
        inventory.addRoomType(suite.getName(), 2);

        // Display inventory (optional)
        inventory.displayInventory();

        // Create search service and display available rooms
        RoomSearchService searchService = new RoomSearchService(inventory);
        searchService.displayAvailableRooms(allRooms);

        System.out.println("Room search complete. Inventory remains unchanged.");
    }
}