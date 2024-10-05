import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static FlightManager flightManager = new FlightManager();
    private static Admin admin = new Admin();
    static BookingManager bookingManager = new BookingManager();

    public static void main(String[] args) {

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 15);
        date.set(Calendar.MONTH, 4);
        date.set(Calendar.YEAR, 2024);
        flightManager.addFlight(new Flight("Chennai Express", "Coimbatore", "Chennai",date,1500,100));
        Calendar dates = Calendar.getInstance();
        dates.set(Calendar.DAY_OF_MONTH, 15);
        dates.set(Calendar.MONTH, 4);
        dates.set(Calendar.YEAR, 2024);
        flightManager.addFlight(new Flight("Coimbatore Express", "Chennai", "Coimbatore",date,1400,200));

        System.out.println(date.compareTo(dates) == 0);

        boolean run = true;
        while (run) {
            displayMainMenu();
            switch (sc.nextInt()) {
                case 1:
                    if (executeAdminLogin())
                        adminOperations();
                    break;
                case 2:
                    passengerOptions();
                    break;
                case 3:
                    run = false;
            }
        }


    }

    private static void adminOperations() {
        boolean run = true;
        while (run) {
            displayAdminOptions();
            switch (sc.nextInt()) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    removeFlight();
                    break;
                case 3:
                    changeFlightInfo();
                    break;
                case 4:
                    flightManager.displayFlights();
                    break;
                case 5:
                    run = false;
            }
        }
    }

    private static void changeFlightInfo() {
        System.out.println("Enter flight number:");
        int id = sc.nextInt();
        System.out.println("""
                What do you want to change:
                    1> Name
                    2=> Departure city
                    3=> Destination
                    4=> date
                    5=> Price
                    6=> Total Seats
                """);
        switch (sc.nextInt()){
            case 1:
                System.out.println("Enter the name");
                sc.nextLine();
                flightManager.changeFlightName(id, sc.nextLine());
                break;
            case 2:
                System.out.println("Enter Departure city:");
                sc.nextLine();
                flightManager.changeFlightDepartureCity(id, sc.nextLine());
                break;
            case 3:
                System.out.println("Enter Destination:");
                sc.nextLine();
                flightManager.changeFlightDestination(id, sc.nextLine());
                break;
            case 4:
                System.out.println("Enter the date(dd-mm-yyyy):");
                String[] d = sc.next().split("-");
                Calendar date = Calendar.getInstance();
                date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d[0]));
                date.set(Calendar.MONTH, Integer.parseInt(d[1]));
                date.set(Calendar.YEAR, Integer.parseInt(d[2]));
                flightManager.changeFlightDate(id, date);
                break;
            case 5:
                System.out.println("Enter the price:");
                flightManager.changeFlightPrice(id, sc.nextInt());
                break;
            case 6:
                System.out.println("Enter total seats");
                flightManager.changeFlightSeates(id, sc.nextInt());
        }
    }

    private static void removeFlight() {
        System.out.println("Enter Flight Number:");
        flightManager.removeFlight(sc.nextInt());
    }

    private static void addFlight() {

        System.out.println("Enter flight name:");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Enter Departure city");
        String departureCity = sc.nextLine();
        System.out.println("Enter Destination:");
        String destination = sc.nextLine();
        System.out.println("Enter date(dd-mm-yyyy):");
        String[] d = sc.next().split("-");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d[0]));
        date.set(Calendar.MONTH, Integer.parseInt(d[1]) - 1);
        date.set(Calendar.YEAR, Integer.parseInt(d[2]));
        System.out.println("Enter price:");
        int price = sc.nextInt();
        System.out.println("Enter total seats:");
        int totalSeats = sc.nextInt();
        System.out.println(date.get(Calendar.DAY_OF_MONTH));
        System.out.println(date.get(Calendar.MONTH));
        System.out.println(date.get(Calendar.YEAR));
        flightManager.addFlight(new Flight(name, departureCity, destination, date, price, totalSeats));

    }

    private static boolean executeAdminLogin() {
        System.out.println("Admin login:");
        System.out.println("------------");
        System.out.println("Enter the username:");
        String user = sc.next();
        System.out.println("Enter the password");
        String password = sc.next();
        return admin.login(user, password);
    }

    private static void cancelBooking() {
        System.out.println("Enter the booking reference number:");
        bookingManager.cancelBooking(sc.nextInt());
    }

    private static void viewBooking() {
        System.out.println("Enter Booking Reference Id:");
        bookingManager.viewBooking(sc.nextInt());
    }

    private static void bookFlight() {
        System.out.println("Enter the flight number:");
        Flight flight = flightManager.findFlightByNumber(sc.nextInt());
        flight.displayAvailableSeats();
        System.out.println("How many passengers:");
        Map<Passenger, String> passengers = new HashMap<>();
        int count = sc.nextInt();
        for (int i = 0; i<count; i++){
            System.out.println();
            System.out.println("Enter passenger " + (i+1) + " name:");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Enter age:");
            int age = sc.nextInt();
            System.out.print("Enter phone:");
            String phone = sc.next();
            System.out.print("Enter idProof:");
            String id = sc.next();
            System.out.print("Enter seat number:");
            String seat = sc.next();
            passengers.put(new Passenger(name, age, phone, id), seat);
        }
        AtomicBoolean seatClear = new AtomicBoolean(true);
        passengers.values().forEach(v -> {
            if (!flight.isSeatAvailable(v)){
                System.out.println("Seat number " + v + " is not available");
                seatClear.set(false);
            }
        });
        if (seatClear.get()) {
            System.out.println("Total amount: " + flight.getPrice() * passengers.size());
            System.out.println("Do you want to proceed with the payment (y/n)");
            if (sc.next().equalsIgnoreCase("y")) {
                bookingManager.createBooking(flight, passengers, new Payment(flight.getPrice() * passengers.size(), true));
            }
        }

    }

    private static void searchFlight() {
        System.out.println("Enter departure city:");
        sc.nextLine();
        String dcity = sc.nextLine();
        System.out.println("Enter destination:");
        String destination = sc.nextLine();
        System.out.println("Enter the date (dd-mm-yyyy):");
        String[] s = sc.next().split("-");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s[0]));
        date.set(Calendar.MONTH, Integer.parseInt(s[1]) - 1);
        date.set(Calendar.YEAR, Integer.parseInt(s[2]));
        flightManager.searchFlights(dcity, destination, date);
    }


    public static void passengerOptions() {
        boolean run = true;
        while (run) {
            displayPassengerOptions();
            switch (sc.nextInt()) {
                case 1:
                    searchFlight();
                    break;
                case 2:
                    System.out.println("Enter flight number");
                    flightManager.displayAvailableSeats(sc.nextInt());
                    break;
                case 3:
                    bookFlight();
                    break;
                case 4:
                    viewBooking();
                    break;
                case 5:
                    cancelBooking();
                    break;
                case 6:
                    run = false;
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println();
        System.out.println("""
                Choose one from below:
                    1=> Admin Operations
                    2=> Passenger options
                    3=> Exit
                """);
    }

    private static void displayPassengerOptions() {
        System.out.println();
        System.out.println("""
                Choose one from below:
                    1=> Search Flight
                    2=> Display available seats
                    3=> Book Flight
                    4=> View Booking
                    5=> Cancel Booking
                    6=> Exit
                """);
    }

    private static void displayAdminOptions() {
        System.out.println();
        System.out.println("""
                Choose one from below:
                    1=> Add Flight
                    2=> Remove Flight
                    3=> Change Flight Information
                    4=> View Flights
                    5=> Exit
                """);
    }
}