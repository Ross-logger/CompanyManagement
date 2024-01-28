public class CmdHire extends RecordedCommand {

    private Employee e;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExEmployeeAlreadyExists {
        Company company = Company.getInstance();
        if (cmdParts.length < 3) {
            throw new ExInsufficientCommandArgs();
        }
        if (company.isEmployeeExists(cmdParts[1])) {
            throw new ExEmployeeAlreadyExists();
        }
        try {
            int annualLeaves = Integer.parseInt(cmdParts[2]);
            e = company.createEmployee(cmdParts[1], annualLeaves);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Wrong number format for annual leaves!");
        }
        RecordedCommand.addUndoCommand(this);
        RecordedCommand.clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Company company = Company.getInstance();
        company.removeEmployee(e);
        RecordedCommand.addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company company = Company.getInstance();
        company.addEmployee(e);
        RecordedCommand.addUndoCommand(this);
    }

    @Override
    public String toString() {
        return e.getName();
    }
}
