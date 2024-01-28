public class CmdSuggestTeam implements Command {

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExTeamAlreadyExists, ExEmployeeNotFound, ExProjectAlreadyExists, ExProjectNotFound, ExTeamNotFound, ExEmployeeAlreadyExists, ExProjectIsAlreadyAssigned, ExEmployeeHasAlreadyJoinedTeam, ExInsufficientAnnualLeaves, ExLeaveOverlapped, ExProjectIsOnFinalStage {
        Company company = Company.getInstance();
        if (cmdParts.length < 2) {
            throw new ExInsufficientCommandArgs();
        }
        String projectName = cmdParts[1];
        if (!company.isProjectExists(projectName)) {
            throw new ExProjectNotFound();
        }
        if (company.isProjectAssigned(projectName)) {
            throw new ExProjectIsAlreadyAssigned();
        }
        company.predictLoadFactor(company.searchProject(projectName));

    }
}
