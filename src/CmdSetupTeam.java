public class CmdSetupTeam extends RecordedCommand {
    private Team team;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExTeamAlreadyExists, ExEmployeeNotFound, ExEmployeeHasAlreadyJoinedTeam {
        if (cmdParts.length < 3) {
            throw new ExInsufficientCommandArgs();
        }
        Company company = Company.getInstance();
        String teamName = cmdParts[1];
        String employeeName = cmdParts[2];
        if (company.isTeamExists(teamName)) {
            throw new ExTeamAlreadyExists();
        }
        if (!company.isEmployeeExists(employeeName)) {
            throw new ExEmployeeNotFound();
        }
        if (company.isJoinedTeam(company.searchEmployee(employeeName))) {
            throw new ExEmployeeHasAlreadyJoinedTeam(employeeName, company.employeeJoinedTeam(company.searchEmployee(employeeName)));
        }
        team = company.createTeam(teamName, employeeName);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Company company = Company.getInstance();
        company.removeTeam(team);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company company = Company.getInstance();
        company.createTeam(team.getTeamName(), team.getTeamHead());
        addUndoCommand(this);

    }

    public String toString() {
        return team.getTeamName();
    }
}