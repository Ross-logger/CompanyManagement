public class Leave implements Comparable<Leave> {
    private Day start;
    private Day end;

    Leave(String start, String finish) {
        this.start = new Day(start);
        this.end = new Day(finish);
    }

    public Day getStart() {
        return start;
    }

    public Day getEnd() {
        return end;
    }

    public boolean isOverlapped(Leave other) {
        if (this.start.compareTo(other.end) <= 0 && this.end.compareTo(other.start) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return start.toString() + " to " + end.toString();
    }

    @Override
    public int compareTo(Leave other) {
        return this.start.compareTo(other.start);
    }
}
