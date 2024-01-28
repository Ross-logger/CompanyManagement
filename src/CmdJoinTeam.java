public class CmdJoinTeam extends RecordedCommand {

    private Employee e;
    private Team t;


    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExEmployeeNotFound, ExTeamNotFound, ExEmployeeHasAlreadyJoinedTeam {
        Company company = Company.getInstance();

        if (cmdParts.length < 3) {
            throw new ExInsufficientCommandArgs();
        }
        String employeeName = cmdParts[1];
        String teamName = cmdParts[2];
        if (!company.isEmployeeExists(employeeName)) {
            throw new ExEmployeeNotFound();
        }
        if (!company.isTeamExists(teamName)) {
            throw new ExTeamNotFound();
        }
        Employee employee = company.searchEmployee(employeeName);
        if (company.isJoinedTeam(employee)) {
            throw new ExEmployeeHasAlreadyJoinedTeam(employeeName, company.employeeJoinedTeam(employee));
        }
        e = company.searchEmployee(employeeName);
        t = company.searchTeam(teamName);
        t.addMemeber(e);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        t.removeMember(e);
        addRedoCommand(this);

    }

    @Override
    public void redoMe() {
        t.addMemeber(e);
        addUndoCommand(this);

    }
}
