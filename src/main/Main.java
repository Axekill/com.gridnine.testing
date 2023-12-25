package main;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilter();

        System.out.println("All flights:");
        printFlights(flightFilter.allSegments(flights));
        System.out.println("******************************");

        System.out.println("Flights with departure before the current time:");
        printFlights(flightFilter.departureBeforeTheCurrentTime(flights));
        System.out.println("******************************");

        System.out.println("Flights segments, with arrival date earlier than departure date:");
        printFlights(flightFilter.segmentsWithArrivalDateBeforeDepartureDate(flights));
        System.out.println("******************************");

        System.out.println("A flights spent more than two hours on the ground:");
        printFlights(flightFilter.theTotalTimeSpentOnEarthExceedsTwoHours(flights, 120));

    }

    static void printFlights(List<Flight> flights) {
        flights.stream()
                .forEach(System.out::println);
    }

}