import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class PledgesTest {
    private Double expectedAmount;
    private LocalDateTime expectedCreationDate;
    private LocalDateTime expectedDonationDate;
    private Pledges testPledge;


    @BeforeEach
    void setUp() {
        expectedAmount = 200.0;
        expectedCreationDate = LocalDateTime.of(2015, Month.JANUARY,12, 1, 45);
        expectedDonationDate = LocalDateTime.of(2020, Month.APRIL, 12, 1, 45);
        testPledge = new Pledges(expectedAmount, expectedCreationDate, null);
    }

    @Test
    void getAmount() {
        // Test that the correct amount is instantiated in the Pledges
        assertEquals(expectedAmount, testPledge.getAmount());
    }

    @Test
    void setAmount() {
    }

    @Test
    void getCreationDateTime() {
        // Test that the correct dateTime is instantiated in the Pledges
        assertEquals(expectedCreationDate, testPledge.getCreationDateTime());
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
    void getDonationDateTime() {
        // Test that the donation date is null when Monthly donation is initialized
        assertEquals(null, testPledge.getDonationDateTime());
    }

    @Test
    void setDonationDateTime() throws InvalidDonationDateException {
        // Add donation date
        testPledge.setDonationDateTime(expectedDonationDate);
        // See that once cancellation date is added, it is no longer null
        assertEquals(expectedDonationDate, testPledge.getDonationDateTime());
        // Create an invalid cancellation date
        LocalDateTime invalidDonationDate = LocalDateTime.of(2015, Month.JANUARY,12, 1, 45);
        // See that an exception is thrown if this invalid date is tried to be set as the donation date
        Assertions.assertThrows(InvalidDonationDateException.class, () -> {
            testPledge.setDonationDateTime(invalidDonationDate);
        });
        // Test that once set the donation date can be changed
        LocalDateTime newDonationDate = LocalDateTime.of(2021, Month.JANUARY, 1, 1, 1);
        testPledge.setDonationDateTime(newDonationDate);
        assertEquals(newDonationDate, testPledge.getDonationDateTime());
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