import java.util.HashMap;
import java.util.Map;

public class BookingManager {
    private Map<Integer, Booking> bookings = new HashMap<>();

    public void createBooking(Flight flight, Map<Passenger, String> passenger, Payment payment){
        Booking booking = new Booking(flight, passenger, payment);
        bookings.put(booking.getBookingReference(), booking);
        booking.displayBookingDetails();
    }

    public void viewBooking(int bookingReference){
        bookings.get(bookingReference).displayBookingDetails();
    }

    public void cancelBooking(int bookingReference){
        bookings.get(bookingReference).cancelBooking();
        bookings.get(bookingReference).setCancelled(true);
        System.out.println("Booking Cancelled");
    }
}
