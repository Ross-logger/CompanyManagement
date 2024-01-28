import java.util.ArrayList;
import java.util.Collections;

public class Company {
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;


    private static Company instance = new Company();

    private Company() {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
        allProjects = new ArrayList<>();
    }

    public static Company getInstance() {
        return instance;
    }


    public void listEmployees() {
        Employee.list(allEmployees);
    }

    public void listTeams() {
        Team.list(allTeams);
    }

    public void listMembersOfTeam(String teamName) {
        searchTeam(teamName).listMembers();
    }

    public void listProjects() {
        Project.list(allProjects);
    }

    public void listAllLeaves() {
        for (Employee e : allEmployees) {
            System.out.print(e.getName() + ": ");
            System.out.print(e.getAllLeaves());
            System.out.println();
        }
    }

    public void listAllLeaves(Employee e) {
        System.out.print(e.getName() + ": ");
        System.out.print(e.getAllLeaves());
        System.out.println();
    }


    public Employee createEmployee(String name, int annualLeaves) // See how it is called in main()
    {
        Employee e = new Employee(name, annualLeaves);
        allEmployees.add(e);
        Collections.sort(allEmployees); //allEmployees
        return e;
    }

    public Team createTeam(String nameTeam, String nameMemeber) // See how it is called in main()
    {
        Employee e = Employee.searchEmployee(allEmployees, nameMemeber);
        Team t = new Team(nameTeam, e);
        allTeams.add(t);
        Collections.sort(allTeams); //allTeams
        return t; //Why return?  Ans: Later you'll find it useful for undoable comments.
    }

    public Project createProject(String nameProject, String startDate, int duration) {
        Project p = new Project(nameProject, startDate, duration);
        allProjects.add(p);
        Collections.sort(allProjects);
        return p;
    }

    public void removeTeam(Team teamToDelete) {
        for (Team team : allTeams) {
            if (team.getTeamName().equals(teamToDelete.getTeamName())) {
                allTeams.remove(team);
                break;
            }
        }
    }

    public void removeEmployee(Employee employeeToDelete) {
        for (Employee employee : allEmployees) {
            if (employee.getName().equals(employeeToDelete.getName())) {
                allEmployees.remove(employee);
                break;
            }
        }
    }

    public void removeProject(Project projectToDelete) {
        for (Project project : allProjects) {
            if (project.getNameProject().equals(projectToDelete.getNameProject())) {
                allProjects.remove(project);
                break;
            }
        }
    }

    public Team searchTeam(String teamToSearch) {
        for (Team team : allTeams) {
            if (team.getTeamName().equals(teamToSearch)) {
                return team;
            }
        }
        return null;
    }

    public Employee searchEmployee(String nameToSearch) {
        for (Employee e : allEmployees) {
            if (e.getName().equals(nameToSearch)) {
                return e;
            }
        }
        return null;
    }


    public Project searchProject(String projectToSearch) {
        for (Project project : allProjects) {
            if (project.getNameProject().equals(projectToSearch)) {
                return project;
            }
        }
        return null;
    }

    public void addEmployee(Employee e) {
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }

    public boolean isTeamExists(String teamName) {
        for (Team t : allTeams) {
            if (t.getTeamName().equals(teamName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmployeeExists(String employeeName) {
        for (Employee e : allEmployees) {
            if (e.getName().equals(employeeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProjectExists(String projectName) {
        for (Project p : allProjects) {
            if (p.getNameProject().equals(projectName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProjectAssigned(String projectName) {
        for (Project p : allProjects) {
            if (p.getNameProject().equals(projectName) && p.getAssignedTeam() != null) {
                return true;
            }
        }
        return false;
    }

    public Project projectAssigned(String teamName) {
        for (Project p : allProjects) {
            if (p.getAssignedTeam().getTeamName().equals(teamName)) {
                return p;
            }
        }
        return null;
    }

    public boolean isJoinedTeam(Employee e) {
        for (Team t : allTeams) {
            if (t.isTeamMember(e) || e.getName().equals(t.getTeamHead())) {
                return true;
            }
        }
        return false;
    }

    public String employeeJoinedTeam(Employee e) {
        for (Team t : allTeams) {
            if (t.isTeamMember(e) || e.getName().equals(t.getTeamHead())) {
                return t.getTeamName();
            }
        }
        return null;
    }

    public String bestTeamBalancedLoad(Project project) {
        Team best = null;
        for (Team t : allTeams) {
            if (best == null || t.predictedLoadFactor(project) < best.predictedLoadFactor(project)) {
                best = t;
            }
        }
        if (best == null) {
            return "";
        }
        return best.getTeamName();
    }

    public void predictLoadFactor(Project project) {
        System.out.printf("During the period of project %s (%s):\n", project.getNameProject(), project.getStart().toString() + " to " + project.getEnd().toString());
        System.out.printf("  Average manpower (m) and count of existing projects (p) of each team:\n");
        for (Team t : allTeams) {
            System.out.printf("    %s: m=%.2f workers, p=%.2f projects\n", t.getTeamName(), t.teamMenPower(project), t.projectCount(project));
        }
        System.out.printf("  Projected loading factor when a team takes this project %s:\n", project.getNameProject());
        for (Team t : allTeams) {
            System.out.printf("    %s: (p+1)/m = %.2f\n", t.getTeamName(), t.predictedLoadFactor(project));
        }
        System.out.printf("Conclusion: %s should be assigned to %s for best balancing of loading\n", project.getNameProject(), bestTeamBalancedLoad(project));
    }

}
