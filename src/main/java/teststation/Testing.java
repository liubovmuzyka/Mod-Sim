package teststation;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Testing extends Event
{

    static Logger logger = Logger.getLogger(Arriving.class);

    public Testing(int timeStamp,int carID, int peopleInCar) {super(timeStamp, carID, peopleInCar);}

    @Override
    public Leaving generateFutureEvent(int entryTimeStamp, int carID, int peopleInCar)
    {
        int newEventTimeStamp = entryTimeStamp + 4*60*peopleInCar;
        Leaving event = new Leaving(newEventTimeStamp, carID, peopleInCar);
        return event;
    }

    @Override
    public void printLn(Eventlist eventList) {
        PropertyConfigurator.configure("log4j.properties");

        logger.info("The car "+ this.getCarID() + " came to test at " + this.getEntryTimeStamp() + " sek" + " with " + this.getPeopleInCar() + " people " + "["+this.getClass().getName() + "]");

        //System.out.println("Testing car "+ this.getCarID());

    }

    @Override
    public void printLnNoSpace(Eventlist eventList) {

    }


}
