import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        File output = new File("output.txt");
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(output);

        int q = Integer.parseInt(scanner.nextLine());
        SmartHomeSystem system = new SmartHomeSystem();

        for (int i = 0; i < q; i++) {
            String command = scanner.nextLine();
            String response = system.handleCommand(command);
            out.println(response);
        }

        scanner.close();
        out.close();
    }
}
