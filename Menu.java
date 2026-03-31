// Menu.java
// This class handles all the user interaction and menus
// It reads input from the user and calls the right methods

import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private BookingManager manager;

    public Menu() {
        scanner = new Scanner(System.in);
        manager = new BookingManager();
    }

    // Start the application - shows the main menu in a loop
    public void start() {
        printWelcomeBanner();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleViewMovies();
                    break;
                case 2:
                    handleViewShows();
                    break;
                case 3:
                    handleBookTicket();
                    break;
                case 4:
                    handleCancelBooking();
                    break;
                case 5:
                    handleViewAllBookings();
                    break;
                case 6:
                    System.out.println("\n  Thanks for using CineBook! Goodbye :)\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n  [!] Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    // --- Menu option handlers ---

    private void handleViewMovies() {
        manager.listAllMovies();
    }

    private void handleViewShows() {
        manager.listAllMovies();
        int movieId = readIntInput("\n  Enter Movie ID to see its shows: ");

        if (manager.findMovieById(movieId) == null) {
            System.out.println("  [Error] Movie not found.");
            return;
        }

        manager.listShowsForMovie(movieId);
    }

    private void handleBookTicket() {
        System.out.println("\n  ===== BOOK A TICKET =====");

        // Step 1: Show movies
        manager.listAllMovies();
        int movieId = readIntInput("\n  Enter Movie ID: ");

        if (manager.findMovieById(movieId) == null) {
            System.out.println("  [Error] Movie not found.");
            return;
        }

        // Step 2: Show available shows
        manager.listShowsForMovie(movieId);
        int showId = readIntInput("\n  Enter Show ID: ");

        Show selectedShow = manager.findShowById(showId);
        if (selectedShow == null) {
            System.out.println("  [Error] Show not found.");
            return;
        }

        // Make sure they picked a show for the right movie
        if (selectedShow.getMovie().getMovieId() != movieId) {
            System.out.println("  [Error] That show doesn't match the selected movie.");
            return;
        }

        // Step 3: Show seat layout and pick a seat
        selectedShow.displaySeatLayout();
        System.out.println("  Available seats: " + selectedShow.getAvailableSeatsCount());
        int seatNumber = readIntInput("  Enter Seat Number (1 to " + selectedShow.getTotalSeats() + "): ");

        // Step 4: Get customer name
        System.out.print("  Enter your name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("  [Error] Name cannot be empty.");
            return;
        }

        // Step 5: Confirm and book
        System.out.println("\n  Booking Summary:");
        System.out.println("    Movie    : " + selectedShow.getMovie().getTitle());
        System.out.println("    Show     : " + selectedShow.getShowTime());
        System.out.println("    Seat     : " + seatNumber);
        System.out.println("    Price    : Rs. " + selectedShow.getTicketPrice());
        System.out.print("\n  Confirm booking? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equals("yes")) {
            System.out.println("  Booking cancelled by user.");
            return;
        }

        Booking booking = manager.bookTicket(name, showId, seatNumber);
        if (booking != null) {
            booking.printTicket();
        }
    }

    private void handleCancelBooking() {
        System.out.println("\n  ===== CANCEL BOOKING =====");
        int bookingId = readIntInput("  Enter Booking ID to cancel: ");
        manager.cancelBooking(bookingId);
    }

    private void handleViewAllBookings() {
        manager.listAllBookings();
    }

    // --- Helper methods ---

    private void printWelcomeBanner() {
        System.out.println("\n  ============================================");
        System.out.println("         WELCOME TO CINEBOOK                 ");
        System.out.println("       Your Simple Movie Ticket Booker        ");
        System.out.println("  ============================================\n");
    }

    private void printMainMenu() {
        System.out.println("\n  ---- MAIN MENU ----");
        System.out.println("  1. View All Movies");
        System.out.println("  2. View Shows for a Movie");
        System.out.println("  3. Book a Ticket");
        System.out.println("  4. Cancel a Booking");
        System.out.println("  5. View All Bookings");
        System.out.println("  6. Exit");
        System.out.println("  -------------------");
    }

    // Reads an integer safely - keeps asking if input is invalid
    private int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a valid number.");
            }
        }
    }
}
