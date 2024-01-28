public class CmdCreateProject extends RecordedCommand {

    private Project project;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExProjectAlreadyExists {
        Company company = Company.getInstance();
        if (cmdParts.length < 4) {
            throw new ExInsufficientCommandArgs();
        }
        if (company.isProjectExists(cmdParts[1])) {
            throw new ExProjectAlreadyExists();
        }
        try {
            int duration = Integer.parseInt(cmdParts[3]);
            project = company.createProject(cmdParts[1], cmdParts[2], duration);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Wrong number format for project duration!");
        }
        RecordedCommand.clearRedoList();
        RecordedCommand.addUndoCommand(this);
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Company company = Company.getInstance();
        company.removeProject(project);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company company = Company.getInstance();
        company.createProject(project.getNameProject(), project.getStartDay(), project.getDuration());
        addUndoCommand(this);
    }

    @Override
    public String toString() {
        return project.getNameProject();
    }
}
