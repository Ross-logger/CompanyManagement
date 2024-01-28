public class ExInsufficientCommandArgs extends Exception {
    public ExInsufficientCommandArgs() {
        super("Insufficient command arguments!");
    }

    public ExInsufficientCommandArgs(String message) {
        super(message);
    }
}
