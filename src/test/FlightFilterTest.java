package test;

import main.Filter;
import main.Flight;
import main.FlightFilter;
import main.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterTest {

    private final List<Flight> flights = new ArrayList<>();
    private final Filter filter = new FlightFilter();
    private final List<Segment> segments = new ArrayList<>();
    LocalDateTime time = LocalDateTime.now();

    @Test
    void departureBeforeTheCurrentTime() {
        Segment segment = new Segment(time.minusHours(5), time);
        segments.add(segment);
        List<Flight> flightListFilter = filter.departureBeforeTheCurrentTime(flights);
        assertTrue(flightListFilter.isEmpty());

    }

    @Test
    void segmentsWithArrivalDateBeforeDepartureDate() {
        Segment segment = new Segment(time, time.minusHours(1));
        segments.add(segment);
        List<Flight> flightListFilter = filter.segmentsWithArrivalDateBeforeDepartureDate(flights);
        assertTrue(flightListFilter.isEmpty());
    }

    @Test
    void theTotalTimeSpentOnEarthExceedsTwoHours() {
        Integer interval = 120; // интервал ожидания в минутах
        Segment segment1 = new Segment(time.minusHours(1), time);
        Segment segment2 = new Segment(time.plusHours(2).plusSeconds(1), time.plusHours(5));
        segments.add(segment1);
        segments.add(segment2);
        List<Flight> flightListFilter = filter.theTotalTimeSpentOnEarthExceedsTwoHours(flights, interval);
        assertTrue(flightListFilter.isEmpty(),"Список перелетов должен быть пустым");

    }
}