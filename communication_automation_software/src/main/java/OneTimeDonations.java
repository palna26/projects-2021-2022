import java.time.LocalDateTime;

public class OneTimeDonations extends AbstractDonations {

    public OneTimeDonations(Double amount, LocalDateTime creationDateTime) {
        super(amount, creationDateTime);
    }

}
