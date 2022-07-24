import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class NonProfitTest {
    private String expectedOrganizationName;
    private AbstractDonations[] expectedAllNonProfitDonations;
    private OneTimeDonations oneTimeDonation1;
    private OneTimeDonations oneTimeDonation2;
    private MonthlyDonations monthlyDonation1;
    private MonthlyDonations monthlyDonation2;
    private Pledges pledge1;
    private Pledges pledge2;
    private NonProfit testNonProfit;


    @BeforeEach
    void setUp() {
        LocalDateTime creationDate = LocalDateTime.of(2016, Month.JANUARY, 12, 0, 0);
        LocalDateTime creationDate1 = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59);
        expectedOrganizationName = "Amazon";
        oneTimeDonation1 = new OneTimeDonations(100.0, creationDate);
        oneTimeDonation2 = new OneTimeDonations(200.0, creationDate1);
        monthlyDonation1 = new MonthlyDonations(20.0, creationDate, null);
        monthlyDonation2 = new MonthlyDonations(30.0, creationDate1, null);
        pledge1 = new Pledges(2000.0, creationDate, null);
        pledge2 = new Pledges(200.0, creationDate1, null);

    }

    @Test
    void getTotalDonationsForYear() throws InvalidDonationDateException, InvalidCancellationDateException {
        //Add monthlyDonation1 in the Donations array
        expectedAllNonProfitDonations = new AbstractDonations[]{monthlyDonation1};
        testNonProfit = new NonProfit(expectedOrganizationName, expectedAllNonProfitDonations);
        // Test to see the total for 2017 includes 12 * monthlyDonation1 amount
        // 12*20 = 240
        assertEquals(240, testNonProfit.getTotalDonationsForYear(2017));
        // Add oneTimeDonation1 and oneTimeDonation2 in the Donation array
        expectedAllNonProfitDonations = new AbstractDonations[]{oneTimeDonation1, oneTimeDonation2};
        testNonProfit = new NonProfit(expectedOrganizationName, expectedAllNonProfitDonations);
        // Test to see the total for 2016 includes only oneTimeDonation1
        assertEquals(100, testNonProfit.getTotalDonationsForYear(2016));
        // Set donationDate for pledge and pledge1
        LocalDateTime donationDate = LocalDateTime.of(2017, Month.FEBRUARY, 2, 2, 2);
        LocalDateTime donationDate1 = LocalDateTime.of(2021, Month.OCTOBER, 2, 2, 2);
        pledge1.setDonationDateTime(donationDate);
        pledge2.setDonationDateTime(donationDate1);
        // Add pledge1 and pledge2 in the Donation array
        expectedAllNonProfitDonations = new AbstractDonations[]{pledge1, pledge2};
        testNonProfit = new NonProfit(expectedOrganizationName, expectedAllNonProfitDonations);
        // Test to see the total for 2016 includes only pledge1
        assertEquals(2000, testNonProfit.getTotalDonationsForYear(2017));
        // Add all to Donations array
        expectedAllNonProfitDonations = new AbstractDonations[]{oneTimeDonation1, oneTimeDonation2, monthlyDonation1, monthlyDonation2, pledge1, pledge2};
        testNonProfit = new NonProfit(expectedOrganizationName, expectedAllNonProfitDonations);
        // Set cancellation date for monthlyDonations1
        LocalDateTime cancellationDate = LocalDateTime.of(2021, Month.AUGUST, 1, 1, 1);
        monthlyDonation1.setCancellationDateTime(cancellationDate);
        // Test to see the total for 2021 includes monthlyDonation1 amount for 8 months, monthlyDonation2 amount for 12 months, and pledge2 amount
        // 20*8 + 30*12 + 200
        assertEquals(720, testNonProfit.getTotalDonationsForYear(2021));

    }

    @Test
    void getOrganizationName() {
        // Test that the Organization is correct at initialization
        assertEquals(expectedOrganizationName, testNonProfit.getOrganizationName());
    }

    @Test
    void setOrganizationName() {
    }

    @Test
    void getAllNonProfitDonations() {
        // Test that the Organization is correct at initialization
        assertEquals(expectedAllNonProfitDonations, testNonProfit.getAllNonProfitDonations());
    }

    @Test
    void setAllNonProfitDonations() {
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