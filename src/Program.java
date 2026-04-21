public class Program{
    public static void main(String[] args) throws Exception {


        Assignment testy1 = new Assignment(5, 10);

        //Tests

        System.out.println(testy1.getPointWorth());
        System.out.println(testy1.getDueDate());
        testy1.setDueDate(9);
        System.out.println(testy1.getDueDate());

    }
}
