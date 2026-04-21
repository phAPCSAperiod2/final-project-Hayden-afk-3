import java.util.ArrayList;

public class List {
    private ArrayList<Assignment> assignments;

    public List() {
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

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public Assignment getAssignment(int index) {
        return assignments.get(index);
    }

    public int size() {
        return assignments.size();
    }
}
