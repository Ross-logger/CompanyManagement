public class ExLeaveOverlapped extends Exception {
    public ExLeaveOverlapped() {
        super("Leave overlapped!");
    }

    public ExLeaveOverlapped(String leaveInfo) {
        super(String.format("Leave overlapped: The leave period %s is found!", leaveInfo));
    }
}
