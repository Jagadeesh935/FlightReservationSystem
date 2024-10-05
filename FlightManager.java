import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FlightManager {
    private List<Flight> flights = new ArrayList<>();

    public void addFlight(Flight flight){
        flights.add(flight);
        System.out.println("New flight added");
    }

    public void removeFlight(int flightNumber){
        for(Flight f : flights){
            if (f.getFlightNumber() == flightNumber){
                flights.remove(f);
                break;
            }
        }
    }

    public void searchFlights(String departureCuity, String destinationCity, Calendar date){
        flights.stream()
                .filter(flight -> flight.getDepartureCity().equalsIgnoreCase(departureCuity)
                        && flight.getDestinationCity().equalsIgnoreCase(destinationCity)
                        && date.get(Calendar.DAY_OF_MONTH) == flight.getDate().get(Calendar.DAY_OF_MONTH)
                        && date.get(Calendar.MONTH) == flight.getDate().get(Calendar.MONTH)
                        && date.get(Calendar.YEAR) == flight.getDate().get(Calendar.YEAR)
                )
                .forEach(System.out::println);
    }

    public Flight findFlightByNumber(int flightNumber){
        return flights.stream()
                .filter(flight -> flight.getFlightNumber() == flightNumber)
                .limit(1)
                .toList()
                .get(0);
    }

    public void displayFlights(){
        flights.forEach(System.out::println);
    }

    public void changeFlightName(int id, String name) {
        findFlightByNumber(id).setName(name);
        System.out.println("Flight name updated");
    }

    public void changeFlightDepartureCity(int id, String s) {
        findFlightByNumber(id).setDepartureCity(s);
        System.out.println("Departure city updated");
    }

    public void changeFlightDestination(int id, String s) {
        findFlightByNumber(id).setDestinationCity(s);
        System.out.println("Destination updated");
    }

    public void changeFlightDate(int id, Calendar date) {
        findFlightByNumber(id).setDate(date);
        System.out.println("Date updated");
    }

    public void changeFlightPrice(int id, int i) {
        findFlightByNumber(id).setPrice(i);
        System.out.println("Price updated");
    }

    public void changeFlightSeates(int id, int i) {
        findFlightByNumber(id).setTotalSeats(i);
        System.out.println("Seates updated");
    }

    public void displayAvailableSeats(int id) {
        findFlightByNumber(id).displayAvailableSeats();
    }
}
