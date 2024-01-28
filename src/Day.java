public class Day implements Cloneable, Comparable<Day> {
    private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";

    private int year;
    private int month;
    private int day;


    public Day(String sDay) {
        set(sDay);
    } //Constructor, simply call set(sDay)


    public void set(String sDay) //Set year,month,day based on a string like 01-Jan-2022
    {
        String[] sDayParts = sDay.split("-");
        this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
        this.year = Integer.parseInt(sDayParts[2]);
        this.month = MonthNames.indexOf(sDayParts[1]) / 3 + 1;
    }

    // check if a given year is a leap year
    static public boolean isLeapYear(int y) {
        if (y % 400 == 0)
            return true;
        else if (y % 100 == 0)
            return false;
        else if (y % 4 == 0)
            return true;
        else
            return false;
    }

    // check if y,m,d valid
    static public boolean valid(int y, int m, int d) {
        if (m < 1 || m > 12 || d < 1) return false;
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return d <= 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return d <= 30;
            case 2:
                if (isLeapYear(y))
                    return d <= 29;
                else
                    return d <= 28;
        }
        return false;
    }

    // Return a string for the day like dd MMM yyyy
    @Override
    public String toString() {
        return day + "-" + MonthNames.substring((this.month - 1) * 3, (this.month) * 3) + "-" + year; // (month-1)*3,(month)*3
    }

    @Override
    public Day clone() {
        Day copy = null;
        try {
            copy = (Day) super.clone();
        } catch (CloneNotSupportedException error) {
            error.printStackTrace();
        }
        return copy;
    }

    public static Day calculateEndDay(String startDate, int duration) {
        Day endDate = new Day(startDate);
        endDate.day += (duration - 1);

        while (!valid(endDate.year, endDate.month, endDate.day)) {

            int maxDays = 31;
            switch (endDate.month) {
                case 4:
                case 6:
                case 9:
                case 11:
                    maxDays = 30;
                    break;
                case 2:
                    if (isLeapYear(endDate.year)) {
                        maxDays = 29;
                    } else {
                        maxDays = 28;
                    }
                    break;
            }

            if (endDate.day > maxDays) {
                endDate.day -= maxDays;
                endDate.month++;
            }
        }

        if (endDate.month > 12) {
            endDate.month = 1;
            endDate.year++;
        }

        return endDate;
    }

    public static int calculateDuration(Day start, Day finish) {
        int calcDuration = 0;
        Day calcDate = start.clone();

        while (calcDate.compareTo(finish) <= 0) {
            calcDuration++;
            calcDate = calculateEndDay(calcDate.toString(), 2); //getting next day
        }

        return calcDuration;
    }


    //recheck this function!!!

    public Day subtractDays(int days) {
        Day result = this.clone();
        result.day -= days;

        while (result.day < 1) {
            result.month--;
            if (result.month < 1) {
                result.month = 12;
                result.year--;
            }

            int maxDays = 31;

            switch (result.month) {
                case 4:
                case 6:
                case 9:
                case 11:
                    maxDays = 30;
                    break;
                case 2:
                    if (isLeapYear(result.year)) {
                        maxDays = 29;
                    } else {
                        maxDays = 28;
                    }
                    break;
            }

            result.day += maxDays;
        }

        return result;
    }

    public static int calculateCommonDays(Day start1, Day end1, Day start2, Day end2) {
        if (end1.compareTo(start1) < 0 || end2.compareTo(start2) < 0) {
            return 0; // Invalid input, no common days
        }

        Day commonStart;
        if (start1.compareTo(start2) >= 0) {
            commonStart = start1;
        } else {
            commonStart = start2;
        }

        Day commonEnd;
        if (end1.compareTo(end2) <= 0) {
            commonEnd = end1;
        } else {
            commonEnd = end2;
        }

        int commonDays = 0;
        Day currentDay = commonStart.clone();

        while (currentDay.compareTo(commonEnd) <= 0) {
            commonDays++;
            currentDay = currentDay.getNextDay();
        }

        return commonDays;
    }

    public Day getNextDay() {
        int maxDays = 31;

        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                maxDays = 30;
                break;
            case 2:
                if (isLeapYear(year)) {
                    maxDays = 29;
                } else {
                    maxDays = 28;
                }
                break;
        }

        if (day < maxDays) {
            day++;
        } else {
            day = 1;
            if (month < 12) {
                month++;
            } else {
                month = 1;
                year++;
            }
        }

        return this;
    }


    @Override
    public int compareTo(Day other) {
        if (this.year > other.year) {
            return 1;
        } else if (this.year < other.year) {
            return -1;
        } else if (this.month > other.month) {
            return 1;
        } else if (this.month < other.month) {
            return -1;
        } else if (this.day > other.day) {
            return 1;
        } else if (this.day < other.day) {
            return -1;
        } else {
            return 0;
        }
    }
}
