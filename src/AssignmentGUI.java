import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AssignmentGUI {

    private AssignmentList list;
    private DefaultListModel<String> listModel;
    private JList<String> displayList;

    public AssignmentGUI() {

        // Load saved data
        list = FileManager.load();

        JFrame frame = new JFrame("Assignment Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        displayList = new JList<>(listModel);

        refreshList();

        JButton addButton  = new JButton("Add Assignment");
        JButton editButton = new JButton("Edit Assignment");

        addButton.addActionListener(e -> addAssignment());
        editButton.addActionListener(e -> editAssignment());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(displayList), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // ── Refresh ───────────────────────────────────────────────────────────────

    private void refreshList() {
        listModel.clear();

        for (int i = 0; i < list.size(); i++) {
            Assignment a = list.getAssignment(i);

            long days = a.getDaysUntilDue();
            String daysLabel;
            if (days == 0) {
                daysLabel = "Due today";
            } else if (days == 1) {
                daysLabel = "1 day left";
            } else {
                daysLabel = days + " days left";
            }

            listModel.addElement(
                a.getName() + "  |  " + a.getPointWorth() + " pts  |  " + daysLabel
            );
        }
    }

    // ── Add ───

    private void addAssignment() {

        String name     = JOptionPane.showInputDialog("Enter assignment name:");
        String date     = JOptionPane.showInputDialog("Enter due date (YYYY/MM/DD):");
        String pointsStr = JOptionPane.showInputDialog("Enter points:");

        // User hit Cancel on any dialog
        if (name == null || date == null || pointsStr == null) return;

        try {
            name = name.trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Assignment name cannot be empty.");
            }

            String[] parts = date.split("/");
            int year  = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day   = Integer.parseInt(parts[2]);
            int points = Integer.parseInt(pointsStr.trim());

            // Assignment constructor validates date (not in past) and points (not negative)
            Assignment a = new Assignment(name, day, month, year, points);

            list.addAssignment(a);
            FileManager.save(list);
            refreshList();

        } catch (IllegalArgumentException e) {
            // Covers: negative points, past date, bad date, empty name
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Covers: bad format, wrong number of date parts, etc.
            JOptionPane.showMessageDialog(null,
                "Please enter the date as YYYY/MM/DD and a whole number for points.",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Edit ───

    private void editAssignment() {

        int selectedIndex = displayList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select an assignment to edit.");
            return;
        }

        Assignment a = list.getAssignment(selectedIndex);

        // Pre-fill prompts with current values so the user can see what they're changing
        String name = JOptionPane.showInputDialog("Edit name:", a.getName());
        String date = JOptionPane.showInputDialog("Edit due date (YYYY/MM/DD):",
                a.getDueYear() + "/" + String.format("%02d", a.getDueMonth())
                               + "/" + String.format("%02d", a.getDueDay()));
        String pointsStr = JOptionPane.showInputDialog("Edit points:", a.getPointWorth());

        if (name == null || date == null || pointsStr == null) return;

        try {
            name = name.trim();
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Assignment name cannot be empty.");
            }

            String[] parts = date.split("/");
            int year   = Integer.parseInt(parts[0]);
            int month  = Integer.parseInt(parts[1]);
            int day    = Integer.parseInt(parts[2]);
            int points = Integer.parseInt(pointsStr.trim());

            // Remove old, insert updated so sorted order is maintained
            list.getAssignments().remove(selectedIndex);
            Assignment updated = new Assignment(name, day, month, year, points);
            list.addAssignment(updated);

            FileManager.save(list);
            refreshList();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Please enter the date as YYYY/MM/DD and a whole number for points.",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
