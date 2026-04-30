import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class AssignmentGUI {

    private AssignmentList list;
    private DefaultListModel<String> listModel;
    private JList<String> displayList;

    public AssignmentGUI() {

        // load saved data
        list = FileManager.load();

        JFrame frame = new JFrame("Assignment Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        displayList = new JList<>(listModel);

        refreshList();

        JButton addButton = new JButton("Add Assignment");

        addButton.addActionListener(e -> addAssignment());

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(displayList), BorderLayout.CENTER);
        frame.add(addButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    private void refreshList() {
        listModel.clear();

        for (int i = 0; i < list.size(); i++) {
            Assignment a = list.getAssignment(i);

            listModel.addElement(
                "Due: " + a.getDueDate() + " | Points: " + a.getPointWorth()
            );
        }
    }
    private void addAssignment() {

        String date = JOptionPane.showInputDialog("Enter due date (YYYY/MM/DD):");
        String pointsStr = JOptionPane.showInputDialog("Enter points:");

        try {
            String[] parts = date.split("/");

            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            int points = Integer.parseInt(pointsStr);

            Assignment a = new Assignment(day, month, year, points);

            list.addAssignment(a);

            FileManager.save(list.getAssignments());   // SAVE HERE
            refreshList();            // UPDATE UI

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input.");
        }
    }
}
