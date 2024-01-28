public class CmdListTeamMembers implements Command {

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCommandArgs, ExTeamNotFound {
        Company company = Company.getInstance();
        if (cmdParts.length < 2) {
            throw new ExInsufficientCommandArgs();
        }
        String teamName = cmdParts[1];
        if (!company.isTeamExists(teamName)) {
            throw new ExTeamNotFound();
        }
        company.listMembersOfTeam(teamName);
    }

}
