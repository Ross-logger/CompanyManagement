import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee> {
    private String name;
    private int entitledAnnualLeaves;
    private int leftAnnualLeaves;
    private ArrayList<Leave> allTakenLeaves;

    public Employee(String n, int yle) {
        this.name = n;
        this.entitledAnnualLeaves = yle;
        this.leftAnnualLeaves = yle;
        allTakenLeaves = new ArrayList<>();
    }

    public static void list(ArrayList<Employee> allEmployees) {
        for (Employee e : allEmployees) {
            System.out.printf("%s (Entitled Annual Leaves: %d days)\n", e.name, e.entitledAnnualLeaves);
        }
    }

    public String getName() {
        return name;
    }

    public int getAnnualLeaves() {
        return leftAnnualLeaves;
    }

    public void ChangeAnnualLeaves(int toAdd) {
        leftAnnualLeaves += toAdd;
    }

    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
        for (Employee e : list) {
            if (e.getName().equals(nameToSearch)) {
                return e;
            }
        }
        return null;
    }

    public void addLeave(Leave toAdd) {
        allTakenLeaves.add(toAdd);
        Collections.sort(allTakenLeaves);
    }

    public void removeLeave(Leave toRemove) {
        for (Leave l : allTakenLeaves) {
            if (l.toString().equals(toRemove.toString())) {
                allTakenLeaves.remove(l);
                break;
            }
        }
    }

    public String getAllLeaves() {
        StringBuilder outputLeaves = new StringBuilder();

        for (Leave l : allTakenLeaves) {
            if (l.getEnd().compareTo(SystemDate.getInstance()) > -1) { // Checking whether leave is current or upcoming
                if (outputLeaves.length() > 0) {
                    outputLeaves.append(", ");
                }
                outputLeaves.append(l);
            }
        }
        if (outputLeaves.length() == 0) {
            outputLeaves.append("--");
        }
        return outputLeaves.toString();
    }

    public Leave isLeaveOverlapped(Leave leaveWanted) {
        for (Leave l : allTakenLeaves) {
            if (l.isOverlapped(leaveWanted)) {
                return l;
            }
        }
        return null;
    }

    public Project projectOnFinalStage(Leave leaveRecord) {
        Company company = Company.getInstance();
        if (!company.isJoinedTeam(this)) {
            return null;
        }
        String esTeam = company.employeeJoinedTeam(this);
        for (Project p : company.searchTeam(esTeam).getAssignedProjects()) {
            if (p.isFinalStage(leaveRecord)) {
                return p;
            }
        }
        return null;
    }

    public float employeeManPower(Project project) {
        float manPower = 0;
        int commonDays = 0;
        for (Leave l : allTakenLeaves) {
            commonDays += Day.calculateCommonDays(l.getStart(), l.getEnd(), project.getStart(), project.getEnd());
        }
        manPower = 1 - ((float) commonDays / project.getDuration());
        return manPower;
    }

    @Override
    public int compareTo(Employee another) {
        return this.name.compareTo(another.name);
    }
}
