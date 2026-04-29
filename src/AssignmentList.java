import java.io.Serializable;
import java.util.ArrayList;

public class AssignmentList implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Assignment> assignments;

    public AssignmentList() {
        assignments = new ArrayList<>();
    }

    public void addAssignment(Assignment assignment) {
        int insertIndex = assignments.size();

        for (int i = 0; i < assignments.size(); i++) {
            Assignment current = assignments.get(i);

            if (assignment.getDueDate() < current.getDueDate()) {
                insertIndex = i;
                break;
            } else if (assignment.getDueDate() == current.getDueDate()) {
                if (assignment.getPointWorth() > current.getPointWorth()) {
                    insertIndex = i;
                    break;
                }
            }
        }

        assignments.add(insertIndex, assignment);
    }

    public Assignment getAssignment(int index) {
        return assignments.get(index);
    }

    public int size() {
        return assignments.size();
    }
}
