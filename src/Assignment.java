/**
 * Represents an Assignment with a point value and due date.
 */
public class Assignment {
    private int pointWorth;
    private int dueDate;

    /**
     * Constructs an Assignment with the specified point worth and due date.
     *
     * @param pointWorth the number of points this assignment is worth
     * @param dueDate    the due date of this assignment
     */
    public Assignment (int pointsWorth, int theDate) {
        pointWorth = pointsWorth;
        dueDate = theDate;
    }

    /**
     * Gets the point worth of this assignment.
     *
     * @return the number of points this assignment is worth
     */
    public int getPointWorth() {
        return pointWorth;
    }

    /**
     * Gets the due date of this assignment.
     *
     * @return the due date of this assignment
     */
    public int getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of assignment
     *
     *  @param dueDate the new Date to set
     */
    public void setDueDate(int newDate){
        this.dueDate = newDate;
    }
}
