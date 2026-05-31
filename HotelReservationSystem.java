import java.io.*;
import java.util.*;

public class HotelReservationSystem {

    static Scanner sc = new Scanner(System.in);

    static String[] rooms = {"Standard", "Deluxe", "Suite"};
    static boolean[] booked = {false, false, false};

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelBooking();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void viewRooms() {

        System.out.println("\nAvailable Rooms:");

        for (int i = 0; i < rooms.length; i++) {

            if (!booked[i]) {
                System.out.println((i + 1) + ". " + rooms[i]);
            }
        }
    }

    static void bookRoom() {

        viewRooms();

        System.out.print("Select Room Number: ");
        int room = sc.nextInt();

        if (booked[room - 1]) {
            System.out.println("Room already booked.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.println("Payment Successful.");

        booked[room - 1] = true;

        try {
            FileWriter fw = new FileWriter("bookings.txt", true);
            fw.write(name + " - " + rooms[room - 1] + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving booking.");
        }

        System.out.println("Room Booked Successfully.");
    }

    static void cancelBooking() {

        System.out.print("Enter Room Number to Cancel: ");
        int room = sc.nextInt();

        booked[room - 1] = false;

        System.out.println("Reservation Cancelled.");
    }

    static void viewBookings() {

        try {

            File file = new File("bookings.txt");

            if (!file.exists()) {
                System.out.println("No bookings found.");
                return;
            }

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }
}