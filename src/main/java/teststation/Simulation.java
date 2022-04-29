package teststation;

import java.util.Queue;
import java.util.Random;

public class Simulation {
    Eventlist eventList;
    int startTime;
    int peopleInCar;

    Simulation(int entryTimeStamp, int peopleInCar, Eventlist eventList) {
        startTime = entryTimeStamp;
        this.peopleInCar = peopleInCar;
        this.eventList = eventList;
        Arriving newEvent = new Arriving(this.startTime, this.peopleInCar);
        eventList.events.add(newEvent);
        eventList.carIDs.add(newEvent.getCarID());
    }

    ;

    public void run() {
        Random rand = new Random();
        int newArrival = 0;
        int entryTimeStamp = this.startTime;
        Event result = getThisEvent();

        while (eventList.events.size() > 0) {

            result.printLn(eventList);
            if (eventList.carIDs.size() < 10) {
                int intervalNewArrival = (int) (Math.random()*90)+30;
                int newPeopleInCar = (int) (Math.random()*4)+1;
                newArrival = entryTimeStamp + intervalNewArrival;
                if (newArrival < 7200) {

                    Arriving newEvent = new Arriving(newArrival, newPeopleInCar);
                    eventList.events.add(newEvent);
                    eventList.carIDs.add(newEvent.getCarID());
                    entryTimeStamp = newArrival;

                }
            } else {
                System.out.println("The queue is full");
            }

            addNewEvent(result);
            result = getThisEvent();

        }
    }

    public Event getThisEvent() {
        return eventList.events.peek();
    }

    public void addNewEvent(Event event) {
        Event newEvent = event.processEvent(eventList);
        if (newEvent != null) {
            eventList.events.add(newEvent);
        }
    }

}
