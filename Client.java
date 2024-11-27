import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1717);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server");
            while (true) {
                System.out.println("Enter 3D object:");
                String Shapes = scanner.nextLine();
                if (Shapes.equalsIgnoreCase("Q")) {
                    objectOutputStream.writeObject(null);
                    System.out.println("Process finished with exit code 0");
                    break;
                }
                Shape s = null;

                if (Shapes.equalsIgnoreCase("Circle")) {
                    System.out.print("Enter radius: ");
                    double r = scanner.nextDouble();
                    scanner.nextLine();
                    s = new Circle(r);
                } else if (Shapes.equalsIgnoreCase("Rectangle")) {
                    System.out.print("Enter length: ");
                    double l = scanner.nextDouble();
                    System.out.print("Enter width: ");
                    double w = scanner.nextDouble();
                    scanner.nextLine();
                    s = new Rectangle(l, w);
                } else {
                    System.out.println("Invalid object ERROR");
                    continue;
                }
                objectOutputStream.writeObject(s);

                System.out.println(in.readLine());
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
