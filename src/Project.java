import java.util.ArrayList;

public class Project implements Comparable<Project> {
    private String nameProject;
    private Day startDay;
    private int duration;
    private Day endDay; //also a finishFinalStage
    private Team assignedTeam;
    private Day startFinalStage;

    public Project(String nameProject, String startDate, int duration) {
        this.nameProject = nameProject;
        this.startDay = new Day(startDate);
        this.duration = duration;
        this.endDay = Day.calculateEndDay(startDate, duration);
        if (duration < 5) {
            startFinalStage = this.startDay;
        } else {
            startFinalStage = endDay.subtractDays(4);
        }
    }

    public String getNameProject() {
        return nameProject;
    }

    public String getStartDay() {
        return startDay.toString();
    }

    public int getDuration() {
        return duration;
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }

    public Day getStartFinalStage() {
        return startFinalStage;
    }

    public Day getEndFinalStage() {
        return endDay;
    }

    public Day getStart() {
        return startDay;
    }

    public Day getEnd() {
        return endDay;
    }

    // last 5 days are final stage (if project < 5 days - always final stage)
    public boolean isFinalStage(Leave leaveRecord) {
        Leave finalDates = new Leave(startFinalStage.toString(), endDay.toString());
        return finalDates.isOverlapped(leaveRecord);

    }

    public void assignTeam(Team teamToAssign) {
        this.assignedTeam = teamToAssign;
        teamToAssign.addProject(this);
    }

    public static void list(ArrayList<Project> list) {
        System.out.printf("%-9s%-13s%-13s%-13s\n", "Project", "Start Day", "End Day", "Team");
        for (Project p : list) {
            System.out.printf("%-9s%-13s%-13s%-13s\n", p.nameProject, p.startDay.toString(), p.endDay.toString(), (p.assignedTeam != null) ? p.assignedTeam.getTeamName() + " (" + p.assignedTeam.listHeadAndMembersNames() + ")" : "--");
        }
    }


    @Override
    public int compareTo(Project another) {
        return this.nameProject.compareTo(another.nameProject);
    }
}