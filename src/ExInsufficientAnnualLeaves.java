public class ExInsufficientAnnualLeaves extends Exception {
    public ExInsufficientAnnualLeaves() {
        super("Insufficient balance of annual leave.");
    }

    public ExInsufficientAnnualLeaves(int leftLeaves) {
        super(String.format("Insufficient balance of annual leave. %d days left only!", leftLeaves));
    }
}
