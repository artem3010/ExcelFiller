import java.util.Scanner;

public class PathScanner {
    private static Scanner scanner;
    static{
        scanner = new Scanner(System.in);
    }
    public static String scan(){
        String result = scanner.nextLine();
        scanner.nextLine();
        return result;
    }

    public static void close(){
        scanner.close();
    }

}
