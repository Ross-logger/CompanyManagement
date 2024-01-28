public class ExProjectIsOnFinalStage extends Exception {
    public ExProjectIsOnFinalStage() {
        super("The leave is invalid. Reason: Project will be in its final stage!");
    }

    public ExProjectIsOnFinalStage(String projectName, String start, String end) {
        super(String.format("The leave is invalid.  Reason: Project %s will be in its final stage during %s to %s.", projectName, start, end));
    }
}
