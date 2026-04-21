public class Program{
    public static void main(String[] args) throws Exception {


        Assignment testy1 = new Assignment(5, 10);

        // Tests for Assignment

        System.out.println(testy1.getPointWorth());
        System.out.println(testy1.getDueDate());
        testy1.setDueDate(9);
        System.out.println(testy1.getDueDate());

        // Tests for List
        List myList = new List();
        System.out.println("List size after creation: " + myList.size());  // Should be 0

        Assignment a1 = new Assignment(5, 10);
        myList.addAssignment(a1);
        System.out.println("Size after adding: " + myList.size());  // Should be 1
        System.out.println("First assignment due date: " + myList.getAssignment(0).getDueDate());  // Should be 5
    }
}
