package teststation;

public abstract class Event {
    private int entryTimeStamp;
    private static int initCarID = 0;
    public int carID;
    private int peopleInCar;

    public Event(int entryTimeStamp, int carID, int peopleInCar) {
        this.entryTimeStamp = entryTimeStamp;
        this.carID = carID;
        this.peopleInCar = peopleInCar;
    }

    public Event(int entryTimeStamp, int peopleInCar) {
        this.entryTimeStamp = entryTimeStamp;
        this.carID = setNewCarID();
        this.peopleInCar = peopleInCar;
    }

    public int setNewCarID() {
        return initCarID++;
    }

    public Event processEvent(Eventlist eventList) {
        Event currentEvent = eventList.events.peek();

        eventList.processCurrentEvent();
        Event newEvent = currentEvent.generateFutureEvent(currentEvent.getEntryTimeStamp(), currentEvent.getCarID(),
                currentEvent.getPeopleInCar());

        if (newEvent == null) {
            int indexCarId = eventList.carIDs.indexOf(currentEvent.getCarID());
            eventList.carIDs.remove(indexCarId);
        }
        return newEvent;
    }

    ;

    public int getCarID() {
        return carID;
    }

    public int getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public int getPeopleInCar() {
        return peopleInCar;
    }

    public abstract Event generateFutureEvent(int entryTimeStamp, int carID, int peopleInCar);
    public abstract void printLn(Eventlist eventList);
    public abstract void printLnNoSpace(Eventlist eventList);
}


