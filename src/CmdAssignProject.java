public class CmdAssignProject extends RecordedCommand {

    private Project project;
    private Team prevAssignedTeam;
    private Team curAssignedTeam;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExProjectNotFound, ExTeamNotFound, ExProjectIsAlreadyAssigned {
        Company company = Company.getInstance();
        if (cmdParts.length < 3) {
            throw new ExInsufficientCommandArgs();
        }
        if (!company.isProjectExists(cmdParts[1])) {
            throw new ExProjectNotFound();
        }
        if (!company.isTeamExists(cmdParts[2])) {
            throw new ExTeamNotFound();
        }
        if (company.isProjectAssigned(cmdParts[1])) {
            throw new ExProjectIsAlreadyAssigned(company.searchProject(cmdParts[1]).getAssignedTeam().getTeamName());
        }
        project = company.searchProject(cmdParts[1]);
        prevAssignedTeam = project.getAssignedTeam();
        project.assignTeam(company.searchTeam(cmdParts[2]));
        curAssignedTeam = project.getAssignedTeam();
        RecordedCommand.addUndoCommand(this);
        RecordedCommand.clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        project.assignTeam(prevAssignedTeam);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        project.assignTeam(curAssignedTeam);
        addRedoCommand(this);
    }
}
