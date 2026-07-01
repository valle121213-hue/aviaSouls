import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


public class AviaSoulsTest {
    //Проверка класса Ticket
    //Тесты для get
    @Test
    public void shouldReturnFrom() {
        Ticket ticket = new Ticket("EVN", "DME", 5000, 12, 15);

        assertEquals("EVN", ticket.getFrom());
    }


    @Test
    public void shouldReturnTo() {
        Ticket ticket = new Ticket("EVN", "DME", 5000, 12, 15);

        assertEquals("DME", ticket.getTo());
    }

    @Test
    public void shouldReturnPrice() {
        Ticket ticket = new Ticket("EVN", "DME", 5000, 12, 15);

        assertEquals(5000, ticket.getPrice());

    }

    @Test
    public void shouldReturnTimeFrom() {
        Ticket ticket = new Ticket("EVN", "DME", 5000, 12, 15);

        assertEquals(12, ticket.getTimeFrom());

    }

    @Test
    public void shouldReturnTimeTo() {
        Ticket ticket = new Ticket("EVN", "DME", 5000, 12, 15);

        assertEquals(15, ticket.getTimeTo());

    }

    // Проверка equals
// сравнить объект с самим собой
    @Test
    void equals_sameObject() {
        Ticket t = new Ticket("AND", "BER", 1000, 12, 15);
        assertTrue(t.equals(t));
    }

    // сравнить с null
    @Test
    void equals_null() {
        Ticket t = new Ticket("AND", "BER", 1000, 12, 15);
        assertFalse(t.equals(null));
    }

    // другой тип
    @Test
    void equals_otherType() {
        Ticket t = new Ticket("AND", "BER", 1000, 12, 15);
        assertFalse(t.equals("text"));
    }

    // одинаковые объекты данные билетов
    @Test
    void equals_equalObjects() {
        Ticket t1 = new Ticket("AND", "BER", 1000, 12, 15);
        Ticket t2 = new Ticket("AND", "BER", 1000, 12, 15);

        assertTrue(t1.equals(t2));
    }

    //пример: разные from
    @Test
    void equals_diffFrom() {
        Ticket t1 = new Ticket("AND", "BER", 1000, 12, 15);
        Ticket t2 = new Ticket("ERV", "BER", 1000, 12, 15);

        assertFalse(t1.equals(t2));
    }

    //Разные to
    @Test
    void equals_diffTo() {
        Ticket t1 = new Ticket("AND", "ERV", 1000, 12, 15);
        Ticket t2 = new Ticket("AND", "BER", 1000, 12, 15);

        assertFalse(t1.equals(t2));
    }

    //Разные price
    @Test
    void equals_diffPrice() {
        Ticket t1 = new Ticket("AND", "ERV", 1000, 12, 15);
        Ticket t2 = new Ticket("AND", "ERV", 12000, 12, 15);

        assertFalse(t1.equals(t2));
    }

    //Разные timeFrom
    @Test
    void equals_diffTimeFrom() {
        Ticket t1 = new Ticket("AND", "ERV", 1000, 12, 15);
        Ticket t2 = new Ticket("AND", "ERV", 1000, 13, 15);

        assertFalse(t1.equals(t2));
    }

    //Разные timeTo
    @Test
    void equals_diffTimeTo() {
        Ticket t1 = new Ticket("AND", "ERV", 1000, 12, 15);
        Ticket t2 = new Ticket("AND", "ERV", 1000, 12, 16);

        assertFalse(t1.equals(t2));
    }

    // проверка hashCode
    @Test
    void hashCode_equalObjects() {
        Ticket t1 = new Ticket("AND", "BER", 10, 20, 100);
        Ticket t2 = new Ticket("AND", "BER", 10, 20, 100);

        assertEquals(t1.hashCode(), t2.hashCode());
    }

    //!!!!!!Добавила
    @Test
    void hashCode_notEqualObjects() {
        Ticket t1 = new Ticket("A", "B", 100, 1, 2);
        Ticket t2 = new Ticket("A", "C", 100, 1, 2);

        assertNotEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void search_fromDoesNotMatch() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("AAA", "LED", 1000, 10, 12));
        manager.add(new Ticket("MOW", "PAR", 2000, 10, 12));

        Ticket[] result = manager.search("MOW", "LED");

        assertEquals(0, result.length);
    }

    @Test
    void searchAndSortBy_emptyManager() {
        AviaSouls manager = new AviaSouls();

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchAndSortBy_fromMatchesToDoesNotMatch() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("MOW", "PAR", 1000, 10, 12));

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", comparator);

        assertEquals(0, actual.length);
    }
