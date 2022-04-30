package teststation;

import java.util.*;

public class Eventlist
{
    PriorityQueue<Event> events;
    ArrayList<Integer> carIDs;

    public Eventlist(PriorityQueue<Event> events, ArrayList<Integer> carIDs)
    {
        this.events =  events;
        this.carIDs =  carIDs;
    }

    public Event processCurrentEvent ()
    {
        return events.poll();
    }
}
