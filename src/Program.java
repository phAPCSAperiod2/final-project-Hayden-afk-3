import java.util.Scanner;

public class Program{
    public static void main(String[] args) throws Exception {


        //Assignment testy1 = new Assignment(5, 10);

        // Tests for Assignment

        //System.out.println(testy1.getPointWorth());
        //System.out.println(testy1.getDueDate());
        //testy1.setDueDate(9);
        //System.out.println(testy1.getDueDate());

        // Tests for List
        // List myList = new List();
        // System.out.println("List size after creation: " + myList.size());  // Should be 0

        //Assignment a1 = new Assignment(5, 10);
        //myList.addAssignment(a1);
        //System.out.println("Size after adding: " + myList.size());  // Should be 1
        //System.out.println("First assignment due date: " + myList.getAssignment(0).getDueDate());  // Should be 5

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
        while (active)
        {
            System.out.println("Question?? ");
            int opt = scan.nextInt();
            switch (opt){
                case 1:
                    if (opt == 1){
                        //new Item
                    }
                    break;
                case 2:
                    if (opt == 2){
                        //access item / view assignment
                    }
                    break;
                case 3:
                    if (opt == 3){
                        //change item
                    }
                    break;
                case 4:
                    if (opt == 4){
                        System.out.print("Good-Bye");
                        active = false;
                    }
                    break:
                default:
                    //ask again
                    break;
            }


        }
    }
}
