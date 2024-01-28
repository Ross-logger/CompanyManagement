public interface Command {
    void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExTeamAlreadyExists, ExEmployeeNotFound, ExProjectAlreadyExists, ExProjectNotFound, ExTeamNotFound, ExEmployeeAlreadyExists, ExProjectIsAlreadyAssigned, ExEmployeeHasAlreadyJoinedTeam, ExInsufficientAnnualLeaves, ExLeaveOverlapped, ExProjectIsOnFinalStage;
}