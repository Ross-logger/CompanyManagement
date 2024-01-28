public class CmdTakeLeave extends RecordedCommand {
    private Employee e;
    private int leaveDuration;

    private boolean isFinalStage;
    private Leave leaveRecord;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExEmployeeNotFound, ExInsufficientAnnualLeaves, ExLeaveOverlapped, ExProjectIsOnFinalStage {
        Company company = Company.getInstance();
        if (cmdParts.length < 4) {
            throw new ExInsufficientCommandArgs();
        }
        String employeeName = cmdParts[1];
        String startLeaveString = cmdParts[2];
        String endLeaveString = cmdParts[3];
        if (!company.isEmployeeExists(employeeName)) {
            throw new ExEmployeeNotFound();
        }
        e = company.searchEmployee(employeeName);
        Day startLeave = new Day(startLeaveString);
        Day endLeave = new Day(endLeaveString);
        leaveDuration = Day.calculateDuration(startLeave, endLeave);
        if (e.getAnnualLeaves() < leaveDuration) {
            throw new ExInsufficientAnnualLeaves(e.getAnnualLeaves());
        }
        leaveRecord = new Leave(startLeaveString, endLeaveString);
        if (e.isLeaveOverlapped(leaveRecord) != null) {
            throw new ExLeaveOverlapped(e.isLeaveOverlapped(leaveRecord).toString());
        }
        Project eProject = e.projectOnFinalStage(leaveRecord);
        if (eProject != null && eProject.isFinalStage(leaveRecord)) {
            throw new ExProjectIsOnFinalStage(eProject.getNameProject(), eProject.getStartFinalStage().toString(), eProject.getEndFinalStage().toString());
        }
        e.ChangeAnnualLeaves(-leaveDuration);
        e.addLeave(leaveRecord);
        RecordedCommand.addUndoCommand(this);
        RecordedCommand.clearRedoList();
        System.out.println(String.format("Done.  %s's remaining annual leave: %d days", e.getName(), e.getAnnualLeaves()));
    }

    @Override
    public void undoMe() {
        e.ChangeAnnualLeaves(leaveDuration);
        e.removeLeave(leaveRecord);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        e.ChangeAnnualLeaves(-leaveDuration);
        e.addLeave(leaveRecord);
        addUndoCommand(this);
    }
}
