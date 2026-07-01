import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    //Сравнение по времени полета
    @Override
    public int compare(Ticket t1, Ticket t2) {

        int firstTime = t1.getTimeTo() - t1.getTimeFrom();
        int secondTime = t2.getTimeTo() - t2.getTimeFrom();

        if (firstTime < secondTime) {
            return -1;
        }

        if (firstTime > secondTime) {
            return 1;
        }

        return 0;
    }
}


