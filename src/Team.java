import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team> {
    /* Very simple - please follow lab sheet */
    private String teamName;
    private Employee head;
    private Day dateSetup;
    private ArrayList<Employee> teamMembers;
    private ArrayList<Project> assignedProjects;

    public Team(String n, Employee hd) {
        teamName = n;
        head = hd;
        dateSetup = SystemDate.getInstance().clone();
        teamMembers = new ArrayList<>();
        assignedProjects = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamHead() {
        return head.getName();
    }

    public ArrayList<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public boolean isTeamMember(Employee toCheck) {
        for (Employee e : teamMembers) {
            if (e.getName().equals(toCheck.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void list(ArrayList<Team> list) {

        System.out.printf("%-15s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
        for (Team t : list) {
            System.out.printf("%-15s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup); //display t.teamName, etc..
        }
    }

    public void addMemeber(Employee toAdd) {
        teamMembers.add(toAdd);
        Collections.sort(teamMembers);
    }

    public void addProject(Project toAdd) {
        assignedProjects.add(toAdd);
        Collections.sort(assignedProjects);
    }

    public void removeMember(Employee toRemove) {
        for (Employee e : teamMembers) {
            if (e.getName().equals(toRemove.getName())) {
                teamMembers.remove(e);
                break;
            }
        }
    }

    public void listMembers() {
        System.out.printf("%-10s%-10s%-13s\n", "Role", "Name", "Current / coming leaves");
        System.out.printf("%-10s%-10s%-13s\n", "Leader", head.getName(), head.getAllLeaves());
        for (Employee e : teamMembers) {
            System.out.printf("%-10s%-10s%-13s\n", "Member", e.getName(), e.getAllLeaves());
        }
    }

    public String listHeadAndMembersNames() {
        StringBuilder names = new StringBuilder();
        names.append(head.getName());
        for (Employee e : teamMembers) {
            names.append(", " + e.getName());
        }
        return String.valueOf(names);
    }


    public float predictedLoadFactor(Project project) {
        return ((1 + projectCount(project)) / teamMenPower(project));
    }


    public float teamMenPower(Project project) {
        float menPower = 0;
        for (Employee e : teamMembers) {
            menPower += e.employeeManPower(project);
        }
        menPower += head.employeeManPower(project);
        return menPower;
    }

    public float projectCount(Project project) {
        float totalCount = 0;
        for (Project p : assignedProjects) {
            totalCount += (float) Day.calculateCommonDays(p.getStart(), p.getEnd(), project.getStart(), project.getEnd()) / project.getDuration();
        }
        return totalCount;
    }


    @Override
    public int compareTo(Team another) {
        return this.teamName.compareTo(another.teamName);
    }


}
