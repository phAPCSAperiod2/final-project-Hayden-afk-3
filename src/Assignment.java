import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assignment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int pointWorth;
    private int dueDay;
    private int dueMonth;
    private int dueYear;

    public Assignment(String name, int theDay, int theMonth, int theYear, int pointsWorth) {
        validatePoints(pointsWorth);
        validateDate(theDay, theMonth, theYear);

        this.name = name;
        this.pointWorth = pointsWorth;
        this.dueDay = theDay;
        this.dueMonth = theMonth;
        this.dueYear = theYear;
    }

    // ── Validation ────────────────────────────────────────────────────────────

    private static void validateDate(int day, int month, int year) {
        LocalDate due;
        try {
            due = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("That date doesn't exist. Please check the day and month.");
        }
        if (due.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Due date cannot be in the past.");
        }
    }

    private static void validatePoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public String getName()      {
        return name;
    }
    public int getPointWorth()   {
        return pointWorth;
    }
    public int getDueDay()       {
        return dueDay;
    }
    public int getDueMonth()     {
        return dueMonth;
    }
    public int getDueYear()      {
        return dueYear;
    }

    /** Returns due date as YYYYMMDD integer (used for sorting). */
    public int getDueDate() {
        return dueYear * 10000 + dueMonth * 100 + dueDay;
    }

    /** Returns how many days from today until this assignment is due. */
    public long getDaysUntilDue() {
        return ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(dueYear, dueMonth, dueDay));
    }

    // ── Setters ───────────────────────────────────────────────────────────────

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignment name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setDueDate(int newDay, int newMonth, int newYear) {
        validateDate(newDay, newMonth, newYear);
        this.dueDay = newDay;
        this.dueMonth = newMonth;
        this.dueYear = newYear;
    }

    public void setPointWorth(int newPoints) {
        validatePoints(newPoints);
        this.pointWorth = newPoints;
    }
}
