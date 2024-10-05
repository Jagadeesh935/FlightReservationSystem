import java.util.List;
import java.util.Map;

public class Booking {
    private static int idTrancker;
    private int bookingReference;
    private Flight flight;
    private Map<Passenger, String> passenger;
    private Payment payment;
    private boolean isCancelled = false;


    public Booking(Flight flight, Map<Passenger, String> passenger, Payment payment) {
        this.bookingReference = ++idTrancker;
        this.flight = flight;
        this.passenger = passenger;
        this.passenger.forEach(flight::bookSeat);
        this.payment = payment;
    }



    public void displayBookingDetails(){
        System.out.println("Booking Reference Id : " + this.bookingReference);
        System.out.println("Flight name          : " + this.flight.getName());
        System.out.println("Flight number        : " + this.flight.getFlightNumber());
        System.out.println("From                 : " + this.flight.getDepartureCity());
        System.out.println("To                   : " + this.flight.getDestinationCity());
        System.out.println("Flight date          : " + this.flight.getDate().getTime());
        System.out.println("Total amount         : " + this.payment.getPayment());
        System.out.println("Payment status       : " + (this.payment.isPaymentStatus()?"Done":"Pending"));
        System.out.println("Booking status       : " + ((isCancelled)?"Cancelled":"Confirmed"));
        System.out.println("List of Passengers:");
        System.out.println("        Name          Age   seat ");
        passenger.forEach((p,v) -> System.out.printf("\n%-20s %5d %-6s", p.getPassengerName(), p.getAge(), v));

    }

    public void cancelBooking(){
        passenger.values().forEach(seat -> flight.cancelSeat(seat));
    }

    public int getBookingReference() {
        return bookingReference;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setBookingReference(int bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Map<Passenger, String> getPassenger() {
        return passenger;
    }

    public void setPassenger(Map<Passenger, String> passenger) {
        this.passenger = passenger;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
