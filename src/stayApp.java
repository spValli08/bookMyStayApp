/**
 * UseCase2RoomInitialization.java
 * This class demonstrates the initialization of different room types
 * in the Hotel Booking Management System. It introduces abstraction,
 * inheritance, and static room availability representation.
 * Key Concepts:
 * - Abstract class for general Room definition
 * - Concrete classes: SingleRoom, DoubleRoom, SuiteRoom
 * - Polymorphism via Room references
 * - Encapsulation of room attributes
 * @author Sreevalli
 * @version 2.1
 */

// Abstract class representing a general Room
abstract class Room {
    private String name;
    private int numberOfBeds;
    private double pricePerNight;

    // Constructor
    public Room(String name, int numberOfBeds, double pricePerNight) {
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Abstract method to display room details
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
public class stayApp {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay App!");
        System.out.println("Hotel Booking Management System v2.1");
        System.out.println("Initializing Room Types...\n");

        // Static availability variables
        int availableSingleRooms = 5;
        int availableDoubleRooms = 3;
        int availableSuiteRooms = 2;

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details and availability
        single.displayRoomInfo();
        System.out.println("Available: " + availableSingleRooms + "\n");

        doubleRoom.displayRoomInfo();
        System.out.println("Available: " + availableDoubleRooms + "\n");

        suite.displayRoomInfo();
        System.out.println("Available: " + availableSuiteRooms + "\n");

        System.out.println("Room initialization complete.");
    }
}