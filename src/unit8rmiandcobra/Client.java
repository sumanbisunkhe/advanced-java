package unit8rmiandcobra;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            Calculator stub = (Calculator) registry.lookup("CalculatorService");

            System.out.println("Addition: " + stub.add(10, 5));
            System.out.println("Subtraction: " + stub.subtract(10, 5));
            System.out.println("Multiplication: " + stub.multiply(10, 5));
            System.out.println("Division: " + stub.divide(10, 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}