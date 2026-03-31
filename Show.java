// Show.java
// A Show is a particular screening of a movie at a specific time
// It also keeps track of which seats are booked

public class Show {

    private int showId;
    private Movie movie;
    private String showTime;
    private int totalSeats;
    private boolean[] seats; // true = booked, false = available
    private double ticketPrice;

    public Show(int showId, Movie movie, String showTime, int totalSeats, double ticketPrice) {
        this.showId = showId;
        this.movie = movie;
        this.showTime = showTime;
        this.totalSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.seats = new boolean[totalSeats + 1]; // index 0 unused, seats start from 1
    }

    // Check if a particular seat number is available
    public boolean isSeatAvailable(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            return false;
        }
        return !seats[seatNumber];
    }

    // Book a seat - returns true if booking was successful
    public boolean bookSeat(int seatNumber) {
        if (!isSeatAvailable(seatNumber)) {
            return false;
        }
        seats[seatNumber] = true;
        return true;
    }

    // Cancel a seat booking - returns true if cancellation worked
    public boolean cancelSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            return false;
        }
        if (!seats[seatNumber]) {
            return false; // seat was not even booked
        }
        seats[seatNumber] = false;
        return true;
    }

    // Count how many seats are still available
    public int getAvailableSeatsCount() {
        int count = 0;
        for (int i = 1; i <= totalSeats; i++) {
            if (!seats[i]) {
                count++;
            }
        }
        return count;
    }

    // Print a visual layout of all seats
    public void displaySeatLayout() {
        System.out.println("\n  === SEAT LAYOUT (O = Available, X = Booked) ===");
        System.out.print("  ");
        for (int i = 1; i <= totalSeats; i++) {
            if (seats[i]) {
                System.out.print("[X] ");
            } else {
                System.out.print("[O] ");
            }
            // New line after every 10 seats for readability
            if (i % 10 == 0) {
                System.out.println();
                System.out.print("  ");
            }
        }
        System.out.println();
    }

    // Display show details
    public void displayShowInfo() {
        System.out.println("  Show ID   : " + showId);
        System.out.println("  Movie     : " + movie.getTitle());
        System.out.println("  Time      : " + showTime);
        System.out.println("  Price     : Rs. " + ticketPrice);
        System.out.println("  Available : " + getAvailableSeatsCount() + " / " + totalSeats + " seats");
    }

    // Getters
    public int getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
