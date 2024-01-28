public class ExProjectIsAlreadyAssigned extends Exception {
    public ExProjectIsAlreadyAssigned() {
        super("This project is already assigned to the team: [team name]");
    }

    public ExProjectIsAlreadyAssigned(String teamName) {
        super("This project is already assigned to the team: " + teamName);
    }
}
