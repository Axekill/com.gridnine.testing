package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.SECONDS;

public class FlightFilter implements Filter {
    private final List<Flight> flightList = new ArrayList<>();

    /**
     * Method of displaying all flights
     *
     * @return
     */
    @Override
    public List<Flight> allSegments(List<Flight> flights) {
        for (Flight flight : flights) {
            for (int i = 0; i < flight.getSegments().size(); i++) {
                System.out.println(flight.getSegments().get(i));
            }
        }
        return flightList;
    }

    @Override
    public List<Flight> departureBeforeTheCurrentTime(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight != null) {
                Optional<Segment> segment = flight.getSegments().stream()
                        .filter(s -> s.getDepartureDate().isBefore(LocalDateTime.now()))
                        .findFirst();
                if (segment.isEmpty()) {
                    flightList.add(new Flight(flight.getSegments()));
                }
            }
        }
        return flightList;
    }

    @Override
    public List<Flight> segmentsWithArrivalDateBeforeDepartureDate(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight != null) {
                Optional<Segment> segment = flight.getSegments().stream()
                        .filter(s -> s.getArrivalDate().isBefore(s.getDepartureDate()))
                        .findFirst();
                if (segment.isEmpty()) {
                    flightList.add(new Flight(flight.getSegments()));
                }
            }
        }
        return flightList;
    }

    @Override
    public List<Flight> theTotalTimeSpentOnEarthExceedsTwoHours(List<Flight> flights, Integer minutesInterval) {
        LocalDateTime startTime;
        LocalDateTime endTime;
        Boolean isValid = true;

        for (Flight flight : flights) {
            if (flight != null) {
                if (flight.getSegments().size() > 1) {
                    for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                        startTime = flight.getSegments().get(i).getArrivalDate();
                        endTime = flight.getSegments().get(i + 1).getDepartureDate();
                        if (SECONDS.between(startTime, endTime) > minutesInterval * 60) {
                            isValid = false;
                            break;
                        }
                    }
                }
            }
            if (isValid) {
                flightList.add(new Flight(flight.getSegments()));
            }
        }
        return flightList;
    }
}
