public class CmdStartNewDay extends RecordedCommand {

    private String curDate;

    private String prevDate;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs {
        if (cmdParts.length < 2) {
            throw new ExInsufficientCommandArgs();
        }
        SystemDate curSystemDate = SystemDate.getInstance();
        prevDate = curSystemDate.toString();
        curSystemDate.set(cmdParts[1]);
        curDate = curSystemDate.toString();
        RecordedCommand.addUndoCommand(this);
        RecordedCommand.clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        SystemDate curSystemDate = SystemDate.getInstance();
        curSystemDate.set(prevDate);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        SystemDate curSystemDate = SystemDate.getInstance();
        curSystemDate.set(curDate);
        addUndoCommand(this);
    }
}
