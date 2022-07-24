import java.time.Month;
import java.util.Arrays;
import java.util.Objects;
import java.time.LocalDateTime;

public class NonProfit {
    //private static int NUM_SLOTS = 0;
    private String organizationName;
    private AbstractDonations[] allNonProfitDonations;

    public NonProfit(String organizationName, AbstractDonations[] allNonProfitDonations) {
        this.organizationName = organizationName;
        this.allNonProfitDonations = allNonProfitDonations;
    }

    /*
    Method takes an input integer as a year and returns the total donations summed together as a double value
    @param - given integer year
    @return - double value of all donations in the given year
     */
    public Double getTotalDonationsForYear(Integer year) {
        Double sum = 0.0;
        // For all donations in the donation list
        for (AbstractDonations d : this.allNonProfitDonations) {
            // If it is a OneTime donation, made in the given year, simply add its amount to the total sum
            if(d instanceof OneTimeDonations && d.creationDateTime.getYear() == year) {
                Double amountToAdd = d.amount;
                sum = sum + amountToAdd;
            }
            // Create a startDate and endDate that is the first of the year and end of the year
            LocalDateTime startDate = LocalDateTime.of(year, Month.JANUARY, 1, 0,0);
            LocalDateTime endDate = LocalDateTime.of(year, Month.DECEMBER, 31, 23, 59);
            // If it is a monthly donation
            if(d instanceof MonthlyDonations){
                // Use calculateAmountUntil method to calculate the amount to add to the total sum
                Double amountToAdd = ((MonthlyDonations) d).calculateYearAmountUntil(startDate, endDate);
                sum = sum + amountToAdd;
            }
            // If it is a pledge
            if(d instanceof Pledges) {
                LocalDateTime donationDate = ((Pledges) d).getDonationDateTime();
                // The donation date is not null, and it is in the input year, add the amount to the total sum
                if(donationDate != null && donationDate.getYear() == year) {
                    Double amountToAdd = d.amount;
                    sum = sum + amountToAdd;
                }
            }
        }
        return sum;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public AbstractDonations[] getAllNonProfitDonations() {
        return allNonProfitDonations;
    }

    public void setAllNonProfitDonations(AbstractDonations[] allNonProfitDonations) {
        this.allNonProfitDonations = allNonProfitDonations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonProfit nonProfit = (NonProfit) o;
        return Objects.equals(organizationName, nonProfit.organizationName) && Arrays.equals(allNonProfitDonations, nonProfit.allNonProfitDonations);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(organizationName);
        result = 31 * result + Arrays.hashCode(allNonProfitDonations);
        return result;
    }

    @Override
    public String toString() {
        return "NonProfit{" +
                "organizationName='" + organizationName + '\'' +
                ", allNonProfitDonations=" + Arrays.toString(allNonProfitDonations) +
                '}';
    }
}
