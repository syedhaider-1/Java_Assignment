// Booking.java
// This class holds all details of a confirmed booking

public class Booking {

    private int bookingId;
    private String customerName;
    private Show show;
    private int seatNumber;
    private double amountPaid;

    public Booking(int bookingId, String customerName, Show show, int seatNumber) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.show = show;
        this.seatNumber = seatNumber;
        this.amountPaid = show.getTicketPrice();
    }

    // Print a nicely formatted ticket
    public void printTicket() {
        System.out.println("\n  ========================================");
        System.out.println("           *** BOOKING CONFIRMED ***      ");
        System.out.println("  ========================================");
        System.out.println("  Booking ID   : " + bookingId);
        System.out.println("  Customer     : " + customerName);
        System.out.println("  Movie        : " + show.getMovie().getTitle());
        System.out.println("  Show Time    : " + show.getShowTime());
        System.out.println("  Seat Number  : " + seatNumber);
        System.out.println("  Amount Paid  : Rs. " + amountPaid);
        System.out.println("  ========================================");
        System.out.println("       Enjoy the movie! Have fun :)       ");
        System.out.println("  ========================================\n");
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Show getShow() {
        return show;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}
