// BookingManager.java
// This class manages all the movies, shows, and bookings
// Think of it as the "brain" of the whole system

import java.util.ArrayList;

public class BookingManager {

    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;
    private ArrayList<Booking> bookings;

    private int nextBookingId = 101; // booking IDs start from 101

    public BookingManager() {
        movies = new ArrayList<>();
        shows = new ArrayList<>();
        bookings = new ArrayList<>();

        // Load some sample movies and shows when system starts
        loadSampleData();
    }

    // Pre-load movies and shows so there's something to work with
    private void loadSampleData() {
        // Add movies
        Movie m1 = new Movie(1, "Interstellar",     "Sci-Fi",  169, 8.6);
        Movie m2 = new Movie(2, "The Dark Knight",  "Action",  152, 9.0);
        Movie m3 = new Movie(3, "Inception",        "Thriller",148, 8.8);
        Movie m4 = new Movie(4, "Avengers: Endgame","Action",  181, 8.4);

        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
        movies.add(m4);

        // Add shows for these movies
        shows.add(new Show(1, m1, "10:00 AM", 30, 180.0));
        shows.add(new Show(2, m1, "03:00 PM", 30, 200.0));
        shows.add(new Show(3, m2, "12:00 PM", 30, 160.0));
        shows.add(new Show(4, m2, "07:00 PM", 30, 220.0));
        shows.add(new Show(5, m3, "01:30 PM", 30, 190.0));
        shows.add(new Show(6, m4, "06:00 PM", 30, 250.0));
    }

    // --- Movie related methods ---

    public void listAllMovies() {
        System.out.println("\n  ===== MOVIES NOW SHOWING =====");
        if (movies.isEmpty()) {
            System.out.println("  No movies available right now.");
            return;
        }
        for (Movie m : movies) {
            System.out.println("\n  ------------------------------");
            m.displayMovieInfo();
        }
        System.out.println("  ------------------------------");
    }

    public Movie findMovieById(int id) {
        for (Movie m : movies) {
            if (m.getMovieId() == id) {
                return m;
            }
        }
        return null;
    }

    // --- Show related methods ---

    public void listShowsForMovie(int movieId) {
        System.out.println("\n  ===== AVAILABLE SHOWS =====");
        boolean found = false;
        for (Show s : shows) {
            if (s.getMovie().getMovieId() == movieId) {
                System.out.println("\n  ----------------------------");
                s.displayShowInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No shows available for this movie.");
        }
        System.out.println("  ----------------------------");
    }

    public Show findShowById(int id) {
        for (Show s : shows) {
            if (s.getShowId() == id) {
                return s;
            }
        }
        return null;
    }

    // --- Booking related methods ---

    // Book a ticket and return the Booking object if successful
    public Booking bookTicket(String customerName, int showId, int seatNumber) {
        Show show = findShowById(showId);

        if (show == null) {
            System.out.println("  [Error] Show not found!");
            return null;
        }

        boolean seatBooked = show.bookSeat(seatNumber);
        if (!seatBooked) {
            System.out.println("  [Error] Seat " + seatNumber + " is not available. Please choose another seat.");
            return null;
        }

        Booking booking = new Booking(nextBookingId++, customerName, show, seatNumber);
        bookings.add(booking);
        return booking;
    }

    // Cancel a booking by booking ID
    public boolean cancelBooking(int bookingId) {
        Booking toCancel = null;

        for (Booking b : bookings) {
            if (b.getBookingId() == bookingId) {
                toCancel = b;
                break;
            }
        }

        if (toCancel == null) {
            System.out.println("  [Error] Booking ID " + bookingId + " not found.");
            return false;
        }

        // Free up the seat
        toCancel.getShow().cancelSeat(toCancel.getSeatNumber());
        bookings.remove(toCancel);

        System.out.println("  Booking " + bookingId + " has been cancelled successfully.");
        System.out.println("  Seat " + toCancel.getSeatNumber() + " is now available again.");
        return true;
    }

    // Show all bookings made so far
    public void listAllBookings() {
        System.out.println("\n  ===== ALL BOOKINGS =====");
        if (bookings.isEmpty()) {
            System.out.println("  No bookings found.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println("\n  ----------------------------");
            System.out.println("  Booking ID  : " + b.getBookingId());
            System.out.println("  Customer    : " + b.getCustomerName());
            System.out.println("  Movie       : " + b.getShow().getMovie().getTitle());
            System.out.println("  Show Time   : " + b.getShow().getShowTime());
            System.out.println("  Seat No.    : " + b.getSeatNumber());
            System.out.println("  Amount Paid : Rs. " + b.getAmountPaid());
        }
        System.out.println("  ----------------------------");
    }
}
