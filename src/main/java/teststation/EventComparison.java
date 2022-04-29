package teststation;

import java.sql.Timestamp;
import java.util.Comparator;

public class EventComparison implements Comparator<Event>
{
    @Override
    public int compare(Event e1, Event e2)
    {
        int  timeStampE1 = e1.getEntryTimeStamp();
        int  timeStampE2 = e2.getEntryTimeStamp();

        int comparisonResult = timeStampE1 - timeStampE2;

        if(comparisonResult > 0)
        {
            return 1;
        }
        else
        {
            return -1;
        }

    }
}
