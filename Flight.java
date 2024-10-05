import java.util.*;
import java.util.stream.Stream;

public class Flight {
    private static int idTracker;
    private int flightNumber;
    private String name;
    private String departureCity;
    private String destinationCity;
    private Calendar date;
    private int price;
    private int totalSeats;
    private int availableSeats;
    private Map<String, Passenger> seats = new HashMap<>();


    public Flight(String name, String departureCity, String destinationCity, Calendar date, int price, int totalSeats) {
        this.flightNumber = ++idTracker;
        this.name = name;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.date = date;
        this.price = price;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        char[] cls = "PEBF".toCharArray();
        Stream.iterate(1, n -> n<= totalSeats - totalSeats%4, n-> n+1)
                .map(n -> cls[(n%(totalSeats/4)==0?n/(totalSeats/4)-1:n/(totalSeats/4))] + "" + n)
                .forEach(num -> seats.put(num,null));
        while(seats.size()<totalSeats){
            seats.put("F" + seats.size()+1, null);
        }
    }

    public void bookSeat(Passenger passenger, String seatNumber){
        this.availableSeats--;
        seats.put(seatNumber, passenger);
    }

    public void cancelSeat(String seatNum){
        this.availableSeats += 1;
        this.seats.put(seatNum,null);
    }

    public boolean isSeatAvailable(String num){
        return seats.get(num) == null;
    }

    public String getName() {
        return name;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public Calendar getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public Map<String, Passenger> getSeats() {
        return seats;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }



    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", name='" + name + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", date=" + date.getTime() +
                ", price=" + price +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                '}';
    }

    public void displayAvailableSeats() {
        int col = 1;
        Comparator<String> com = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int num1 = Integer.parseInt(o1.substring(1));
                int num2 = Integer.parseInt(o2.substring(1));
                if (num1 > num2) return 1;
                return -1;
            }
        };

        System.out.println("Available seats:");
        System.out.println("----------------");
        for (String s : seats.keySet().stream().sorted(com).toList()){
            if(seats.get(s) == null){
                System.out.printf("%-5s",s);
                col++;
            }
            if (col == 6){
                col = 1;
                System.out.println();
            }
        }
    }
}
