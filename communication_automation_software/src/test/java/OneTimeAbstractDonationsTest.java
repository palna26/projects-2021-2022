import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeAbstractDonationsTest {
    private Double expectedAmount;
    private LocalDateTime expectedCreationDate;
    private OneTimeDonations testOneTimeDonation;

    @BeforeEach
    void setUp() {
        expectedAmount = 100.00;
        expectedCreationDate = LocalDateTime.of(2020, Month.JANUARY,12, 1, 45);
        testOneTimeDonation = new OneTimeDonations(expectedAmount,expectedCreationDate);

    }

    @Test
    void getAmount() {
        // Test that the correct amount is instantiated in the OneTimeDonation
        assertEquals(expectedAmount, testOneTimeDonation.getAmount());
    }

    @Test
    void setAmount() {
    }

    @Test
    void getCreationDateTime() {
        // Test that the correct dateTime is instantiated in the OneTimeDonation
        assertEquals(expectedCreationDate, testOneTimeDonation.getCreationDateTime());
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
}