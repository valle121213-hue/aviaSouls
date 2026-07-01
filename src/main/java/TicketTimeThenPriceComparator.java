import java.util.Comparator;

public class TicketTimeThenPriceComparator implements Comparator<Ticket> {
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

        // Если время одинаковое — сравниваем цену
        if (t1.getPrice() < t2.getPrice()) {
            return -1;
        }

        if (t1.getPrice() > t2.getPrice()) {
            return 1;
        }

        return 0;
    }
}
