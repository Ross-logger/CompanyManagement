public class ExEmployeeHasAlreadyJoinedTeam extends Exception {
    public ExEmployeeHasAlreadyJoinedTeam() {
        super("[employee name] has already joined another team: [team name]");
    }

    public ExEmployeeHasAlreadyJoinedTeam(String employeeName, String teamName) {
        super(String.format("%s has already joined another team: %s", employeeName, teamName));
    }
}
