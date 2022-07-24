import java.time.LocalDateTime;
import java.util.Objects;

public class MonthlyDonations extends AbstractDonations {
    private LocalDateTime cancellationDateTime;


    public MonthlyDonations(Double amount, LocalDateTime creationDateTime,LocalDateTime cancellationDateTime) {
        super(amount, creationDateTime);
        // The cancellation date begins as null
        this.cancellationDateTime = null;
    }

    public LocalDateTime getCancellationDateTime() {
        return cancellationDateTime;
    }

    public void setCancellationDateTime(LocalDateTime cancellationDateTime) throws InvalidCancellationDateException {
        if(this.creationDateTime.isBefore(cancellationDateTime)) {
            // If creation date is before the cancellation date then set the new cancellation date
            this.cancellationDateTime = cancellationDateTime;
            // Throw an exception if the dateTime of cancellation is prior to creation
        } else throw new InvalidCancellationDateException("The cancellation date time is prior to creation date.");
    }

    /*
    Method returns a double value that is the total monthly donations from the given start date to given end date
    @param - start date and end date
    @return - double value
     */
    public Double calculateYearAmountUntil(LocalDateTime startDate, LocalDateTime endDate){
        LocalDateTime realEndDate = null;
        // If there is no cancellation date set on the monthly payment yet or the cancellation date is after the end date
        // set the endDate to the same as the input
        if(cancellationDateTime == null|| endDate.isBefore(cancellationDateTime)) {
            realEndDate = endDate;
        } else {
            // Otherwise, if the cancellation date is before the end date then set the endDate to be the cancellation date
            realEndDate = cancellationDateTime;
        }
        // Turn the date months into integer values so that the difference can be compared
        Integer startMonth = startDate.getMonthValue();
        Integer endMonth = realEndDate.getMonthValue();
        // Return the amount multiplied by however many months
        return amount*((endMonth-startMonth)+1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MonthlyDonations that = (MonthlyDonations) o;
        return Objects.equals(cancellationDateTime, that.cancellationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cancellationDateTime);
    }

    @Override
    public String toString() {
        return "MonthlyDonations{" +
                "cancellationDateTime=" + cancellationDateTime +
                '}';
    }
}
