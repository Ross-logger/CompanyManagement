public class CmdListLeaves implements Command {

    @Override
    public void execute(String[] cmdParts) throws ExEmployeeNotFound {
        Company company = Company.getInstance();
        if (cmdParts.length > 1) {
            String employeeName = cmdParts[1];
            if (company.isEmployeeExists(employeeName)) {
                Employee e = company.searchEmployee(employeeName);
                company.listAllLeaves(e);
            } else {
                throw new ExEmployeeNotFound();
            }
        } else {
            company.listAllLeaves();
        }
    }
}