//Тесты для проверки compareTo 3 ветки

    // Цена дешевле
    @Test
    public void shouldReturnMinusOneIfFirstTicketIsCheaper() {
        Ticket first = new Ticket("EVN", "DME", 5000, 10, 13);
        Ticket second = new Ticket("EVN", "DME", 8000, 17, 18);

        assertEquals(-1, first.compareTo(second));
    }

    // Цена дороже
    @Test
    public void shouldReturnOneIfFirstTicketIsExpensive() {
        Ticket first = new Ticket("EVN", "DME", 15000, 10, 13);
        Ticket second = new Ticket("EVN", "DME", 8000, 17, 18);

        assertEquals(1, first.compareTo(second));
    }

    // Цена одинакова
    @Test
    public void shouldReturnZeroIfPricesEqual() {
        Ticket first = new Ticket("EVN", "DME", 8000, 10, 13);
        Ticket second = new Ticket("EVN", "DME", 8000, 17, 18);

        assertEquals(0, first.compareTo(second));
    }

// Проверка класса AviaSouls


    // Проверка метода add
    @Test
    public void shouldAddOneTicket() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket = new Ticket(
                "ERV",
                "LED",
                5000,
                10,
                12
        );

        manager.add(ticket);

        Ticket[] expected = {ticket};
        Ticket[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // несколько билетов и за одно findAll()
    @Test
    public void shouldAddSomeTickets() {
        AviaSouls manager = new AviaSouls();

        Ticket first = new Ticket("MOW", "LED", 15000, 10, 12);
        Ticket second = new Ticket("ERV", "KZN", 60000, 11, 13);
        Ticket third = new Ticket("LED", "MOW", 17000, 14, 16);

        manager.add(first);
        manager.add(second);
        manager.add(third);

        Ticket[] expected = {first, second, third};
        Ticket[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


//Проверка дополненного метода search по возрастанию


//searchAndSortBy с comparator

    @Test
    void searchAndSortBy_withComparator() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("MOW", "LED", 1000, 10, 12));
        manager.add(new Ticket("MOW", "LED", 500, 10, 15));

        Comparator<Ticket> cmp = new TicketTimeComparator();

        Ticket[] result = manager.searchAndSortBy("MOW", "LED", cmp);

        assertEquals(2, result.length);
    }

    //from совпадает, to НЕ совпадает
    @Test
    void search_coverToFalseBranch() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("MOW", "LED", 1000, 10, 12)); // совпадает
        manager.add(new Ticket("MOW", "PAR", 2000, 10, 12)); // from ok, to NO

        Ticket[] result = manager.search("MOW", "LED");

        assertEquals(1, result.length);
    }

    //from НЕ совпадает, to совпадает
    @Test
    void search_onlyToMatches() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("MOW", "LED", 1000, 10, 12));
        manager.add(new Ticket("PAR", "LED", 2000, 10, 12));

        Ticket[] result = manager.search("MOW", "LED");

        assertEquals(1, result.length);
    }


    //Расстановка по увеличению цены
    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSouls manager = new AviaSouls();

        Ticket first = new Ticket("EVN", "LED", 12000, 10, 12);
        Ticket second = new Ticket("EVN", "LED", 8000, 9, 11);
        Ticket third = new Ticket("EVN", "LED", 9000, 8, 10);

        manager.add(first);
        manager.add(second);
        manager.add(third);

        Ticket[] expected = {second, third, first};
        Ticket[] actual = manager.search("EVN", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    // Совпадений нет
    @Test
    public void shouldReturnIfNoTicketsFound() {
        AviaSouls manager = new AviaSouls();

        Ticket first = new Ticket("EVN", "LED", 12000, 10, 12);
        Ticket second = new Ticket("EVN", "LED", 8000, 9, 11);
        Ticket third = new Ticket("EVN", "LED", 9000, 8, 10);

        manager.add(first);
        manager.add(second);
        manager.add(third);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("MOW", "KZN");

        Assertions.assertArrayEquals(expected, actual);
    }

    //Найден только один билет
    @Test
    public void shouldReturnIfFoundOneTicket() {
        AviaSouls manager = new AviaSouls();

        Ticket first = new Ticket("EVN", "SPB", 12000, 10, 12);
        Ticket second = new Ticket("ERV", "EKZ", 8000, 9, 11);
        Ticket third = new Ticket("EVN", "LED", 9000, 8, 10);

        manager.add(first);
        manager.add(second);
        manager.add(third);

        Ticket[] expected = {second};
        Ticket[] actual = manager.search("ERV", "EKZ");

        Assertions.assertArrayEquals(expected, actual);
    }

    // Проверка класса TicketTimeComparator
//]Проверка 3 веток метода compare - первый рейс летит быстрее
    @Test
    public void shouldCompareByFlightTimeLess() {
        Ticket first = new Ticket("ERV", "LED", 15000, 10, 13);   // 3 часа
        Ticket second = new Ticket("ERV", "LED", 15000, 10, 15);  // 5 часов

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertEquals(-1, comparator.compare(first, second));
    }

    //Второй рейс быстрее
    @Test
    public void shouldCompareByFlightTimeMore() {
        Ticket first = new Ticket("ERV", "LED", 15000, 10, 16);   // 6 часов
        Ticket second = new Ticket("ERV", "LED", 15000, 9, 11);  // 3 часа

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertEquals(1, comparator.compare(first, second));
    }

    //Оба летят одинаково
    @Test
    public void shouldCompareByFlightTimeEqual() {
        Ticket first = new Ticket("ERV", "LED", 5000, 10, 14);   // 4 часа
        Ticket second = new Ticket("ERV", "LED", 7000, 9, 13);  // 4 часа

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertEquals(0, comparator.compare(first, second));
    }

    // Сортировка билетов по времени полета
    @Test
    public void shouldSearchAndSortByFlightTime() {
        AviaSouls manager = new AviaSouls();

        Ticket first = new Ticket("MOW", "LED", 21000, 10, 16);   // 6 часов
        Ticket second = new Ticket("MOW", "LED", 17000, 10, 12);  // 2 часа
        Ticket third = new Ticket("MOW", "LED", 16000, 10, 14);   // 4 часа

        manager.add(first);
        manager.add(second);
        manager.add(third);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {second, third, first};
        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    // Результат не найден
    @Test
    public void shouldReturnSearchAndSortByNothingFound() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("ERV", "LED", 15000, 10, 12));

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("MOW", "KZN", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    // для TicketTimeThenPriceComparator (понравилось задание - дополнительный метод)
    @Test
    public void shouldCompareByFlightTimeMoreComparatorTP() {
        Ticket first = new Ticket("MOW", "LED", 5000, 10, 16);   // 6 часов
        Ticket second = new Ticket("MOW", "LED", 7000, 10, 12);  // 2 часа

        TicketTimeThenPriceComparator comparator = new TicketTimeThenPriceComparator();

        int actual = comparator.compare(first, second);

        assertEquals(1, actual);
    }

    @Test
    public void shouldCompareByFlightTimeLessComparatorTP() {
        Ticket first = new Ticket("MOW", "LED", 5000, 10, 12);   // 2 часа
        Ticket second = new Ticket("MOW", "LED", 7000, 10, 15);  // 5 часов

        TicketTimeThenPriceComparator comparator = new TicketTimeThenPriceComparator();

        int actual = comparator.compare(first, second);

        assertEquals(-1, actual);
    }

    @Test
    public void shouldCompareByPriceWhenTimeEqualLess() {
        Ticket first = new Ticket("MOW", "LED", 5000, 10, 12);   // 2 часа
        Ticket second = new Ticket("MOW", "LED", 7000, 14, 16);  // 2 часа

        TicketTimeThenPriceComparator comparator = new TicketTimeThenPriceComparator();

        int actual = comparator.compare(first, second);

        assertEquals(-1, actual);
    }

    @Test
    public void shouldCompareByPriceWhenTimeEqualMore() {
        Ticket first = new Ticket("MOW", "LED", 9000, 10, 12);   // 2 часа
        Ticket second = new Ticket("MOW", "LED", 7000, 14, 16);  // 2 часа

        TicketTimeThenPriceComparator comparator = new TicketTimeThenPriceComparator();

        int actual = comparator.compare(first, second);

        assertEquals(1, actual);
    }

    @Test
    public void shouldCompareEqualTimeAndEqualPrice() {
        Ticket first = new Ticket("MOW", "LED", 7000, 10, 12);   // 2 часа
        Ticket second = new Ticket("MOW", "LED", 7000, 14, 16);  // 2 часа

        TicketTimeThenPriceComparator comparator = new TicketTimeThenPriceComparator();

        int actual = comparator.compare(first, second);

        assertEquals(0, actual);
    }

}
