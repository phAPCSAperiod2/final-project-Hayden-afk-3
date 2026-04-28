import java.time.LocalDate;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws Exception {

        /**
         * Main method
         * loop while active
         * Options:
         * 1 add new item to list
         * 2 access assignment
         * 3 change assignment
         * 4 quit
         */
        Scanner scan = new Scanner(System.in);
        boolean active = true;
        int assi = 0;
        LocalDate todayDate = LocalDate.now();
        String dateString = todayDate.toString();
        String[] parts = dateString.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        List myList = new List();
        while (active) {
            System.out.println(
                    "What would you like to do (1/2/3/4):\n1. Add a new assignment\n2. View list\n3. Change assignment's attributes\n4. Quit");
            int opt = scan.nextInt();
            switch (opt) {
                case 1:
                    if (opt == 1) {
                        // new Item
                        System.out.println("What day is it due? (YYYY/MM/DD)");
                        String dueInput = scan.next();
                        String[] partsInput = dueInput.split("/");
                        int yearInput = Integer.parseInt(partsInput[0]);
                        int monthInput = Integer.parseInt(partsInput[1]);
                        int dayInput = Integer.parseInt(partsInput[2]);

                        System.out.println("How many points is it worth");
                        int pointsInput = scan.nextInt();

                        Assignment a = new Assignment(dayInput, monthInput, yearInput, pointsInput);
                        myList.addAssignment(a);
                        assi++;
                    }
                    break;
                case 2:
                    if (opt == 2) {
                        // access item / view assignment
                        if (myList.size() == 0) {
                            System.out.println("No assignments.");
                        } else {
                            for (int i = 0; i < myList.size(); i++) {
                                Assignment cur = myList.getAssignment(i);
                                System.out.println((i + 1) + ": Due " + cur.getDueDate() + ", Points " + cur.getPointWorth());
                            }
                        }
                    }
                    break;
                case 3:
                    if (opt == 3) {
                        // change item
                        if (myList.size() == 0) {
                            System.out.println("No assignments to edit.");
                            break;
                        }

                        System.out.println("Enter assignment order:");
                        int index = scan.nextInt();

                        if (index < 1 || index > myList.size()) {
                            System.out.println("Invalid index.");
                            break;
                        }

                        Assignment edit = myList.getAssignment(index - 1);

                        System.out.println("1. Change due date\n2. Change points");
                        int choice = scan.nextInt();

                        if (choice == 1) {
                            System.out.println("Enter new due date (YYYY/MM/DD):");
                            String newDateInput = scan.next();
                            String[] newDateParts = newDateInput.split("/");
                            int newYear = Integer.parseInt(newDateParts[0]);
                            int newMonth = Integer.parseInt(newDateParts[1]);
                            int newDay = Integer.parseInt(newDateParts[2]);
                            edit.setDueDate(newDay, newMonth, newYear);
                        } else if (choice == 2) {
                            System.out.println("Enter new point value:");
                            int newPoints = scan.nextInt();
                            edit.setPointWorth(newPoints);
                        } else {
                            System.out.println("Invalid option.");
                        }

                        System.out.println("Assignment updated.");
                    }
                    break;
                case 4:
                    if (opt == 4) {
                        System.out.print("Good-Bye");
                        active = false;
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        }
    }
}
