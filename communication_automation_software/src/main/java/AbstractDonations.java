import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractDonations {
    protected Double amount;
    protected LocalDateTime creationDateTime;

    public AbstractDonations(Double amount, LocalDateTime creationDateTime) {
        this.amount = amount;
        this.creationDateTime = creationDateTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDonations donations = (AbstractDonations) o;
        return Objects.equals(amount, donations.amount) && Objects.equals(creationDateTime, donations.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, creationDateTime);
    }

    @Override
    public String toString() {
        return "Donations{" +
                "amount=" + amount +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
