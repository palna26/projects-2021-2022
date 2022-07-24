import java.time.LocalDateTime;
import java.util.Objects;

public class Pledges extends AbstractDonations {
    private LocalDateTime donationDateTime;

    public Pledges(Double amount, LocalDateTime dateTime, LocalDateTime donationDateTime) {
        super(amount, dateTime);
        this.donationDateTime = null;

    }

    public LocalDateTime getDonationDateTime() {
        return donationDateTime;
    }

    public void setDonationDateTime(LocalDateTime donationDateTime) throws InvalidDonationDateException {
        // Set the new donation date as long as the creation date is before the donation date
        if (creationDateTime.isBefore(donationDateTime)) {
            this.donationDateTime = donationDateTime;
        } else {
            // Otherwise, throw an exception
            throw new InvalidDonationDateException ("The donation date must be after the creation date.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pledges pledges = (Pledges) o;
        return Objects.equals(donationDateTime, pledges.donationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), donationDateTime);
    }

    @Override
    public String toString() {
        return "Pledges{" +
                "donationDateTime=" + donationDateTime +
                '}';
    }
}
