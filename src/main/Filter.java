package main;

import java.util.List;

public interface Filter {
    List<Flight> allSegments(List<Flight> flights);

    List<Flight> departureBeforeTheCurrentTime(List<Flight> flights);

    List<Flight> segmentsWithArrivalDateBeforeDepartureDate(List<Flight> flights);

    List<Flight> theTotalTimeSpentOnEarthExceedsTwoHours(List<Flight> flights, Integer minutesInterval);
}
