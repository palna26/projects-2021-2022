import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyAbstractDonationsTest {
    private Double expectedAmount;
    private LocalDateTime expectedCreationDate;
    private LocalDateTime expectedCancellationDate;
    private MonthlyDonations testMonthlyTimeDonation;

    @BeforeEach
    void setUp() {
        expectedAmount = 10.00;
        expectedCreationDate = LocalDateTime.of(2015, Month.JANUARY,12, 1, 45);
        expectedCancellationDate = LocalDateTime.of(2020, Month.APRIL, 12, 1, 45);
        testMonthlyTimeDonation = new MonthlyDonations(expectedAmount, expectedCreationDate, null);

    }

    @Test
    void getAmount() {
        // Test that the correct amount is instantiated in the MonthlyDonations
        assertEquals(expectedAmount, testMonthlyTimeDonation.getAmount());
    }

    @Test
    void setAmount() {
    }

    @Test
    void getCreationDateTime() {
        // Test that the correct dateTime is instantiated in the MonthlyDonations
        assertEquals(expectedCreationDate, testMonthlyTimeDonation.getCreationDateTime());
    }

    @Test
    void setCreationDateTime() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getCancellationDateTime() {
        // Test that the cancellation date is null when Monthly donation is initialized
        assertEquals(null, testMonthlyTimeDonation.getCancellationDateTime());
    }

    @Test
    void setCancellationDateTime() throws InvalidCancellationDateException {
        // Add cancellation date
        testMonthlyTimeDonation.setCancellationDateTime(expectedCancellationDate);
        // See that once cancellation date is added, it is no longer null
        assertEquals(expectedCancellationDate, testMonthlyTimeDonation.getCancellationDateTime());
        // Create an invalid cancellation date
        LocalDateTime invalidCancellationDate = LocalDateTime.of(2015, Month.JANUARY,12, 1, 45);
        // See that an exception is thrown if this invalid date is tried to be set as the cancellation date
        Assertions.assertThrows(InvalidCancellationDateException.class, () -> {
            testMonthlyTimeDonation.setCancellationDateTime(invalidCancellationDate);
        });
    }

    @Test
    void calculateYearAmountUntil() throws InvalidCancellationDateException {
        // Create local start date and end date variables
        LocalDateTime startDate = LocalDateTime.of(2016, Month.JANUARY, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 59);
        // Calculate the amount of donations in 2016, which should be 11*10 = 240
        Double monthlyDonationAmount = testMonthlyTimeDonation.calculateYearAmountUntil(startDate, endDate);
        assertEquals(120, monthlyDonationAmount);
        // Create cancellation date before end of the year
        LocalDateTime cancellationDate = LocalDateTime.of(2016, Month.AUGUST, 23, 1, 1);
        // Set the cancellation date
        testMonthlyTimeDonation.setCancellationDateTime(cancellationDate);
        // Calculate the amount of donations in 2016, which should be 7*10 = 70
        // Keep the endDate december and see that the function recognizes to stop adding amount after it has been cancelled
        Double monthlyDonationAmount1 = testMonthlyTimeDonation.calculateYearAmountUntil(startDate, endDate);
        assertEquals(80, monthlyDonationAmount1);

    }

    @Test
    void testEquals1() {
    }

    @Test
    void testHashCode1() {
    }

    @Test
    void testToString1() {
    }
}