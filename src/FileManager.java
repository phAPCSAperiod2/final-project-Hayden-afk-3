import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    public static void save(AssignmentList list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("assignments.dat"))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AssignmentList load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("assignments.dat"))) {
            return (AssignmentList) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new AssignmentList();
        }
    }
}
