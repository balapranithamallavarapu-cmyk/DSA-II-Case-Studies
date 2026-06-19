import java.util.*;

class Event {
    int start, end;

    Event(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class EventSphere {

    public static void main(String[] args) {

        Event[] events = {
            new Event(1, 2),
            new Event(3, 4),
            new Event(0, 6),
            new Event(5, 7),
            new Event(8, 9),
            new Event(5, 9)
        };

        Arrays.sort(events, (a, b) -> a.end - b.end);

        System.out.println("Selected Events:");

        int count = 1;
        System.out.println("(" + events[0].start + ", " + events[0].end + ")");

        int lastEnd = events[0].end;

        for (int i = 1; i < events.length; i++) {

            if (events[i].start >= lastEnd) {
                System.out.println("(" + events[i].start + ", " + events[i].end + ")");
                count++;
                lastEnd = events[i].end;
            }
        }

        System.out.println("\nMaximum Events Scheduled = " + count);
    }
}
